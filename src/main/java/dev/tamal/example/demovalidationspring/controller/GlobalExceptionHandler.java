package dev.tamal.example.demovalidationspring.controller;


import dev.tamal.example.demovalidationspring.exception.CustomerException;
import dev.tamal.example.demovalidationspring.model.CustomError;
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
    public ResponseEntity<String> handleMyCustomException(Exception ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<CustomError> handleGenericException(BindException ex) {
        return new ResponseEntity<>(CustomError.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .code("SI-500")
                .message("Invalid argument exception")
                .detail(extractDetails(ex))
                .build(),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @ExceptionHandler(CustomerException.class)
    public ResponseEntity<CustomError> handleCustomerException(CustomerException ex) {
        return new ResponseEntity<>(CustomError.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .code(ex.getCode())
                .message(ex.getMessage())
                .build(),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    private String extractDetails(Exception ex) {
        var list = new ArrayList<String>();
        if (ex instanceof MethodArgumentNotValidException notValidException) {
            notValidException.getBindingResult().getAllErrors().forEach(error -> {
                        var fieldError = (FieldError) error;
                        list.add(fieldError.getField() + " " + fieldError.getDefaultMessage());
                    }
            );
        }
        return String.join(";", list);
    }
}
