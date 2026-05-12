package learning.projects.java_ecommerce.location.service;

import java.util.List;

import org.springframework.stereotype.Service;

import learning.projects.java_ecommerce.location.dto.CountryDto;
import learning.projects.java_ecommerce.location.exception.CountryNotFoundException;
import learning.projects.java_ecommerce.location.mapper.CountryMapper;
import learning.projects.java_ecommerce.location.repo.CountryRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CountryService {

    private final CountryRepository countryRepo;

    public List<CountryDto> getAllCountries() {
        return countryRepo.findAll().stream()
            .map(CountryMapper::toDto)
            .toList();
    }

    public CountryDto getCountryById(Long id) {
        return countryRepo.findById(id)
            .map(CountryMapper::toDto)
            .orElseThrow(() -> new CountryNotFoundException(id));
    }
}
