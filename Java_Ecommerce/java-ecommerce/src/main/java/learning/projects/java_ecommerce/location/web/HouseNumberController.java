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

    @GetMapping
    public CollectionModel<EntityModel<HouseNumberDto>> getAllHouseNumbers() {
        return houseNumberAssembler.toCollectionModel(houseNumberService.getAllHouseNumbers());
    }

    @GetMapping("/{id}")
    public EntityModel<HouseNumberDto> getHouseNumberById(@PathVariable UUID id) {
        return houseNumberAssembler.toModel(houseNumberService.getHouseNumberById(id));
    }

    @GetMapping("/search")
    public CollectionModel<EntityModel<HouseNumberDto>> getAllHouseNumbersBySearchCriteria(@RequestBody HouseNumberSearchCriteria searchCriteria) {

        List<HouseNumberDto> houseNumbers = houseNumberService.getAllHouseNumbersByCriteria(searchCriteria);

        return houseNumberAssembler.toCollectionModel(houseNumbers);
    }

}
