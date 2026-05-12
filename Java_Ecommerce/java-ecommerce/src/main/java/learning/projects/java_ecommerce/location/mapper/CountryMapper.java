package learning.projects.java_ecommerce.location.mapper;

import learning.projects.java_ecommerce.location.dto.CountryDto;
import learning.projects.java_ecommerce.location.model.Country;

public class CountryMapper {

    public static CountryDto toDto(Country country) {
        return new CountryDto(country.getId(), country.getCountryCode(), country.getCountryName());
    }

}
