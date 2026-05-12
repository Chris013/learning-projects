package learning.projects.java_ecommerce.customer.dto;

import learning.projects.java_ecommerce.customer.model.AddressType;
import learning.projects.java_ecommerce.location.model.StreetId;

public record CustomerAddressDto(
    StreetId addressId,
    String streetName,
    String houseNumber,
    String zipCode,
    String city,
    AddressType addressType // z.B. "RECHNUNG" oder "LIEFERUNG" aus der Brückentabelle
) { }
