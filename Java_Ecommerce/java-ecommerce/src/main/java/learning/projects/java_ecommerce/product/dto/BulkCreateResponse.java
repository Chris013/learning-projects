package learning.projects.java_ecommerce.product.dto;

import java.util.List;

import learning.projects.java_ecommerce.product.model.ProductId;

public record BulkCreateResponse(
    int createdCount,
    List<ProductId> createdIds
) {}
