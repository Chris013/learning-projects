package learning.projects.java_ecommerce.customer.dto;

import java.util.List;

import learning.projects.java_ecommerce.customer.model.CustomerId;

/**
 * Ein Record für die vollständige Kundenansicht inklusive Adressen.
 */
public record CustomerDto(
    CustomerId id,
    String firstName,
    String lastName,
    String email,
    List<CustomerAddressDto> addresses // Die Liste der verknüpften Adressen
) { }
