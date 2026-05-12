package learning.projects.java_ecommerce.location.mapper;

import org.springframework.stereotype.Component;

import learning.projects.java_ecommerce.location.dto.StreetDto;
import learning.projects.java_ecommerce.location.model.Street;

@Component
public class StreetMapper {

    public static StreetDto toDto(Street street){
        return new StreetDto(street.getId(), street.getStreetName(), street.getCity().getId());
    }
}
