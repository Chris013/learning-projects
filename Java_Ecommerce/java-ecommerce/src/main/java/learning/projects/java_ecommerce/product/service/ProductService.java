package learning.projects.java_ecommerce.product.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import learning.projects.java_ecommerce.product.dto.ProductDto;
import learning.projects.java_ecommerce.product.exception.ProductNotFoundException;
import learning.projects.java_ecommerce.product.mapper.ProductMapper;
import learning.projects.java_ecommerce.product.model.Product;
import learning.projects.java_ecommerce.product.model.ProductId;
import learning.projects.java_ecommerce.product.repo.ProductRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;

    public List<ProductDto> getAllProducts() {
        return repository.findAll().stream()
            .map(ProductMapper::toDto)
            .toList();
    }

    public ProductDto getProductById(UUID id) {

        ProductId productId = new ProductId(id);

        return repository.findById(productId)
            .map(ProductMapper::toDto)
            .orElseThrow(() -> new ProductNotFoundException("Product not found: " + id));
    }


    public ProductDto createProduct(ProductDto dto) {
        try {
            Product saved = repository.save(
                ProductMapper.toEntity(dto)
            );

            return ProductMapper.toDto(saved);

        } catch (Exception e) {
            throw new RuntimeException("Could not save product", e);
        }
    }
}
