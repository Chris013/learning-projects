package learning.projects.java_ecommerce.customer.dto;

import learning.projects.java_ecommerce.customer.model.AddressType;
import learning.projects.java_ecommerce.location.model.CityId;
import learning.projects.java_ecommerce.location.model.HouseNumberId;
import learning.projects.java_ecommerce.location.model.StreetId;

public record CustomerAddressResponseDto(
    Long customerAddressId,
    Long countryId,
    String countryName,
    String countryCode,
    CityId cityId,
    String cityName,
    String zipCode,
    StreetId streetId,
    String streetName,
    HouseNumberId houseNumberId,
    String houseNumber,
    AddressType addressType
) {}