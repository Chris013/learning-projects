package learning.projects.java_ecommerce.product.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.List;
import java.util.stream.StreamSupport;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import learning.projects.java_ecommerce.product.dto.ProductDto;
import learning.projects.java_ecommerce.product.web.ProductController;

@Component
public class ProductModelAssembler
    implements RepresentationModelAssembler<ProductDto, EntityModel<ProductDto>> {

    @Override
    public EntityModel<ProductDto> toModel(ProductDto product) {

        return EntityModel.of(
            product,
            linkTo(methodOn(ProductController.class).getProduct(product.productId().value())).withSelfRel(),
            linkTo(methodOn(ProductController.class).getAllProducts()).withRel("products"),     
            linkTo(methodOn(ProductController.class).deleteProduct(product.productId().value())).withRel("delete")
        );
    }

    @Override
    public CollectionModel<EntityModel<ProductDto>> toCollectionModel(Iterable<? extends ProductDto> productCollection) {    
        
        List<EntityModel<ProductDto>> models = StreamSupport.stream(productCollection.spliterator(), false)
            .map(this::toModel)
            .toList();

        return CollectionModel.of(
            models,
            linkTo(methodOn(ProductController.class).getAllProducts()).withSelfRel()
        );
    }
}
