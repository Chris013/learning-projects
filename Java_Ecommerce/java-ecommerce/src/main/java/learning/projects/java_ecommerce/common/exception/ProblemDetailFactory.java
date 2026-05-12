package learning.projects.java_ecommerce.common.exception;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class ProblemDetailFactory {
    
    public static ProblemDetail create(
            HttpStatus status,
            String title,
            String detail,
            String code,
            String path
    ) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(status, detail);

        problem.setTitle(title);
        problem.setProperty("code", code);
        problem.setProperty("timestamp", Instant.now().toString());
        problem.setProperty("path", path);

        return problem;
    }
}
