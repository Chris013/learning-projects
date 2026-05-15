package learning.projects.java_ecommerce.location.web;

import java.util.List;
import java.util.UUID;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import learning.projects.java_ecommerce.location.assembler.HouseNumberModelAssembler;
import learning.projects.java_ecommerce.location.dto.HouseNumberDto;
import learning.projects.java_ecommerce.location.dto.HouseNumberSearchCriteria;
import learning.projects.java_ecommerce.location.service.HouseNumberService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/house-numbers")
@RequiredArgsConstructor
public class HouseNumberController {

    private final HouseNumberService houseNumberService;

    private final HouseNumberModelAssembler houseNumberAssembler;

    /**
     * Retrieves all existing house numbers.
     *
     * <p>Returns a HATEOAS collection containing all house numbers, where each house number
     * includes navigational links such as:
     * <ul>
     *     <li>self link (GET /api/v1/house-numbers)</li>
     * </ul>
     *
     * @return a collection of house number resources wrapped in HATEOAS metadata
     */
    @GetMapping
    public CollectionModel<EntityModel<HouseNumberDto>> getAllHouseNumbers() {
        return houseNumberAssembler.toCollectionModel(houseNumberService.getAllHouseNumbers());
    }

    /**
     * Retrieves a single house number by its unique identifier.
     *
     * <p>The response includes HATEOAS links such as:
     * <ul>
     *     <li>self link (GET /api/v1/house-numbers/{id})</li>
     *     <li>link to house-number collection (GET /api/v1/house-numbers)</li>
     * </ul>
     *
     * @param id the unique id of the house number
     * @return the house number wrapped in an EntityModel with hypermedia links
     */
    @GetMapping("/{id}")
    public EntityModel<HouseNumberDto> getHouseNumberById(@PathVariable UUID id) {
        return houseNumberAssembler.toModel(houseNumberService.getHouseNumberById(id));
    }

    /**
     * Searches house numbers based on flexible street and house number filter criteria.
     *
     * <p>This endpoint allows querying house numbers using multiple optional filters such as:
     * street ID, street name, and house number
     * Any combination of these filters can be provided; null values are ignored by the search logic.</p>
     *
     * <p>The result is returned as a HATEOAS-compliant {@link CollectionModel} containing
     * {@link EntityModel} instances of {@link HouseNumberDto}, including self and navigation links.</p>
     *
     * @param searchCriteria the search criteria used to filter house numbers, including:
     *                       <ul>
     *                         <li>streetId - the internal database ID of the street</li>
     *                         <li>streetName - name of the street</li>
     *                         <li>houseNumber - the house number</li>
     *                       </ul>
     *
     * @return a HATEOAS {@link CollectionModel} of {@link HouseNumberDto} wrapped in {@link EntityModel},
     *         each enriched with hypermedia links such as self and collection navigation links
     *
     * @see HouseNumberSearchCriteria
     * @see HouseNumberService#getAllHouseNumbersByCriteria(HouseNumberSearchCriteria)
     * @see HouseNumberModelAssembler
     */
    @GetMapping("/search")
    public CollectionModel<EntityModel<HouseNumberDto>> getAllHouseNumbersBySearchCriteria(@RequestBody HouseNumberSearchCriteria searchCriteria) {

        List<HouseNumberDto> houseNumbers = houseNumberService.getAllHouseNumbersByCriteria(searchCriteria);

        return houseNumberAssembler.toCollectionModel(houseNumbers);
    }

}
