package learning.projects.java_ecommerce.location.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.StreamSupport;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import learning.projects.java_ecommerce.location.dto.StreetDto;
import learning.projects.java_ecommerce.location.web.StreetController;

@Component
public class StreetModelAssembler implements RepresentationModelAssembler<StreetDto, EntityModel<StreetDto>>{

    @Override
    public EntityModel<StreetDto> toModel(StreetDto entity) {
        return EntityModel.of(
            entity,
            linkTo(methodOn(StreetController.class).getStreetById(entity.streetId().value())).withSelfRel(),
            linkTo(methodOn(StreetController.class).getAllStreets()).withRel("streets")
        );
    }

    @Override
    public CollectionModel<EntityModel<StreetDto>> toCollectionModel(Iterable<? extends StreetDto> streetCollection) {

        List<EntityModel<StreetDto>> streetModels = StreamSupport.stream(streetCollection.spliterator(), false).map(this::toModel).toList();
        
        return CollectionModel.of(
            streetModels,
            linkTo(methodOn(StreetController.class).getAllStreets()).withSelfRel()
        );
    }

}
