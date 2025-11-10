package dev.tamal.example.demovalidationspring.controller;

import dev.tamal.example.demovalidationspring.model.Customer;
import dev.tamal.example.demovalidationspring.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/")
    ResponseEntity<Customer> saveCustomer(@RequestBody @Validated Customer customer) {
        return ResponseEntity.ok().body(customerService.saveCustomer(customer));
    }

    @GetMapping("/")
    ResponseEntity<List<Customer>> getAllCustomer() {
        return ResponseEntity.ok().body(customerService.getAllCustomer());
    }

    @GetMapping("/{id}")
    ResponseEntity<Customer> getCustomerById(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(customerService.getCustomerById(id));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Object> deleteCustomerById(@PathVariable("id") Long id) {
        customerService.deleteCustomerById(id);
        return ResponseEntity.noContent().build();
    }
}
