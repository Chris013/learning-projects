package learning.projects.java_ecommerce.customer.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import learning.projects.java_ecommerce.customer.dto.CustomerDto;
import learning.projects.java_ecommerce.customer.exception.CustomerNotFoundException;
import learning.projects.java_ecommerce.customer.mapper.CustomerMapper;
import learning.projects.java_ecommerce.customer.model.Customer;
import learning.projects.java_ecommerce.customer.model.CustomerId;
import learning.projects.java_ecommerce.customer.repo.CustomerRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor //It looks for any field in the class that is marked as final (or @NonNull) and automatically generates a constructor that accepts those fields as arguments.
public class CustomerService {

    private final CustomerRepository customerRepo;

    @Transactional // Ensures database integrity
    public Customer createNewCustomer(String name, String email) {
        Customer customer = Customer.builder()
            .customerId(new CustomerId(UUID.randomUUID()))
            .userName(name)
            .email(email)
            .build();

        return customerRepo.save(customer);
    }

    /*@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED) //READ_COMMITTED is the default
    public Customer getCustomer( id) {
        return customerRepo.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Customer not found"));
    }*/

    @Transactional(readOnly = true)
    public CustomerDto getCustomerWithFullProfile(UUID id) {

        CustomerId customerId = new CustomerId(id);
        // Hier rufen wir die "teure" Abfrage auf
        // Dank JOIN FETCH sind die Adressen jetzt im Speicher.
        // Das Mapping in das DTO verursacht KEINE weiteren SQL-Abfragen.
        return customerRepo.findByIdWithFullAddress(customerId)
            .map(CustomerMapper::toDto)
            .orElseThrow(() -> new CustomerNotFoundException(id));
    }

    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED) //READ_COMMITTED is the default
    public CustomerDto getCustomerById(UUID id) {
        
        CustomerId customerId = new CustomerId(id);
        
        return customerRepo.findById(customerId)
        .map(CustomerMapper::toDto)
        .orElseThrow(() -> new CustomerNotFoundException(id));
    }
}
