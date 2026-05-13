package learning.projects.java_ecommerce.location.repo;

import java.util.UUID;

import org.springframework.data.jpa.domain.Specification;

import learning.projects.java_ecommerce.location.model.HouseNumber;
import learning.projects.java_ecommerce.location.model.HouseNumber_;
import learning.projects.java_ecommerce.location.model.Street_;

public class HouseNumberSpecification {
    
    public static Specification<HouseNumber> hasStreetId(UUID id){
        return (root, query, builder) -> id == null ? null : builder.equal(root.get(HouseNumber_.street).get(Street_.id), query);
    }
    
    public static Specification<HouseNumber> hasStreetName(String streetName){
        return (root, query, builder) -> streetName == null ? null : builder.equal(root.get(HouseNumber_.street).get(Street_.streetName), query);
    }

    public static Specification<HouseNumber> houseNumberStartsWith(String houseNumber){
        return (root, query, builder) -> houseNumber == null ? null : builder.like(builder.lower(root.get(HouseNumber_.houseNumber)), houseNumber.toLowerCase() + "%");
    }
}
