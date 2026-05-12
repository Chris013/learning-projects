package learning.projects.java_ecommerce.location.dto;

import learning.projects.java_ecommerce.location.model.HouseNumberId;
import learning.projects.java_ecommerce.location.model.StreetId;

public record HouseNumberDto(
    HouseNumberId houseNumberId,
    String houseNumber,
    StreetId streetId
) {}
