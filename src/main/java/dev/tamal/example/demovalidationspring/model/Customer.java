package dev.tamal.example.demovalidationspring.model;

import java.time.LocalDate;

public record Customer(String firstName, String lastName, String email, String address, LocalDate birthDate) {
}
