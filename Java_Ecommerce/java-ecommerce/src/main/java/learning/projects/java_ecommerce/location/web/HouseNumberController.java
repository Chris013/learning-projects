package learning.projects.java_ecommerce.location.web;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import learning.projects.java_ecommerce.location.dto.HouseNumberDto;
import learning.projects.java_ecommerce.location.service.HouseNumberService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/house-numbers")
@RequiredArgsConstructor
public class HouseNumberController {

    private final HouseNumberService houseNumberService;

    @GetMapping
    public List<HouseNumberDto> getAllHouseNumbers() {
        return houseNumberService.getAllHouseNumbers();
    }

    @GetMapping("/{id}")
    public HouseNumberDto getHouseNumberById(@PathVariable UUID id) {
        return houseNumberService.getHouseNumberById(id);
    }

}
