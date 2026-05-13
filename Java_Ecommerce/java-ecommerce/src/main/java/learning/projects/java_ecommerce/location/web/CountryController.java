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
    
    /**
     * Retrieves all existing countries.
     *
     * <p>Returns a HATEOAS collection containing all countries, where each country
     * includes navigational links such as:
     * <ul>
     *     <li>self link (GET /api/v1/countries)</li>
     * </ul>
     *
     * @return a collection of country resources wrapped in HATEOAS metadata
     */
    @GetMapping
    public CollectionModel<EntityModel<CountryDto>> getAllCountries() {

        List<CountryDto> products = countryService.getAllCountries();

        return countryAssembler.toCollectionModel(products);
    }

    /**
     * Retrieves a single country by its unique identifier.
     *
     * <p>The response includes HATEOAS links such as:
     * <ul>
     *     <li>self link (GET /api/v1/countries/{id})</li>
     *     <li>link to country collection (GET /api/v1/countries) </li>
     * </ul>
     *
     * @param id the unique id of the country
     * @return the country wrapped in an EntityModel with hypermedia links
     */
    @GetMapping("/{id}")
    public EntityModel<CountryDto> getCountryById(@RequestParam Long id) {

        return countryAssembler.toModel(countryService.getCountryById(id));
    }

}
