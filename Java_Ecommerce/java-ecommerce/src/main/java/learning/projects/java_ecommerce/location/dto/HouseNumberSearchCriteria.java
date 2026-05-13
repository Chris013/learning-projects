package learning.projects.java_ecommerce.location.dto;

import java.util.UUID;

public record HouseNumberSearchCriteria(
    UUID streetId,
    String streetName,
    String houseNumber
) {}
