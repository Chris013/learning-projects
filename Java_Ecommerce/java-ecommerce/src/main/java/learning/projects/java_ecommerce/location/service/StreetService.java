package learning.projects.java_ecommerce.location.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import learning.projects.java_ecommerce.location.dto.StreetDto;
import learning.projects.java_ecommerce.location.mapper.StreetMapper;
import learning.projects.java_ecommerce.location.repo.StreetRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StreetService {

    private final StreetRepository streetRepo;

    public List<StreetDto> getAllStreets() {
        return streetRepo.findAll().stream().map(StreetMapper::toDto).toList();
    }

    public StreetDto getStreetById(UUID id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getStreetById'");
    }
}
