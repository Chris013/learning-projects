package learning.projects.java_ecommerce.customer.dto;

import java.util.List;

import learning.projects.java_ecommerce.customer.model.CustomerId;

public record CustomerResponseDto(
        CustomerId id,
        String firstName,
        String lastName,
        String userName,
        String email,
        List<CustomerAddressResponseDto> addresses
) {}
