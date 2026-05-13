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
    
    @GetMapping
    public CollectionModel<EntityModel<StreetDto>> getAllStreets() {
        return streetAssembler.toCollectionModel(streetService.getAllStreets());
    }       

    @GetMapping("/{id}")
    public EntityModel<StreetDto> getStreetById(@PathVariable UUID id) {
        return streetAssembler.toModel(streetService.getStreetById(id));
    }

    @GetMapping("/search")
    public CollectionModel<EntityModel<StreetDto>> getAllStreetsByCriteria(@RequestBody StreetSearchCriteria searchCriteria){

        List<StreetDto> result = streetService.getAllStreetsByCriteria(searchCriteria);

        return streetAssembler.toCollectionModel(result);
    }
    
}
