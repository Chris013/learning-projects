package learning.projects.java_ecommerce.customer.mapper;

import learning.projects.java_ecommerce.customer.dto.CustomerAddressDto;
import learning.projects.java_ecommerce.customer.model.CustomerAddress;
import learning.projects.java_ecommerce.location.model.City;
import learning.projects.java_ecommerce.location.model.HouseNumber;
import learning.projects.java_ecommerce.location.model.Street;

public class CustomerAddressMapper {

    /**
     * Private Hilfsmethode für die Adress-Konvertierung
     */
    public static CustomerAddressDto toDto(CustomerAddress customerAdr) {
        Street addr = customerAdr.getStreet();
        City city = addr.getCity();
        HouseNumber houseNum = customerAdr.getHouseNumber();
        return new CustomerAddressDto(
            customerAdr.getId(),
            addr.getId(),
            addr.getStreetName(),
            houseNum.getHouseNumber(),
            city.getZipCode(),
            city.getName(),
            customerAdr.getAddressType() // Das Feld aus der Verknüpfungstabelle
        );
    }

    public static CustomerAddress toEntity(CustomerAddressDto customerAddressDto){
        
        CustomerAddress customerAddress = new CustomerAddress();

        customerAddress.setId(customerAddressDto.customerAddressId());
        //TODO alles andere noch korrigieren und fertig machen

        return customerAddress;
    }

}
