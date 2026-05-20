package learning.projects.java_ecommerce.customer.web;

import java.util.UUID;

import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;
import learning.projects.java_ecommerce.customer.assembler.CustomerModelAssembler;
import learning.projects.java_ecommerce.customer.dto.CustomerCreateRequestDto;
import learning.projects.java_ecommerce.customer.dto.CustomerResponseDto;
import learning.projects.java_ecommerce.customer.service.CustomerService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
@Tag(name = "Customer Service", description = "APIs for managing customers")
public class CustomerController {

    private final CustomerService customerService;

    private final CustomerModelAssembler customerAssembler;

    /**
     * Retrieves a single customer by its unique identifier.
     *
     * <p>The response includes HATEOAS links such as:
     * <ul>
     *     <li>self link (GET /api/v1/customers/{id})</li>
     *     <li>link to customer collection (GET /api/v1/customers)</li>
     * </ul>
     *
     * @param id the unique id of the customer
     * @return the customer wrapped in an EntityModel with hypermedia links
     */
    @GetMapping("/{id}")
    public EntityModel<CustomerResponseDto> getCustomerById(@PathVariable UUID id) {
        return customerAssembler.toModel(customerService.getCustomerById(id));
    }

    /**
     * Creates a new Customer.
     *
     * <p>Returns the created customer with HATEOAS links.
     * The HTTP response includes:
     * <ul>
     *     <li>201 Created status</li>
     *     <li>Customer representation with self link</li>
     * </ul>
     *
     * @param dto the product data to create
     * @return created customer wrapped in EntityModel
     */
    @PostMapping
    public ResponseEntity<EntityModel<CustomerResponseDto>> createCustomer(@RequestBody CustomerCreateRequestDto dto) {

        CustomerResponseDto customer = customerService.createCustomer(dto);

        EntityModel<CustomerResponseDto> entityModel = customerAssembler.toModel(customer);

        return ResponseEntity.status(HttpStatus.CREATED).body(entityModel);
    }

    /**
     * Deletes a customer by its ID.
     *
     * <p>If the customer does not exist, a runtime exception is thrown
     * and handled by the global exception handler.
     *
     * <p>HTTP response:
     * <ul>
     *     <li>204 No Content on success</li>
     * </ul>
     *
     * @param id the UUID of the customer to delete
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable UUID id){
        customerService.deleteCustomerById(id);
        return ResponseEntity.noContent().build();
    }
}
