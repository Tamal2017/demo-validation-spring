package dev.tamal.example.demovalidationspring.controller;

import dev.tamal.example.demovalidationspring.mapper.CustomerMapper;
import dev.tamal.example.demovalidationspring.model.Customer;
import dev.tamal.example.demovalidationspring.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/customers")
@AllArgsConstructor
public class CustomerController {
    private final CustomerService customerService;
    private final CustomerMapper mapper;

    @PostMapping("/")
    Customer saveCustomer(@RequestBody @Validated Customer customer) {
        return mapper.toDto(customerService.saveCustomer(mapper.toEntity(customer)));
    }

    @GetMapping("/")
    List<Customer> getAllCustomer() {
        return mapper.toList(customerService.getAllCustomer());
    }

    @GetMapping("/{id}")
    Customer getCustomerById(@PathVariable("id") Long id) {
        return mapper.toDto(customerService.getCustomerById(id));
    }

    @DeleteMapping("/{id}")
    void deleteCustomerById(@PathVariable("id") Long id) {
        customerService.deleteCustomerById(id);
    }
}
