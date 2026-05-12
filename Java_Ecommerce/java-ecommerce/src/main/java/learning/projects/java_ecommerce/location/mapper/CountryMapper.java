package learning.projects.java_ecommerce.location.mapper;

import org.springframework.stereotype.Component;

import learning.projects.java_ecommerce.location.dto.CountryDto;
import learning.projects.java_ecommerce.location.model.Country;

@Component
public class CountryMapper {

    public static CountryDto toDto(Country country) {
        return new CountryDto(country.getId(), country.getCountryCode(), country.getCountryName());
    }

}
