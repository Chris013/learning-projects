package learning.projects.java_ecommerce.customer.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import learning.projects.java_ecommerce.customer.dto.CustomerAddressDto;
import learning.projects.java_ecommerce.customer.dto.CustomerDto;
import learning.projects.java_ecommerce.customer.model.Customer;
import learning.projects.java_ecommerce.customer.model.CustomerAddress;

@Component
public class CustomerMapper {

    /**
     * Wandelt eine Customer-Entität in ein CustomerDto um.
     */
    public static CustomerDto toDto(Customer customer) {
        if (customer == null) return null;

        List<CustomerAddressDto> addressDtos = customer.getCustomerAdresses().stream()
            .map(CustomerAddressMapper::toDto)
            .toList();

        return new CustomerDto(
            customer.getCustomerId(),
            customer.getFirstName(),
            customer.getLastName(),
            customer.getEmail(),
            addressDtos
        );
    }

    public static Customer toEntity(CustomerDto customerDto) {
        Customer customer = new Customer();

        customer.setCustomerId(customerDto.id());
        customer.setFirstName(customerDto.firstName());
        customer.setLastName(customerDto.lastName());
        customer.setEmail(customerDto.email());

        List<CustomerAddress> addresses = customerDto.addresses().stream()
        .map(CustomerAddressMapper::toEntity)
        .toList();

        customer.setCustomerAdresses(addresses);

        return customer;
    }

}
