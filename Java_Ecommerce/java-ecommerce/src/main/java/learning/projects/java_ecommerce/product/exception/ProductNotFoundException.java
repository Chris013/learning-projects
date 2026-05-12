package learning.projects.java_ecommerce.product.exception;

import java.util.UUID;

import org.springframework.http.HttpStatus;

import learning.projects.java_ecommerce.common.exception.DomainException;

public class ProductNotFoundException extends DomainException {

    public ProductNotFoundException(UUID id) {
        super(
            "PRODUCT_NOT_FOUND",
            "Product not found: " + id,
            HttpStatus.NOT_FOUND
        );
    }
}
