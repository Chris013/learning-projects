package learning.projects.java_ecommerce.product.service;

import java.util.List;
import java.util.UUID;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import learning.projects.java_ecommerce.common.exception.BadRequestException;
import learning.projects.java_ecommerce.product.dto.ProductDto;
import learning.projects.java_ecommerce.product.dto.ProductSearchCriteria;
import learning.projects.java_ecommerce.product.exception.ProductDataValidationException;
import learning.projects.java_ecommerce.product.exception.ProductNotFoundException;
import learning.projects.java_ecommerce.product.mapper.ProductMapper;
import learning.projects.java_ecommerce.product.model.Product;
import learning.projects.java_ecommerce.product.model.ProductId;
import learning.projects.java_ecommerce.product.repo.ProductRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepo;

    public List<ProductDto> getAllProducts() {
        return productRepo.findAll().stream()
            .map(ProductMapper::toDto)
            .toList();
    }

    public ProductDto getProductById(UUID id) {

        ProductId productId = new ProductId(id);

        return productRepo.findById(productId)
            .map(ProductMapper::toDto)
            .orElseThrow(() -> new ProductNotFoundException(id));
    }


    public ProductDto createProduct(ProductDto dto) {
        try {
            Product saved = productRepo.save(
                ProductMapper.toEntity(dto)
            );

            return ProductMapper.toDto(saved);

        } catch (DataIntegrityViolationException e) {
            throw new ProductDataValidationException(dto);
        } catch (Exception e) {
            throw new RuntimeException("Unexpected error while saving product", e);
        }
    }

    public List<ProductDto> createAllProducts(List<ProductDto> products) {
        return products.stream().map(this::createProduct).toList();
    }

    public void deleteProductById(UUID id) throws BadRequestException {

        ProductId productId = new ProductId(id);
        
        try {
            productRepo.deleteById(productId);
        } catch (EmptyResultDataAccessException e) {
            // Handles the missing ID issue (mostly for Spring Boot 2.x)
            throw new ProductNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            // Handles database constraint issues (e.g., product is used in an order)
            throw new BadRequestException("Cannot delete product because of data integrity");
        }
    }

    public List<ProductDto> searchProductsByCriteria(ProductSearchCriteria searchCriteria) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'searchProductsByCriteria'");
    }

}
