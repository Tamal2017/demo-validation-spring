package dev.tamal.example.demovalidationspring.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomError {
    private int status;
    private String code;
    private String message;
    private String detail;
    private String path;
}
