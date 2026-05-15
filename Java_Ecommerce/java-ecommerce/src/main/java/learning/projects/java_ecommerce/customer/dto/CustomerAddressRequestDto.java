package learning.projects.java_ecommerce.customer.dto;

import learning.projects.java_ecommerce.customer.model.AddressType;
import learning.projects.java_ecommerce.location.model.CityId;
import learning.projects.java_ecommerce.location.model.HouseNumberId;
import learning.projects.java_ecommerce.location.model.StreetId;

public record CustomerAddressRequestDto(
    Long countryId,
    CityId cityId,
    StreetId streetId,
    HouseNumberId houseNumberId,
    AddressType addressType
) {}
