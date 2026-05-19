package learning.projects.java_ecommerce.core.idempotency.repo;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import learning.projects.java_ecommerce.core.idempotency.model.IdempotencyRecord;

public interface IdempotencyRepository extends JpaRepository<IdempotencyRecord, String> {
    Optional<IdempotencyRecord> findByIdempotencyKeyAndEndpoint(String key, String endpoint);

    //TODO: use Redis instead of normal DB for this
}

