package learning.projects.java_ecommerce.location.mapper;

import org.springframework.stereotype.Component;

import learning.projects.java_ecommerce.location.dto.CityDto;
import learning.projects.java_ecommerce.location.model.City;
import learning.projects.java_ecommerce.location.model.Country;

@Component
public class CityMapper {

    public static CityDto toDto(City city) {
        return new CityDto(city.getId(), city.getName(), city.getZipCode(), city.getCountry().getId());
    }

    public static City toEntity(CityDto cityDto) {
        City city = new City();

        city.setId(cityDto.cityId());
        city.setName(cityDto.name());
        city.setZipCode(cityDto.zipCode());
        city.setCountry(Country.builder().id(cityDto.countryId()).build());

        return city;
    }

}
