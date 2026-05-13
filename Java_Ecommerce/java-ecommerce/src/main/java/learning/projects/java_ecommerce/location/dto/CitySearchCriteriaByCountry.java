package learning.projects.java_ecommerce.location.dto;

public record CitySearchCriteriaByCountry(
    Long countryId,
    String countryIsoCode,
    String countryName,
    String cityNameStartsWith
) {}
