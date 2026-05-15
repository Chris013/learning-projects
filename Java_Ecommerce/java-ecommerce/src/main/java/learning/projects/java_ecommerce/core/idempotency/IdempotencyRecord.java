package learning.projects.java_ecommerce.core.idempotency;

import java.time.Instant;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "idempotency_keys")
public class IdempotencyRecord {

    @Id
    private String idempotencyKey;

    private String endpoint;

    private String requestHash;

    @Lob
    private String responseBody;

    private int statusCode;

    private Instant createdAt;

    private Instant expiresAt;
}
