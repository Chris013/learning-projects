package learning.projects.java_ecommerce.location.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.StreamSupport;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import learning.projects.java_ecommerce.location.dto.CityDto;
import learning.projects.java_ecommerce.location.web.CityController;

@Component
public class CityModelAssembler implements RepresentationModelAssembler<CityDto, EntityModel<CityDto>>{

    @Override
    public EntityModel<CityDto> toModel(CityDto entity) {

        return EntityModel.of(
            entity,
            linkTo(methodOn(CityController.class).getCityById(entity.cityId().value())).withSelfRel(),
            linkTo(methodOn(CityController.class).getAllCities()).withRel("cities")
        );
    }

    @Override
    public CollectionModel<EntityModel<CityDto>> toCollectionModel(Iterable<? extends CityDto> cityCollection) {

        List<EntityModel<CityDto>> models = StreamSupport.stream(cityCollection.spliterator(), false).map(this::toModel).toList();


        return CollectionModel.of(
            models,
            linkTo(methodOn(CityController.class).getAllCities()).withSelfRel()
        );
    }

}
