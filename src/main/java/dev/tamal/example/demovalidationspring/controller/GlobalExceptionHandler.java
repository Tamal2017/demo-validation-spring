package dev.tamal.example.demovalidationspring.controller;


import dev.tamal.example.demovalidationspring.dto.CustomError;
import dev.tamal.example.demovalidationspring.exception.CustomerException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class, BindException.class})
    public ResponseEntity<CustomError> handleMethodArgumentNotValidException(BindException ex, HttpServletRequest request) {
        return new ResponseEntity<>(CustomError.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .code("SI-500")
                .message("Invalid argument exception")
                .detail(extractDetails(ex))
                .path(request.getRequestURI())
                .build(),
                HttpStatus.BAD_REQUEST
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

    private String extractDetails(BindException ex) {
        var list = new ArrayList<String>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
                    var fieldError = (FieldError) error;
                    list.add(fieldError.getField() + " " + fieldError.getDefaultMessage());
                }
        );
        return String.join("; ", list);
    }
}
