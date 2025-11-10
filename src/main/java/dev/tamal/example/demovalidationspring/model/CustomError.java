package dev.tamal.example.demovalidationspring.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomError {
    int status;
    String code;
    String message;
    String detail;
    String path;
}
