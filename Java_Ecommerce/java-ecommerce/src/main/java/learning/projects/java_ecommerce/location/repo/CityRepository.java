package learning.projects.java_ecommerce.location.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import learning.projects.java_ecommerce.location.model.City;
import learning.projects.java_ecommerce.location.model.CityId;

@Repository
public interface CityRepository extends JpaRepository<City, CityId>{

}
