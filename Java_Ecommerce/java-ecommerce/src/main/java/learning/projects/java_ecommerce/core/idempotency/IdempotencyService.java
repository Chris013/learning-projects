package learning.projects.java_ecommerce.core.idempotency;

import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class IdempotencyService {

    private final IdempotencyRepository repo;

    public IdempotencyService(IdempotencyRepository repo) {
        this.repo = repo;
    }

    public Optional<IdempotencyRecord> get(String key, String endpoint) {
        return repo.findByIdempotencyKeyAndEndpoint(key, endpoint);
    }

    public void save(IdempotencyRecord record) {
        repo.save(record);
    }
}
