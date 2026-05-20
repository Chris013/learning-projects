package learning.projects.java_ecommerce.core.idempotency.service;

import org.springframework.stereotype.Service;
import org.apache.commons.codec.digest.DigestUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RequestHashService {

    private final ObjectMapper objectMapper;

    public String hash(Object body) {
        try {
            String json = objectMapper.writeValueAsString(body);
            return DigestUtils.sha256Hex(json);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}