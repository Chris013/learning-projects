package learning.projects.java_ecommerce.product.mapper;

import org.springframework.stereotype.Component;

import learning.projects.java_ecommerce.product.dto.ProductDto;
import learning.projects.java_ecommerce.product.model.Product;

@Component
public class ProductMapper {

    public static ProductDto toDto(Product product) {
        return new ProductDto(
                product.getProductId(),
                product.getBarcode(),
                product.getName(),
                product.getDescription(),
                product.getStockQuantity(),
                product.getPrice()
        );
    }

    public static Product toEntity(ProductDto dto) {
        Product product = new Product();

        product.setProductId(dto.productId());
        product.setBarcode(dto.barcode());
        product.setName(dto.name());
        product.setDescription(dto.description());
        product.setStockQuantity(dto.stockQuantity());
        product.setPrice(dto.price());

        return product;
    }
}
