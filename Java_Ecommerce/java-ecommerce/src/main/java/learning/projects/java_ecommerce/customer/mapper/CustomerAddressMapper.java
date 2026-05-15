package learning.projects.java_ecommerce.customer.mapper;

import learning.projects.java_ecommerce.customer.dto.CustomerAddressResponseDto;
import learning.projects.java_ecommerce.customer.model.AddressType;
import learning.projects.java_ecommerce.customer.model.Customer;
import learning.projects.java_ecommerce.customer.model.CustomerAddress;
import learning.projects.java_ecommerce.location.model.City;
import learning.projects.java_ecommerce.location.model.Country;
import learning.projects.java_ecommerce.location.model.HouseNumber;
import learning.projects.java_ecommerce.location.model.Street;

public class CustomerAddressMapper {

    private CustomerAddressMapper() {}

    public static CustomerAddress toEntity(
            Customer customer,
            Country country,
            City city,
            Street street,
            HouseNumber houseNumber,
            AddressType addressType
    ) {

        return CustomerAddress.builder()
                .customer(customer)
                .country(country)
                .city(city)
                .street(street)
                .houseNumber(houseNumber)
                .addressType(addressType)
                .build();
    }

    public static CustomerAddressResponseDto toDto(CustomerAddress entity) {

        Country country = entity.getCountry();
        City city = entity.getCity();
        Street street = entity.getStreet();
        HouseNumber houseNumber = entity.getHouseNumber();

        return new CustomerAddressResponseDto(
                entity.getId(),
                country.getId(),
                country.getCountryName(),
                country.getCountryCode(),
                city.getId(),
                city.getName(),
                city.getZipCode(),
                street.getId(),
                street.getStreetName(),
                houseNumber.getId(),
                houseNumber.getHouseNumber(),
                entity.getAddressType()
        );
    }

}
