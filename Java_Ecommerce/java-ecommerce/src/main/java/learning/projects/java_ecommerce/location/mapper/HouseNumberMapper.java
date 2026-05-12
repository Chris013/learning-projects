package learning.projects.java_ecommerce.location.mapper;

import org.springframework.stereotype.Component;

import learning.projects.java_ecommerce.location.dto.HouseNumberDto;
import learning.projects.java_ecommerce.location.model.HouseNumber;

@Component
public class HouseNumberMapper {

    public static HouseNumberDto toDto(HouseNumber houseNumber) {
        return new HouseNumberDto(houseNumber.getId(), houseNumber.getHouseNumber(), houseNumber.getStreet().getId());
    }
}
