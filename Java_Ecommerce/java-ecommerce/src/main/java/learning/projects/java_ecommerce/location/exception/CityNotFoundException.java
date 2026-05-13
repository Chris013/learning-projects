package learning.projects.java_ecommerce.location.exception;

import java.util.UUID;

import org.springframework.http.HttpStatus;

import learning.projects.java_ecommerce.common.exception.DomainException;

public class CityNotFoundException extends DomainException{

    public CityNotFoundException(UUID id) {
        super(
            "CITY_NOT_FOUND", 
            "City not found: " + id, 
            HttpStatus.NOT_FOUND
        );
    }

}
