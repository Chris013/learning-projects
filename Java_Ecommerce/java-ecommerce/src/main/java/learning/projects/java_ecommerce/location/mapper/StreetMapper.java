package learning.projects.java_ecommerce.location.mapper;

import learning.projects.java_ecommerce.location.dto.StreetDto;
import learning.projects.java_ecommerce.location.model.Street;

public class StreetMapper {

    public static StreetDto toDto(Street street){
        return new StreetDto(street.getId(), street.getStreetName(), street.getCity().getId());
    }
}
