package learning.projects.java_ecommerce.customer.dto;

import learning.projects.java_ecommerce.location.model.AddressId;

public interface StreetLookupView {
    AddressId getId();      // Matches the 'id' field in Address entity
    String getStreetName(); // Matches the 'streetName' field in Address entity
}
