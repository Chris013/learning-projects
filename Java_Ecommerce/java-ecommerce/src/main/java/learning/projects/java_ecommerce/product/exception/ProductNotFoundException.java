package learning.projects.java_ecommerce.product.exception;

import org.springframework.http.HttpStatus;

import learning.projects.java_ecommerce.common.exception.DomainException;

public class ProductNotFoundException extends DomainException {

    public ProductNotFoundException(String id) {
        super(
            "PRODUCT_NOT_FOUND",
            "Product not found: " + id,
            HttpStatus.NOT_FOUND
        );
    }
}
