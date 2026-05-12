package learning.projects.java_ecommerce.location.web;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;
import learning.projects.java_ecommerce.location.assembler.CountryModelAssembler;
import learning.projects.java_ecommerce.location.dto.CountryDto;
import learning.projects.java_ecommerce.location.service.CountryService;
import lombok.RequiredArgsConstructor;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/v1/countries")
@RequiredArgsConstructor
@Tag(name = "Country Service", description = "APIs for reading countriy data")
public class CountryController {

    private final CountryService countryService;

    private final CountryModelAssembler countryAssembler;
    
    @GetMapping
    public CollectionModel<EntityModel<CountryDto>> getAllCountries() {

        List<CountryDto> products = countryService.getAllCountries();

        return countryAssembler.toCollectionModel(products);
    }

    @GetMapping("/{id}")
    public EntityModel<CountryDto> getCountryById(@RequestParam Long id) {

        return countryAssembler.toModel(countryService.getCountryById(id));
    }

}
