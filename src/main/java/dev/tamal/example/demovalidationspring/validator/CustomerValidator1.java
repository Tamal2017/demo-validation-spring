package dev.tamal.example.demovalidationspring.validator;

import dev.tamal.example.demovalidationspring.dto.Customer;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Component
public class CustomerValidator1 implements Validator {

    private static final int LEGAL_AGE = 18;

    @Override
    public boolean supports(Class<?> clazz) {
        return Customer.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Customer customer = (Customer) target;
        if (getAge(customer.birthDate()) <= LEGAL_AGE) {
            errors.rejectValue("birthDate", "NotValid", "The customer should be an adult!");
        }
    }

    private Long getAge(LocalDate birthDate) {
        return ChronoUnit.YEARS.between(birthDate, LocalDate.now());
    }
}
