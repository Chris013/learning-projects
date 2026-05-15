package learning.projects.java_ecommerce.customer.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import learning.projects.java_ecommerce.customer.dto.CustomerAddressResponseDto;
import learning.projects.java_ecommerce.customer.dto.CustomerResponseDto;
import learning.projects.java_ecommerce.customer.model.Customer;

@Component
public class CustomerMapper {

    private CustomerMapper() {}

    public static CustomerResponseDto toDto(Customer customer) {

        List<CustomerAddressResponseDto> addresses = customer.getCustomerAdresses()
                .stream()
                .map(CustomerAddressMapper::toDto)
                .toList();

        return new CustomerResponseDto(
                customer.getCustomerId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getUserName(),
                customer.getEmail(),
                addresses
        );
    }

}
