package learning.projects.java_ecommerce.location.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.StreamSupport;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import learning.projects.java_ecommerce.location.dto.HouseNumberDto;
import learning.projects.java_ecommerce.location.web.HouseNumberController;

@Component
public class HouseNumberModelAssembler implements RepresentationModelAssembler<HouseNumberDto, EntityModel<HouseNumberDto>> {

    @Override
    public EntityModel<HouseNumberDto> toModel(HouseNumberDto entity) {

        return EntityModel.of(
            entity,
            linkTo(methodOn(HouseNumberController.class).getHouseNumberById(entity.houseNumberId().value())).withSelfRel(),
            linkTo(methodOn(HouseNumberController.class).getAllHouseNumbers()).withRel("house-numbers")
        );
    }

    @Override
    public CollectionModel<EntityModel<HouseNumberDto>> toCollectionModel(Iterable<? extends HouseNumberDto> houseNumberCollection) {
        
        List<EntityModel<HouseNumberDto>> houseNumberModels = StreamSupport.stream(houseNumberCollection.spliterator(), false).map(this::toModel).toList();
        
        return CollectionModel.of(
            houseNumberModels,
            linkTo(methodOn(HouseNumberController.class).getAllHouseNumbers()).withSelfRel()
        );
    }

}
