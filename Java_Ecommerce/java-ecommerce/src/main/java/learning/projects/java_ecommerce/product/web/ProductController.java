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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;
import learning.projects.java_ecommerce.product.assembler.ProductModelAssembler;
import learning.projects.java_ecommerce.product.dto.ProductDto;
import learning.projects.java_ecommerce.product.dto.ProductSearchCriteria;
import learning.projects.java_ecommerce.product.service.ProductService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
@Tag(name = "Product Management", description = "APIs for managing products")
public class ProductController {

    private final ProductService productService;
    private final ProductModelAssembler productAssembler;

    /*private final IdempotencyService idempotencyService;
    private final RequestHashService hashService;
    private final ObjectMapper objectMapper;*/

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
    public ResponseEntity<EntityModel<ProductDto>> createProduct(@RequestHeader("Idempotency-Key") String key, @RequestBody ProductDto dto) {

        //TODO: use Redis instead of normal DB for this
        //TODO: instead of implementing this in every endpoint, implement the IdempotencyInterceptor

        /*String endpoint = "POST:/products";

        // 1. Check existing request
        var existing = idempotencyService.get(key, endpoint);

        if (existing.isPresent()) {
            IdempotencyRecord record = existing.get();

            return ResponseEntity
                    .status(record.getStatusCode())
                    .body(objectMapper.readValue(
                            record.getResponseBody(),
                            ProductResponse.class
                    ));
        }
        
        2. If same key but different payload:
        if (!existing.requestHash.equals(currentHash)) {
            throw new IllegalStateException("Idempotency-Key reused with different payload");
        }
        */

        ProductDto product = productService.createProduct(dto);

        // 3. Store result
        /*IdempotencyRecord record = new IdempotencyRecord();
        record.setIdempotencyKey(key);
        record.setEndpoint(endpoint);
        record.setRequestHash(hashService.hash(request));
        record.setResponseBody(objectMapper.writeValueAsString(response));
        record.setStatusCode(201);
        record.setCreatedAt(Instant.now());

        idempotencyService.save(record);*/

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

    /**
     * Searches products based on flexible filter criteria.
     *
     * <p>This endpoint allows querying products using multiple optional filters such as:
     * text search, prices and quantities.
     * Any combination of these filters can be provided; null values are ignored by the search logic.</p>
     *
     * <p>The result is returned as a HATEOAS-compliant {@link CollectionModel} containing
     * {@link EntityModel} instances of {@link ProductDto}, including self and navigation links.</p>
     *
     * @param searchCriteria the search criteria used to filter products, including:
     *                       <ul>
     *                         <li>productId - the internal database ID of the product</li>
     *                         <li>barcode - the unique barcode of a product</li>
     *                         <li>productName - full name of the product</li>
     *                         <li>nameStartsWith - the name the product starts with</li>
     *                         <li>nameContains - the text the product name contains</li>
     *                         <li>descriptionStartsWith - the description the product starts with</li>
     *                         <li>descriptionContains - the text the description contains</li>
     *                         <li>stockQuantity - exact quantity</li>
     *                         <li>stockQuantityMin - minimal stock quantity</li>
     *                         <li>stockQuantityMax - maximum stock quantity</li>
     *                         <li>price - exact price</li>
     *                         <li>priceMin - minimal price</li>
     *                         <li>priceMax - maximum price</li>
     *                       </ul>
     *
     * @return a HATEOAS {@link CollectionModel} of {@link ProductDto} wrapped in {@link EntityModel},
     *         each enriched with hypermedia links such as self and collection navigation links
     *
     * @see ProductSearchCriteria
     * @see ProductService#searchProductsByCriteria(ProductSearchCriteria)
     * @see ProductModelAssembler
     */
    @GetMapping("/search")
    public CollectionModel<EntityModel<ProductDto>> getCityByCriteria(@RequestBody ProductSearchCriteria searchCriteria) {
        List<ProductDto> results = productService.searchProductsByCriteria(searchCriteria);

        return productAssembler.toCollectionModel(results);
    }
}
