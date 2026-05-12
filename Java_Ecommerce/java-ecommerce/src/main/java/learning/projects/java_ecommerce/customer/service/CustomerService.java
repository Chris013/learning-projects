package learning.projects.java_ecommerce.customer.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;
import learning.projects.java_ecommerce.customer.dto.CustomerDto;
import learning.projects.java_ecommerce.customer.mapper.CustomerMapper;
import learning.projects.java_ecommerce.customer.model.Customer;
import learning.projects.java_ecommerce.customer.model.CustomerId;
import learning.projects.java_ecommerce.customer.repo.CustomerRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor //It looks for any field in the class that is marked as final (or @NonNull) and automatically generates a constructor that accepts those fields as arguments.
public class CustomerService {

    private final CustomerRepository customerRepo;

    private final CustomerMapper customerMapper;

    @Transactional // Ensures database integrity
    public Customer createNewCustomer(String name, String email) {
        Customer customer = Customer.builder()
            .customerId(new CustomerId(UUID.randomUUID()))
            .userName(name)
            .email(email)
            .build();

        return customerRepo.save(customer);
    }

    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED) //READ_COMMITTED is the default
    public Customer getCustomer(CustomerId id) {
        return customerRepo.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Customer not found"));
    }

    @Transactional(readOnly = true)
    public CustomerDto getCustomerWithFullProfile(CustomerId id) {
        // Hier rufen wir die "teure" Abfrage auf
        // Dank JOIN FETCH sind die Adressen jetzt im Speicher.
        // Das Mapping in das DTO verursacht KEINE weiteren SQL-Abfragen.
        return customerRepo.findByIdWithFullAddress(id)
            .map(customerMapper::toDto)
            .orElseThrow(() -> new EntityNotFoundException());
    }

    public CustomerDto getCustomerById(UUID id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCustomerById'");
    }
}
