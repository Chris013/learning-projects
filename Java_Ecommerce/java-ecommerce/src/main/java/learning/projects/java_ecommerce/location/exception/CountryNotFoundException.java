package learning.projects.java_ecommerce.location.exception;

import org.springframework.http.HttpStatus;

import learning.projects.java_ecommerce.common.exception.DomainException;

public class CountryNotFoundException extends DomainException{

    public CountryNotFoundException(Long id) {
        super(
            "COUNTRY_NOT_FOUND",
            "Country not found: " + id,
            HttpStatus.NOT_FOUND
        );
    }

}
