package learning.projects.java_ecommerce.location.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import learning.projects.java_ecommerce.location.dto.CityDto;
import learning.projects.java_ecommerce.location.mapper.CityMapper;
import learning.projects.java_ecommerce.location.repo.CityRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CityService {

    private final CityRepository cityRepo;

    public List<CityDto> getAllCities() {
        return cityRepo.findAll().stream().map(CityMapper::toDto).toList();
    }

    public CityDto getCityById(UUID id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCityById'");
    }

}
