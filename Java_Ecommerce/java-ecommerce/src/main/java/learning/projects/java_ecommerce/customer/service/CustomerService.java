package learning.projects.java_ecommerce.customer.service;

import java.util.List;
import java.util.UUID;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import learning.projects.java_ecommerce.common.exception.BadRequestException;
import learning.projects.java_ecommerce.customer.dto.CustomerAddressRequestDto;
import learning.projects.java_ecommerce.customer.dto.CustomerCreateRequestDto;
import learning.projects.java_ecommerce.customer.dto.CustomerResponseDto;
import learning.projects.java_ecommerce.customer.exception.CustomerNotFoundException;
import learning.projects.java_ecommerce.customer.mapper.CustomerAddressMapper;
import learning.projects.java_ecommerce.customer.mapper.CustomerMapper;
import learning.projects.java_ecommerce.customer.model.Customer;
import learning.projects.java_ecommerce.customer.model.CustomerAddress;
import learning.projects.java_ecommerce.customer.model.CustomerId;
import learning.projects.java_ecommerce.customer.repo.CustomerRepository;
import learning.projects.java_ecommerce.location.exception.CityNotFoundException;
import learning.projects.java_ecommerce.location.exception.CountryNotFoundException;
import learning.projects.java_ecommerce.location.exception.HouseNumberNotFoundException;
import learning.projects.java_ecommerce.location.exception.StreetNotFoundException;
import learning.projects.java_ecommerce.location.model.City;
import learning.projects.java_ecommerce.location.model.Country;
import learning.projects.java_ecommerce.location.model.HouseNumber;
import learning.projects.java_ecommerce.location.model.Street;
import learning.projects.java_ecommerce.location.repo.CityRepository;
import learning.projects.java_ecommerce.location.repo.CountryRepository;
import learning.projects.java_ecommerce.location.repo.HouseNumberRepository;
import learning.projects.java_ecommerce.location.repo.StreetRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor //It looks for any field in the class that is marked as final (or @NonNull) and automatically generates a constructor that accepts those fields as arguments.
public class CustomerService {

    private final CustomerRepository customerRepo;
    private final CountryRepository countryRepo;
    private final CityRepository cityRepo;
    private final StreetRepository streetRepo;
    private final HouseNumberRepository houseNumberRepo;

    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED) //READ_COMMITTED is the default
    public CustomerResponseDto getCustomerById(UUID id) {
        
        CustomerId customerId = new CustomerId(id);

        Customer customer = customerRepo
                .findByIdWithFullAddress(customerId)
                .orElseThrow(() -> new CustomerNotFoundException(id));

        return CustomerMapper.toDto(customer);
    }

    @Transactional
    public CustomerResponseDto createCustomer(CustomerCreateRequestDto dto) {

        Customer customer = Customer.builder()
                .customerId(new CustomerId(UUID.randomUUID()))
                .firstName(dto.firstName())
                .lastName(dto.lastName())
                .userName(dto.userName())
                .email(dto.email())
                .build();

        List<CustomerAddress> addresses = dto.addresses()
                .stream()
                .map(addressDto -> createAddressEntity(customer, addressDto))
                .toList();

        customer.setCustomerAdresses(addresses);

        Customer saved = customerRepo.save(customer);

        return CustomerMapper.toDto(saved);
    }

    private CustomerAddress createAddressEntity(Customer customer, CustomerAddressRequestDto dto) {

        Country country = countryRepo.findById(dto.countryId())
                .orElseThrow(() -> new CountryNotFoundException(dto.countryId()));

        City city = cityRepo.findById(dto.cityId())
                .orElseThrow(() -> new CityNotFoundException(dto.cityId().value()));

        Street street = streetRepo.findById(dto.streetId())
                .orElseThrow(() -> new StreetNotFoundException(dto.streetId().value()));

        HouseNumber houseNumber = houseNumberRepo.findById(dto.houseNumberId())
                .orElseThrow(() -> new HouseNumberNotFoundException(dto.houseNumberId().value()));

        return CustomerAddressMapper.toEntity(
                customer,
                country,
                city,
                street,
                houseNumber,
                dto.addressType()
        );
    }

    public void deleteCustomerById(UUID id) {

       CustomerId customerId = new CustomerId(id);
        
        try {
            customerRepo.deleteById(customerId);
        } catch (EmptyResultDataAccessException e) {
            throw new CustomerNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            // Handles database constraint issues (e.g., product is used in an order)
            throw new BadRequestException("Cannot delete product because of data integrity");
        }
    }
}
