package learning.projects.java_ecommerce.customer.dto;

import learning.projects.java_ecommerce.location.model.StreetId;

public interface StreetLookupView {
    StreetId getId();      // Matches the 'id' field in Street entity
    String getStreetName(); // Matches the 'streetName' field in Street entity
}
