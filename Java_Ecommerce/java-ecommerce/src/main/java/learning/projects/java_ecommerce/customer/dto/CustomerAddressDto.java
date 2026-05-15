package learning.projects.java_ecommerce.customer.dto;

import learning.projects.java_ecommerce.customer.model.AddressType;
import learning.projects.java_ecommerce.location.model.CityId;
import learning.projects.java_ecommerce.location.model.HouseNumberId;
import learning.projects.java_ecommerce.location.model.StreetId;

public record CustomerAddressDto(
    Long customerAddressId,
    StreetId addressId,
    String streetName,
    HouseNumberId houseNumberId,
    String houseNumber,
    CityId cityId,
    String zipCode,
    String cityName,
    Long countryId,
    String countryIso,
    String countryName,
    AddressType addressType // z.B. "RECHNUNG" oder "LIEFERUNG" aus der Brückentabelle
) { }
