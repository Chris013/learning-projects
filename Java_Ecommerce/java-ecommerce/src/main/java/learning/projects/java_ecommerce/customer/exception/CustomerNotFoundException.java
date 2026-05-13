package learning.projects.java_ecommerce.customer.exception;

import java.util.UUID;

import org.springframework.http.HttpStatus;

import learning.projects.java_ecommerce.common.exception.DomainException;

public class CustomerNotFoundException extends DomainException{

    public CustomerNotFoundException(UUID id) {
        super(
            "CUSTOMER_NOT_FOUND", 
            "Customer not found: " + id, 
            HttpStatus.NOT_FOUND
        );
    }

}
