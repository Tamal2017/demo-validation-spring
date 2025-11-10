package dev.tamal.example.demovalidationspring.service;

import dev.tamal.example.demovalidationspring.model.Customer;

import java.util.List;

public interface CustomerService {
    Customer getCustomerById(Long id);

    List<Customer> getAllCustomer();

    Customer saveCustomer(Customer customer);

    Customer updateCustomer(Customer customer);

    void deleteCustomer(Customer customer);

    void deleteCustomerById(Long id);
}
