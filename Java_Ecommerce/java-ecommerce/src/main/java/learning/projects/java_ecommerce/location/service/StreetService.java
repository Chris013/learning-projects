package learning.projects.java_ecommerce.location.service;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import learning.projects.java_ecommerce.location.dto.StreetDto;
import learning.projects.java_ecommerce.location.dto.StreetSearchCriteria;
import learning.projects.java_ecommerce.location.exception.StreetNotFoundException;
import learning.projects.java_ecommerce.location.mapper.StreetMapper;
import learning.projects.java_ecommerce.location.model.Street;
import learning.projects.java_ecommerce.location.model.StreetId;
import learning.projects.java_ecommerce.location.repo.StreetRepository;
import learning.projects.java_ecommerce.location.repo.StreetSpecification;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StreetService {

    private final StreetRepository streetRepo;

    public List<StreetDto> getAllStreets() {
        return streetRepo.findAll().stream()
        .map(StreetMapper::toDto)
        .toList();
    }

    public StreetDto getStreetById(UUID id) {

        StreetId streetId = new StreetId(id);

        return streetRepo.findById(streetId)
        .map(StreetMapper::toDto)
        .orElseThrow(() -> new StreetNotFoundException(id));
    }

    public List<StreetDto> getAllStreetsByCriteria(StreetSearchCriteria searchCriteria) {
            
        Specification<Street> spec = Specification.
        where(StreetSpecification.hasCityId(searchCriteria.cityId()))
        .and(StreetSpecification.hasCityName(searchCriteria.cityName()))
        .and(StreetSpecification.hasCityZipCode(searchCriteria.zipCode()))
        .and(StreetSpecification.nameStartsWith(searchCriteria.streetNameStartsWith()));

        return streetRepo.findAll(spec).stream()
        .map(StreetMapper::toDto)
        .toList();
    }
}
