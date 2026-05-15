package learning.projects.java_ecommerce.core.idempotency;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IdempotencyRepository
        extends JpaRepository<IdempotencyRecord, String> {

    Optional<IdempotencyRecord> findByIdempotencyKeyAndEndpoint(
            String key,
            String endpoint
    );
}
