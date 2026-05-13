package learning.projects.java_ecommerce.location.dto;

public record CitySearchCriteria(
    Long countryId,
    String countryIsoCode,
    String countryName,
    String cityNameStartsWith
) {}
