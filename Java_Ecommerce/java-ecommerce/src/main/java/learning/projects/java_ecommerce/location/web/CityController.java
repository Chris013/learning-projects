package learning.projects.java_ecommerce.location.web;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import learning.projects.java_ecommerce.location.dto.CityDto;
import learning.projects.java_ecommerce.location.service.CityService;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/api/v1/cities")
@RequiredArgsConstructor
public class CityController {

    private final CityService cityService;

    @GetMapping
    public List<CityDto> getAllCities() {
        return cityService.getAllCities();
    }

    @GetMapping("/{id}")
    public CityDto getCityById(@RequestParam UUID id) {
        return cityService.getCityById(id);
    }

}
