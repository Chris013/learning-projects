package learning.projects.java_ecommerce.location.dto;

import learning.projects.java_ecommerce.location.model.CityId;

public record CityDto(
    CityId cityId,
    String name,
    String zipCode,
    Long countryId
) {}
