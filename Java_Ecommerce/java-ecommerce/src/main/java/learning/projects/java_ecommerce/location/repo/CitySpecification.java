package learning.projects.java_ecommerce.location.repo;

import org.springframework.data.jpa.domain.Specification;

import learning.projects.java_ecommerce.location.model.City;
import learning.projects.java_ecommerce.location.model.City_;
import learning.projects.java_ecommerce.location.model.Country_;

public class CitySpecification {

    public static Specification<City> hasCountryId(Long countryId){
        return (root, query, cb) ->
                countryId == null ? null : cb.equal(root.get(City_.country).get(Country_.id), countryId);
    }

    public static Specification<City> hasCountryIsoCode(String countryIsoCode){
        return (root, query, cb) -> countryIsoCode == null ? null : cb.equal(root.get(City_.country).get(Country_.countryCode), countryIsoCode);
    }

    /*public static Specification<City> hasMinPopulation(Integer minPopulation) {
        return (root, query, cb) -> minPopulation == null ? null : cb.greaterThanOrEqualTo(root.get("population"), minPopulation);
    }*/

    public static Specification<City> nameStartsWith(String nameStartsWith) {
        return (root, query, cb) -> nameStartsWith == null ? null : cb.like(cb.lower(root.get(City_.name)), nameStartsWith.toLowerCase() + "%");
    }

    public static Specification<City> hasCountryName(String countryName) {
        return (root, query, cb) -> countryName == null ? null : cb.equal(root.get(City_.name), countryName);
    }

}
