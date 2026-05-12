package learning.projects.java_ecommerce.product.web;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.List;
import java.util.UUID;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import learning.projects.java_ecommerce.product.dto.ProductDto;
import learning.projects.java_ecommerce.product.service.ProductService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;

    @GetMapping
    public CollectionModel<EntityModel<ProductDto>> getAllProducts() {

        List<ProductDto> products = service.getAllProducts();

        List<EntityModel<ProductDto>> productEntityModels = products.stream()
            .map(product -> EntityModel.of(
                product,
                linkTo(methodOn(ProductController.class)
                    .getProduct(product.productId().value()))
                    .withSelfRel()
            ))
            .toList();

        return CollectionModel.of(productEntityModels,
            linkTo(methodOn(ProductController.class).getAllProducts()).withSelfRel());
    }

    @GetMapping("/{id}")
    public ProductDto getProduct(@PathVariable UUID id) {
        return service.getProductById(id);
    }

    @PostMapping
    public ProductDto createProduct(@RequestBody ProductDto dto) {
        return service.createProduct(dto);
    }
}
