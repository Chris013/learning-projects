package learning.projects.java_ecommerce.product.web;

import java.util.List;
import java.util.UUID;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;
import learning.projects.java_ecommerce.product.assembler.ProductModelAssembler;
import learning.projects.java_ecommerce.product.dto.ProductDto;
import learning.projects.java_ecommerce.product.service.ProductService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
@Tag(name = "Product Management", description = "APIs for managing products")
public class ProductController {

    private final ProductService productService;

    private final ProductModelAssembler productAssembler;

    /**
     * Retrieves all available products.
     *
     * <p>Returns a HATEOAS collection containing all products, where each product
     * includes navigational links such as:
     * <ul>
     *     <li>self link (GET /api/v1/products)</li>
     *     <li>delete link (DELETE /api/v1/products/{id})</li>
     * </ul>
     *
     * @return a collection of product resources wrapped in HATEOAS metadata
     */
    @GetMapping
    public CollectionModel<EntityModel<ProductDto>> getAllProducts() {

        List<ProductDto> products = productService.getAllProducts();

        CollectionModel<EntityModel<ProductDto>> collectionModel = productAssembler.toCollectionModel(products);

        return collectionModel;
    }

    /**
     * Retrieves a single product by its unique identifier.
     *
     * <p>The response includes HATEOAS links such as:
     * <ul>
     *     <li>self link (GET /api/v1/products/{id})</li>
     *     <li>link to product collection (GET /api/v1/products)</li>
     *     <li>delete action link (DELETE /api/v1/products/{id})</li>
     * </ul>
     *
     * @param id the UUID of the product
     * @return the product wrapped in an EntityModel with hypermedia links
     */
    @GetMapping("/{id}")
    public EntityModel<ProductDto> getProduct(@PathVariable UUID id) {

        return productAssembler.toModel(productService.getProductById(id));
    }

    /**
     * Creates a new product.
     *
     * <p>Returns the created product with HATEOAS links.
     * The HTTP response includes:
     * <ul>
     *     <li>201 Created status</li>
     *     <li>Product representation with self link</li>
     * </ul>
     *
     * @param dto the product data to create
     * @return created product wrapped in EntityModel
     */
    @PostMapping
    public ResponseEntity<EntityModel<ProductDto>> createProduct(@RequestBody ProductDto dto) {

        ProductDto product = productService.createProduct(dto);

        EntityModel<ProductDto> entityModel = productAssembler.toModel(product);

        return ResponseEntity.status(HttpStatus.CREATED).body(entityModel);
    }


    /**
     * Creates multiple products in a single request.
     *
     * <p>Each created product is returned with its own HATEOAS links.
     * The response is a collection of resources, not a single entity.
     *
     * <p>HTTP response:
     * <ul>
     *     <li>201 Created</li>
     *     <li>Collection of created products</li>
     * </ul>
     *
     * @param dtos list of products to create
     * @return collection of created product resources
     */
    @PostMapping("/bulk")
    public ResponseEntity<CollectionModel<EntityModel<ProductDto>>> createProducts(@RequestBody List<ProductDto> dtos) {

        List<ProductDto> savedProducts = productService.createAllProducts(dtos);

        CollectionModel<EntityModel<ProductDto>> collectionModel = productAssembler.toCollectionModel(savedProducts);

        return ResponseEntity.status(HttpStatus.CREATED).body(collectionModel);
    }
    
    /**
     * Deletes a product by its ID.
     *
     * <p>If the product does not exist, a runtime exception is thrown
     * and handled by the global exception handler.
     *
     * <p>HTTP response:
     * <ul>
     *     <li>204 No Content on success</li>
     * </ul>
     *
     * @param id the UUID of the product to delete
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable UUID id){
        productService.deleteProductById(id);
        return ResponseEntity.noContent().build();
    }
}
