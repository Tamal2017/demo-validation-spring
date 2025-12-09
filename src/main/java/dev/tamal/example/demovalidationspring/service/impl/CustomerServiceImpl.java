package dev.tamal.example.demovalidationspring.service.impl;

import dev.tamal.example.demovalidationspring.exception.CustomerException;
import dev.tamal.example.demovalidationspring.mapper.CustomerMapper;
import dev.tamal.example.demovalidationspring.dto.Customer;
import dev.tamal.example.demovalidationspring.repository.CustomerRepository;
import dev.tamal.example.demovalidationspring.service.CustomerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper mapper;

    @Override
    public Customer getCustomerById(Long id) {
        log.info("Find customer by id : {}", id);
        var customerEntity = customerRepository.findById(id).orElseThrow(() -> new CustomerException("SI-404", "Customer not found with id %s".formatted(id), null));
        return mapper.toDto(customerEntity);
    }

    @Override
    public List<Customer> getAllCustomer() {
        log.info("Get all customer!");
        return mapper.toList(customerRepository.findAll());
    }

    @Override
    public Customer saveCustomer(Customer customer) {
        log.info("Save customer : {}", customer);
        return mapper.toDto(customerRepository.save(mapper.toEntity(customer)));
    }

    @Override
    public Customer updateCustomer(Customer customerEntity) {
        log.info("Updating customer : {}", customerEntity);
        return mapper.toDto(customerRepository.save(mapper.toEntity(customerEntity)));
    }

    @Override
    public void deleteCustomer(Customer customerEntity) {
        log.info("Deleting customer : {}", customerEntity);
        customerRepository.delete(mapper.toEntity(customerEntity));
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
