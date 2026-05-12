package learning.projects.java_ecommerce.product.dto;

import java.math.BigDecimal;

import learning.projects.java_ecommerce.product.model.ProductId;

public record ProductDto(    
    ProductId productId,
    String barcode,
    String name,
    String description,
    int stockQuantity,
    BigDecimal price
) { }
