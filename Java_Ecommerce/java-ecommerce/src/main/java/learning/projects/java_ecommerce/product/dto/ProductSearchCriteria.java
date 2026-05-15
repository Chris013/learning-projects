package learning.projects.java_ecommerce.product.dto;

import java.math.BigDecimal;

public record ProductSearchCriteria(    
    String barcodeStartsWith,
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