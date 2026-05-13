package learning.projects.java_ecommerce.location.repo;

import java.util.UUID;

import org.springframework.data.jpa.domain.Specification;

import learning.projects.java_ecommerce.location.model.City_;
import learning.projects.java_ecommerce.location.model.Street;
import learning.projects.java_ecommerce.location.model.Street_;

public class StreetSpecification {

    public static Specification<Street> hasCityId(UUID id){
        return (root, query, builder) -> id == null ? null : builder.equal(root.get(Street_.city).get(City_.id), query);
    }

    public static Specification<Street> hasCityZipCode(String zipCode){
        return (root, query, builder) -> zipCode == null ? null : builder.equal(root.get(Street_.city).get(City_.zipCode), query);
    }

    public static Specification<Street> hasCityName(String cityName){
        return (root, query, builder) -> cityName == null ? null : builder.equal(root.get(Street_.city).get(City_.name), query);
    }

    public static Specification<Street> nameStartsWith(String nameStartsWith){
        return (root, query, builder) -> nameStartsWith == null ? null : builder.like(builder.lower(root.get(Street_.streetName)), nameStartsWith.toLowerCase() + "%");
    }

}
