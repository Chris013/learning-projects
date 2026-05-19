package learning.projects.java_ecommerce.core.idempotency.annotation;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Idempotent {
    /**
     * TTL in seconds for stored responses (optional).
     */
    long ttlSeconds() default 3600;
}
