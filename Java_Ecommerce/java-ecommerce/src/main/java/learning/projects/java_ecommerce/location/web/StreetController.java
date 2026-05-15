package learning.projects.java_ecommerce.location.web;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import learning.projects.java_ecommerce.location.assembler.StreetModelAssembler;
import learning.projects.java_ecommerce.location.dto.StreetDto;
import learning.projects.java_ecommerce.location.dto.StreetSearchCriteria;
import learning.projects.java_ecommerce.location.service.StreetService;
import lombok.RequiredArgsConstructor;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/api/v1/streets")
@RequiredArgsConstructor
@Tag(name = "Street Service", description = "APIs for reading street data")
public class StreetController {

    private final StreetService streetService;

    private final StreetModelAssembler streetAssembler;
    
    /**
     * Retrieves all existing streets.
     *
     * <p>Returns a HATEOAS collection containing all streets, where each street
     * includes navigational links such as:
     * <ul>
     *     <li>self link (GET /api/v1/streets)</li>
     * </ul>
     *
     * @return a collection of streets resources wrapped in HATEOAS metadata
     */
    @GetMapping
    public CollectionModel<EntityModel<StreetDto>> getAllStreets() {
        return streetAssembler.toCollectionModel(streetService.getAllStreets());
    }       

    /**
     * Retrieves a single street by its unique identifier.
     *
     * <p>The response includes HATEOAS links such as:
     * <ul>
     *     <li>self link (GET /api/v1/streets/{id})</li>
     *     <li>link to street collection (GET /api/v1/streets)</li>
     * </ul>
     *
     * @param id the unique id of the street
     * @return the street wrapped in an EntityModel with hypermedia links
     */
    @GetMapping("/{id}")
    public EntityModel<StreetDto> getStreetById(@PathVariable UUID id) {
        return streetAssembler.toModel(streetService.getStreetById(id));
    }

    /**
     * Searches streets based on flexible city and streets filter criteria.
     *
     * <p>This endpoint allows querying streets using multiple optional filters such as:
     * city ID, city name, city zip code and street name
     * Any combination of these filters can be provided; null values are ignored by the search logic.</p>
     *
     * <p>The result is returned as a HATEOAS-compliant {@link CollectionModel} containing
     * {@link EntityModel} instances of {@link StreetDto}, including self and navigation links.</p>
     *
     * @param searchCriteria the search criteria used to filter streets, including:
     *                       <ul>
     *                         <li>cityId - the internal database ID of the city</li>
     *                         <li>cityName - name of the city</li>
     *                         <li>zipCode - the zip code of the city</li>
     *                         <li>streetName - the start of the street name</li>
     *                       </ul>
     *
     * @return a HATEOAS {@link CollectionModel} of {@link StreetDto} wrapped in {@link EntityModel},
     *         each enriched with hypermedia links such as self and collection navigation links
     *
     * @see StreetSearchCriteria
     * @see StreetService#getAllStreetsByCriteria(StreetSearchCriteria)
     * @see StreetModelAssembler
     */
    @GetMapping("/search")
    public CollectionModel<EntityModel<StreetDto>> getAllStreetsByCriteria(@RequestBody StreetSearchCriteria searchCriteria){

        List<StreetDto> result = streetService.getAllStreetsByCriteria(searchCriteria);

        return streetAssembler.toCollectionModel(result);
    }
    
}
