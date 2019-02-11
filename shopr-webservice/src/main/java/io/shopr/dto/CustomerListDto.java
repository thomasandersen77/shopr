package io.shopr.dto;

import io.shopr.repositories.domain.Customer;

import java.util.Collections;
import java.util.List;

public class CustomerListDto {
    private List<Customer> customers = Collections.emptyList();
    private CustomerListDto(){}

    public CustomerListDto(List<Customer> customers) {
        this.customers = customers;
    }

    public List<Customer> getCustomers() {
        return customers;
    }
}
