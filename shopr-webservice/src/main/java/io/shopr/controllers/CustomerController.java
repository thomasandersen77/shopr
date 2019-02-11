package io.shopr.controllers;

import io.shopr.dto.CustomerListDto;
import io.shopr.repositories.api.CustomerRepository;
import io.shopr.repositories.domain.Customer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {
    final CustomerRepository repository;

    public CustomerController(CustomerRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/customer")
    public Customer createCustomer(@RequestBody Customer customer) {
        return repository.save(customer);
    }

    @GetMapping("/customer")
    public CustomerListDto getCustomers(){
        return new CustomerListDto(repository.findAll());
    }
}
