package dev.tamal.example.demovalidationspring.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record Customer(
        @NotBlank
        String firstName,
        String lastName,
        @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")
        String email,
        @NotBlank @Size(min = 6, max = 128, message = "must be between 6 and 128 characters!")
        String address,
        LocalDate birthDate
) {
}
