package learning.projects.java_ecommerce.customer.web;

import java.util.UUID;

import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import learning.projects.java_ecommerce.customer.assembler.CustomerModelAssembler;
import learning.projects.java_ecommerce.customer.dto.CustomerDto;
import learning.projects.java_ecommerce.customer.service.CustomerService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    private final CustomerModelAssembler customerAssembler;

    @GetMapping("/{id}")
    public EntityModel<CustomerDto> getCustomerById(@PathVariable UUID id) {
        return customerAssembler.toModel(customerService.getCustomerById(id));
    }

}
