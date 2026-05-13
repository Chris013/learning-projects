package learning.projects.java_ecommerce.location.service;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import learning.projects.java_ecommerce.location.dto.CityDto;
import learning.projects.java_ecommerce.location.dto.CitySearchCriteriaByCountry;
import learning.projects.java_ecommerce.location.exception.CityNotFoundException;
import learning.projects.java_ecommerce.location.mapper.CityMapper;
import learning.projects.java_ecommerce.location.model.City;
import learning.projects.java_ecommerce.location.model.CityId;
import learning.projects.java_ecommerce.location.repo.CityRepository;
import learning.projects.java_ecommerce.location.repo.CitySpecification;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CityService {

    private final CityRepository cityRepo;

    public List<CityDto> getAllCities() {
        return cityRepo.findAll().stream()
        .map(CityMapper::toDto)
        .toList();
    }

    public CityDto getCityById(UUID id) {

        CityId cityId = new CityId(id);

        return cityRepo.findById(cityId)
        .map(CityMapper::toDto)
        .orElseThrow(() -> new CityNotFoundException(id));
    }

    public List<CityDto> searchCitiesByCriteria(CitySearchCriteriaByCountry searchCriteria) {
        Specification<City> spec = Specification
            .where(CitySpecification.hasCountryId(searchCriteria.countryId()))
            .and(CitySpecification.hasCountryIsoCode(searchCriteria.countryIsoCode()))
            .and(CitySpecification.hasCountryName(searchCriteria.countryName()))
            .and(CitySpecification.nameStartsWith(searchCriteria.cityNameStartsWith()));

        return cityRepo.findAll(spec).stream()
        .map(CityMapper::toDto)
        .toList();
    }

}
