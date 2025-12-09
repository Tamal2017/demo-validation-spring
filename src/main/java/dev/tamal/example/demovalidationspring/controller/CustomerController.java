package dev.tamal.example.demovalidationspring.controller;

import dev.tamal.example.demovalidationspring.dto.Customer;
import dev.tamal.example.demovalidationspring.service.CustomerService;
import dev.tamal.example.demovalidationspring.validator.CustomerValidator1;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindException;
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
    private final CustomerValidator1 customerValidator;

    @PostMapping("/")
    ResponseEntity<Customer> saveCustomer(@RequestBody @Valid Customer customer) throws BindException {
        // run custom validator in addition to @Valid
        BeanPropertyBindingResult errors = new BeanPropertyBindingResult(customer, "customer");
        customerValidator.validate(customer, errors);
        if (errors.hasErrors()) {
            throw new BindException(errors); // handled by Spring MVC as a 400 with validation details
        }
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
