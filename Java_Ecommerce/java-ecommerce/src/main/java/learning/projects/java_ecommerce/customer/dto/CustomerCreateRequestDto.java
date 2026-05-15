package learning.projects.java_ecommerce.customer.dto;

import java.util.List;

public record CustomerCreateRequestDto(
        String firstName,
        String lastName,
        String userName,
        String email,
        List<CustomerAddressRequestDto> addresses
) {}
