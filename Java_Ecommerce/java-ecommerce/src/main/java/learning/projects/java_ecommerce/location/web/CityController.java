package learning.projects.java_ecommerce.location.web;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import learning.projects.java_ecommerce.location.assembler.CityModelAssembler;
import learning.projects.java_ecommerce.location.dto.CityDto;
import learning.projects.java_ecommerce.location.dto.CitySearchCriteria;
import learning.projects.java_ecommerce.location.service.CityService;
import lombok.RequiredArgsConstructor;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/api/v1/cities")
@RequiredArgsConstructor
@Tag(name = "City Service", description = "APIs for reading city data")
public class CityController {

    private final CityService cityService;

    private final CityModelAssembler cityAssembler;

    /**
     * Retrieves all existing cities.
     *
     * <p>Returns a HATEOAS collection containing all cities, where each city
     * includes navigational links such as:
     * <ul>
     *     <li>self link (GET /api/v1/citites)</li>
     * </ul>
     *
     * @return a collection of city resources wrapped in HATEOAS metadata
     */
    @GetMapping
    public CollectionModel<EntityModel<CityDto>> getAllCities() {

        return cityAssembler.toCollectionModel(cityService.getAllCities());
    }

    /**
     * Retrieves a single city by its unique identifier.
     *
     * <p>The response includes HATEOAS links such as:
     * <ul>
     *     <li>self link (GET /api/v1/citites/{id})</li>
     *     <li>link to city collection (GET /api/v1/citites)</li>
     * </ul>
     *
     * @param id the unique id of the city
     * @return the city wrapped in an EntityModel with hypermedia links
     */
    @GetMapping("/{id}")
    public EntityModel<CityDto> getCityById(@RequestParam UUID id) {

        return cityAssembler.toModel(cityService.getCityById(id));
    }

        /**
     * Searches cities based on flexible country and city filter criteria.
     *
     * <p>This endpoint allows querying cities using multiple optional filters such as:
     * country ID, country ISO code, country name, and city name prefix.
     * Any combination of these filters can be provided; null values are ignored by the search logic.</p>
     *
     * <p>The result is returned as a HATEOAS-compliant {@link CollectionModel} containing
     * {@link EntityModel} instances of {@link CityDto}, including self and navigation links.</p>
     *
     * @param searchCriteria the search criteria used to filter cities, including:
     *                       <ul>
     *                         <li>countryId - the internal database ID of the country</li>
     *                         <li>countryIsoCode - ISO code of the country (e.g. "DE")</li>
     *                         <li>countryName - full name of the country</li>
     *                         <li>cityNameStartsWith - prefix filter for city names</li>
     *                       </ul>
     *
     * @return a HATEOAS {@link CollectionModel} of {@link CityDto} wrapped in {@link EntityModel},
     *         each enriched with hypermedia links such as self and collection navigation links
     *
     * @see CitySearchCriteria
     * @see CityService#searchCitiesByCriteria(CitySearchCriteria)
     * @see CityModelAssembler
     */
    @GetMapping("/search")
    public CollectionModel<EntityModel<CityDto>> getCityByCriteria(@RequestBody CitySearchCriteria searchCriteria) {
        List<CityDto> results = cityService.searchCitiesByCriteria(searchCriteria);

        return cityAssembler.toCollectionModel(results);
    }

}
