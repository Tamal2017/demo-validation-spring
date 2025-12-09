package dev.tamal.example.demovalidationspring.validator;

import dev.tamal.example.demovalidationspring.dto.Customer;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class CustomerValidator implements ConstraintValidator<CustomerValid, Customer> {

    @Override
    public boolean isValid(Customer customer, ConstraintValidatorContext constraintValidatorContext) {

        // Check if the customer is an adult
        return customer.birthDate().isAfter(LocalDate.now()) && getAge(customer.birthDate()) >= 18;
    }

    private Long getAge(LocalDate birthDate) {
        return ChronoUnit.YEARS.between(birthDate, LocalDate.now());
    }
}
