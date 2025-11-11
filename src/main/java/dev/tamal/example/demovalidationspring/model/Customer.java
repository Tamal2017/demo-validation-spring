package dev.tamal.example.demovalidationspring.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

public record Customer(
        @NotBlank String firstName,
        String lastName,
        @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$") String email,
        @NotBlank @Min(6) String address,
        LocalDate birthDate
) {
}
