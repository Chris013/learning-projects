package learning.projects.java_ecommerce.customer.model;

import java.io.Serializable;
import java.util.UUID;

public record CustomerId(UUID value) implements Serializable {}
