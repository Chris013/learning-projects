package learning.projects.java_ecommerce.product.model;

import java.io.Serializable;
import java.util.UUID;

public record ProductId(UUID value) implements Serializable {};
