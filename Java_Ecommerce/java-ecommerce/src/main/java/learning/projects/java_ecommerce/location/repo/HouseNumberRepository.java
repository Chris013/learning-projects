package learning.projects.java_ecommerce.location.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import learning.projects.java_ecommerce.location.model.HouseNumber;
import learning.projects.java_ecommerce.location.model.HouseNumberId;

public interface HouseNumberRepository extends JpaRepository<HouseNumber, HouseNumberId> {

}
