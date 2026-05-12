package learning.projects.java_ecommerce.location.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import learning.projects.java_ecommerce.location.model.Country;

public interface CountryRepository extends JpaRepository<Country, Long> {

}
