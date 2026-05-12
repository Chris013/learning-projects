package learning.projects.java_ecommerce.location.dto;

import learning.projects.java_ecommerce.location.model.CityId;
import learning.projects.java_ecommerce.location.model.StreetId;

public record StreetDto(
    StreetId streetId,
    String name,
    CityId cityId
) {}
