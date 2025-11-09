package dev.tamal.example.demovalidationspring.service;

import dev.tamal.example.demovalidationspring.entity.CustomerEntity;
import dev.tamal.example.demovalidationspring.exception.CustomerException;
import dev.tamal.example.demovalidationspring.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public CustomerEntity getCustomerById(Long id) {
        log.info("Find customer by id : {}", id);
        return customerRepository.findById(id).orElseThrow(() -> new CustomerException("SI-404", "Customer not found with id %s".formatted(id), null));
    }

    @Override
    public List<CustomerEntity> getAllCustomer() {
        log.info("Get all customer!");
        return customerRepository.findAll();
    }

    @Override
    public CustomerEntity saveCustomer(CustomerEntity customerEntity) {
        log.info("Save customer : {}", customerEntity);
        return customerRepository.save(customerEntity);
    }

    @Override
    public CustomerEntity updateCustomer(CustomerEntity customerEntity) {
        log.info("Updating customer : {}", customerEntity);
        return customerRepository.save(customerEntity);
    }

    @Override
    public void deleteCustomer(CustomerEntity customerEntity) {
        log.info("Deleting customer : {}", customerEntity);
        customerRepository.delete(customerEntity);
    }

    @Override
    public void deleteCustomerById(Long id) {
        if (!customerRepository.existsById(id)) {
            throw new CustomerException("SI-404", "Customer not found with id %s".formatted(id), null);
        }
        customerRepository.deleteById(id);
        log.info("Customer with id %s was deleted".formatted(id));
    }
}
