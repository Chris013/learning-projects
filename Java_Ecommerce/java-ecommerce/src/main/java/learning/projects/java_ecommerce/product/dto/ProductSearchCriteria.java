package learning.projects.java_ecommerce.product.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductSearchCriteria(    
    UUID productId,
    String barcode,
    String productName,
    String nameStartsWith,
    String nameContains,
    String descriptionStartsWith,
    String descriptionContains,
    int stockQuantity,
    int stockQuantityMin,
    int stockQuantityMax,
    BigDecimal price,
    BigDecimal priceMin,
    BigDecimal priceMax
) {}