package learning.projects.java_ecommerce.location.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.List;
import java.util.stream.StreamSupport;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;

import learning.projects.java_ecommerce.location.dto.CountryDto;
import learning.projects.java_ecommerce.location.web.CountryController;

public class CountryModelAssembler implements RepresentationModelAssembler<CountryDto, EntityModel<CountryDto>>{

    @Override
    public EntityModel<CountryDto> toModel(CountryDto entity) {

        return EntityModel.of(
            entity,
            linkTo(methodOn(CountryController.class).getCountryById(entity.countryId())).withSelfRel(),
            linkTo(methodOn(CountryController.class).getAllCountries()).withRel("countries")
        );
    }

    @Override
    public CollectionModel<EntityModel<CountryDto>> toCollectionModel(Iterable<? extends CountryDto> countryCollection) {  

        List<EntityModel<CountryDto>> models = StreamSupport.stream(countryCollection.spliterator(), false).map(this::toModel).toList();

        return CollectionModel.of(
            models,
            linkTo(methodOn(CountryController.class).getAllCountries()).withSelfRel()
        );
    }

}
