package dev.tamal.example.demovalidationspring.service;

import dev.tamal.example.demovalidationspring.entity.CustomerEntity;

import java.util.List;

public interface CustomerService {
    CustomerEntity getCustomerById(Long id);

    List<CustomerEntity> getAllCustomer();

    CustomerEntity saveCustomer(CustomerEntity customerEntity);

    CustomerEntity updateCustomer(CustomerEntity customerEntity);

    void deleteCustomer(CustomerEntity customerEntity);

    void deleteCustomerById(Long id);
}
