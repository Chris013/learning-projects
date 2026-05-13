package learning.projects.java_ecommerce.customer.repo;

import org.springframework.stereotype.Repository;


import learning.projects.java_ecommerce.customer.model.Customer;
import learning.projects.java_ecommerce.customer.model.CustomerId;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, CustomerId>, JpaSpecificationExecutor<Customer>{

    /*@Modifying
    @Transactional
    @Query("UPDATE Customer c SET c.isVip = true WHERE c.email LIKE %:domain")
    int upgradeCustomersByDomain(@Param("domain") String domain);*/

    @Query("SELECT c FROM Customer c " +
           "LEFT JOIN FETCH c.customerAdresses ca " +
           "LEFT JOIN FETCH ca.street " +
           "LEFT JOIN FETCH ca.houseNumber " +
           "LEFT JOIN FETCH ca.city " +
           "LEFT JOIN FETCH ca.country " +
           "WHERE c.id = :id")
    Optional<Customer> findByIdWithFullAddress(@Param("id") CustomerId id);

}
