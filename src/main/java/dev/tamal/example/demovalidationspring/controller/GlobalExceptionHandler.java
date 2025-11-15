package dev.tamal.example.demovalidationspring.controller;


import dev.tamal.example.demovalidationspring.exception.CustomerException;
import dev.tamal.example.demovalidationspring.model.CustomError;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomError> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, HttpServletRequest request) {
        return new ResponseEntity<>(CustomError.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .code("SI-500")
                .message("Invalid argument exception")
                .detail(extractDetails(ex))
                .path(request.getRequestURI())
                .build(),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @ExceptionHandler(CustomerException.class)
    public ResponseEntity<CustomError> handleCustomerException(CustomerException ex, HttpServletRequest request) {
        return new ResponseEntity<>(CustomError.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .code(ex.getCode())
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .build(),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    private String extractDetails(MethodArgumentNotValidException ex) {
        var list = new ArrayList<String>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
                    var fieldError = (FieldError) error;
                    list.add(fieldError.getField() + " " + fieldError.getDefaultMessage());
                }
        );
        return String.join("; ", list);
    }
}
