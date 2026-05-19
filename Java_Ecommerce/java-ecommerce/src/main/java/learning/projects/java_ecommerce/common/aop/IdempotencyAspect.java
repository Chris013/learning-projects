package learning.projects.java_ecommerce.common.aop;

import java.lang.reflect.Type;
import java.time.Instant;
import java.util.Optional;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.*;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import learning.projects.java_ecommerce.core.idempotency.annotation.Idempotent;
import learning.projects.java_ecommerce.core.idempotency.model.IdempotencyRecord;
import learning.projects.java_ecommerce.core.idempotency.service.IdempotencyService;
import learning.projects.java_ecommerce.core.idempotency.service.RequestHashService;
import lombok.RequiredArgsConstructor;

@Aspect
@Component
@RequiredArgsConstructor
public class IdempotencyAspect {

    private final IdempotencyService idempotencyService;
    private final RequestHashService hashService;
    private final ObjectMapper objectMapper;

    @Around("@annotation(idempotent) && within(@org.springframework.web.bind.annotation.RestController *)")
    public Object aroundIdempotent(ProceedingJoinPoint pjp, Idempotent idempotent) throws Throwable {
        //TODO: test this intesively
        ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attrs == null) return pjp.proceed();

        HttpServletRequest request = attrs.getRequest();
        String key = request.getHeader("Idempotency-Key");
        if (key == null || key.isBlank()) {
            return pjp.proceed();
        }

        String endpoint = request.getMethod() + ":" + request.getRequestURI();

        // compute request hash from body argument(s)
        Object[] args = pjp.getArgs();
        Object body = args.length > 0 ? args[0] : null;
        String currentHash = hashService.hash(body);

        Optional<IdempotencyRecord> existing = idempotencyService.get(key, endpoint);
        if (existing.isPresent()) {
            IdempotencyRecord record = existing.get();
            if (!record.getRequestHash().equals(currentHash)) {
                throw new IllegalStateException("Idempotency-Key reused with different payload");
            }
            // return stored response
            String json = record.getResponseBody();
            HttpStatusCode status = record.getStatusCode();

            // attempt to deserialize into the controller return type
            MethodSignature ms = (MethodSignature) pjp.getSignature();
            Type returnType = ms.getMethod().getGenericReturnType();
            Object bodyObj = objectMapper.readValue(json, objectMapper.getTypeFactory().constructType(returnType));
            if (bodyObj instanceof ResponseEntity) {
                return bodyObj;
            } else {
                return ResponseEntity.status(status).body(bodyObj);
            }
        }

        // proceed and capture result
        Object result = pjp.proceed();

        // serialize response body and status
        ResponseEntity<?> responseEntity;
        if (result instanceof ResponseEntity) {
            responseEntity = (ResponseEntity<?>) result;
        } else {
            responseEntity = ResponseEntity.ok(result);
        }

        String responseJson = objectMapper.writeValueAsString(responseEntity.getBody());
        IdempotencyRecord record = new IdempotencyRecord();
        record.setIdempotencyKey(key);
        record.setEndpoint(endpoint);
        record.setRequestHash(currentHash);
        record.setResponseBody(responseJson);
        record.setStatusCode(responseEntity.getStatusCode());
        record.setCreatedAt(Instant.now());
        record.setExpiresAt(Instant.now().plusSeconds(idempotent.ttlSeconds()));

        idempotencyService.save(record);

        return result;
    }
}
