package learning.projects.java_ecommerce.location.dto;

import java.util.UUID;

public record StreetSearchCriteria(
    UUID cityId,
    String cityName,
    String zipCode,
    String streetNameStartsWith
) {}
