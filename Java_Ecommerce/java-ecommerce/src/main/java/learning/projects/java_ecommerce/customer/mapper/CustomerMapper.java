package learning.projects.java_ecommerce.customer.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import learning.projects.java_ecommerce.customer.dto.CustomerAddressDto;
import learning.projects.java_ecommerce.customer.dto.CustomerDto;
import learning.projects.java_ecommerce.customer.model.Customer;
import learning.projects.java_ecommerce.customer.model.CustomerAddress;
import learning.projects.java_ecommerce.location.model.City;
import learning.projects.java_ecommerce.location.model.HouseNumber;
import learning.projects.java_ecommerce.location.model.Street;

@Component
public class CustomerMapper {

    /**
     * Wandelt eine Customer-Entität in ein CustomerDto um.
     */
    public CustomerDto toDto(Customer customer) {
        if (customer == null) return null;

        List<CustomerAddressDto> addressDtos = customer.getCustomerAdresses().stream()
            .map(this::toAddressDto)
            .toList();

        return new CustomerDto(
            customer.getCustomerId(),
            customer.getFirstName(),
            customer.getLastName(),
            customer.getEmail(),
            addressDtos
        );
    }

    /**
     * Private Hilfsmethode für die Adress-Konvertierung
     */
    private CustomerAddressDto toAddressDto(CustomerAddress customerAdr) {
        Street addr = customerAdr.getStreet();
        City city = addr.getCity();
        HouseNumber houseNum = customerAdr.getHouseNumber();
        return new CustomerAddressDto(
            addr.getId(),
            addr.getStreetName(),
            houseNum.getHouseNumber(),
            city.getZipCode(),
            city.getName(),
            customerAdr.getAddressType() // Das Feld aus der Verknüpfungstabelle
        );
    }
}
