package learning.projects.java_ecommerce.location.exception;

import java.util.UUID;

import org.springframework.http.HttpStatus;

import learning.projects.java_ecommerce.common.exception.DomainException;

public class StreetNotFoundException extends DomainException{

    public StreetNotFoundException(UUID id) {
        super(
            "STREET_NOT_FOUND", 
            "Street not found: " + id, 
            HttpStatus.NOT_FOUND
        );
    }
}
