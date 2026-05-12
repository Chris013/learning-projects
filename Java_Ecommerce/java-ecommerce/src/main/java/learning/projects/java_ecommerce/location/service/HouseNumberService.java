package learning.projects.java_ecommerce.location.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import learning.projects.java_ecommerce.location.dto.HouseNumberDto;
import learning.projects.java_ecommerce.location.mapper.HouseNumberMapper;
import learning.projects.java_ecommerce.location.repo.HouseNumberRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HouseNumberService {

    private final HouseNumberRepository houseNumberRepo;

    public List<HouseNumberDto> getAllHouseNumbers() {
        return houseNumberRepo.findAll().stream().map(HouseNumberMapper::toDto).toList();
    }

    public HouseNumberDto getHouseNumberById(UUID id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getHouseNumberById'");
    }

}
