package learning.projects.java_ecommerce.location.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import learning.projects.java_ecommerce.customer.dto.StreetLookupDto;
import learning.projects.java_ecommerce.customer.dto.StreetLookupView;
import learning.projects.java_ecommerce.location.model.CityId;
import learning.projects.java_ecommerce.location.model.Street;
import learning.projects.java_ecommerce.location.model.StreetId;

@Repository
public interface AddressRepository extends JpaRepository<Street, StreetId>{

    // 'Address' refers to the Class, 'streetName' refers to the Field
    @Query("SELECT a FROM Address a WHERE a.streetName LIKE %:streetName%")
    List<Street> searchByStreet(@Param("streetPart") String streetName);

    @Query("SELECT a FROM Address a JOIN FETCH a.city WHERE a.postalCode = :postCode")
    List<Street> findAddressesWithCity(@Param("postCode") String postCode);

    @Query("SELECT new StreetLookupDto(a.id, a.streetName) " + "FROM Address a WHERE a.city.id = :cityId")
    List<StreetLookupDto> getDropdownData(@Param("cityId") CityId cityId);

    //SpEL (Spring Expression Language). #{#entityName} automatically resolves to the name of the entity the repository manages.
    //@Query("SELECT e FROM #{#entityName} e WHERE e.active = true")
    //List<T> findAllActive();

    // Spring sees the return type is an interface and applies the "View" logic
    List<StreetLookupView> findByCityId(CityId cityId);
}
