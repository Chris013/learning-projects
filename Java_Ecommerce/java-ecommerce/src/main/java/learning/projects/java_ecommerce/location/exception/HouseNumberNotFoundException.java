package learning.projects.java_ecommerce.location.exception;

import java.util.UUID;

import org.springframework.http.HttpStatus;

import learning.projects.java_ecommerce.common.exception.DomainException;

public class HouseNumberNotFoundException extends DomainException{

    public HouseNumberNotFoundException(UUID id) {
        super(
            "HOUSE_NUMBER_NOT_FOUND", 
            "Housenumber not found: " + id, 
            HttpStatus.NOT_FOUND
        );
    }

}
