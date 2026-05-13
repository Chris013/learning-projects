package learning.projects.java_ecommerce.location.service;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import learning.projects.java_ecommerce.location.dto.HouseNumberDto;
import learning.projects.java_ecommerce.location.dto.HouseNumberSearchCriteria;
import learning.projects.java_ecommerce.location.exception.HouseNumberNotFoundException;
import learning.projects.java_ecommerce.location.mapper.HouseNumberMapper;
import learning.projects.java_ecommerce.location.model.HouseNumber;
import learning.projects.java_ecommerce.location.model.HouseNumberId;
import learning.projects.java_ecommerce.location.repo.HouseNumberRepository;
import learning.projects.java_ecommerce.location.repo.HouseNumberSpecification;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HouseNumberService {

    private final HouseNumberRepository houseNumberRepo;

    public List<HouseNumberDto> getAllHouseNumbers() {
        return houseNumberRepo.findAll().stream().map(HouseNumberMapper::toDto).toList();
    }

    public HouseNumberDto getHouseNumberById(UUID id) {

        HouseNumberId houseNumberId = new HouseNumberId(id);

        return houseNumberRepo.findById(houseNumberId)
        .map(HouseNumberMapper::toDto)
        .orElseThrow(() -> new HouseNumberNotFoundException(id));
    }

    public List<HouseNumberDto> getAllHouseNumbersByCriteria(HouseNumberSearchCriteria searchCriteria) {

        Specification<HouseNumber> spec = Specification
            .where(HouseNumberSpecification.hasStreetId(searchCriteria.streetId()))
            .and(HouseNumberSpecification.hasStreetName(searchCriteria.streetName()))
            .and(HouseNumberSpecification.houseNumberStartsWith(searchCriteria.houseNumber()));
        
        return houseNumberRepo.findAll(spec).stream().map(HouseNumberMapper::toDto).toList();
    }

}
