package learning.projects.java_ecommerce.customer.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import learning.projects.java_ecommerce.customer.dto.CustomerDto;
import learning.projects.java_ecommerce.customer.web.CustomerController;

@Component
public class CustomerModelAssembler implements RepresentationModelAssembler<CustomerDto, EntityModel<CustomerDto>>{

    @Override
    public EntityModel<CustomerDto> toModel(CustomerDto entity) {
        return EntityModel.of(
            entity,
            linkTo(methodOn(CustomerController.class).getCustomerById(entity.id().value())).withSelfRel()
        );
    }
}
