package com.main.blogs.exception;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Exception handler for resources not found
    @ExceptionHandler(value = {ResourceNotFoundException.class})
    public ResponseEntity<Object> handleResourceNotFoundException(
            ResourceNotFoundException ex) {

        ErrorResponse errorResponse = new ErrorResponse(
                List.of(ex.getMessage()),
                ex,
                HttpStatus.NOT_FOUND,
                404
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    // Exception handler for invalid parameters
    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<Object> handleValidatorExceptions(
            MethodArgumentNotValidException ex) {
        List<String> message = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(err -> err.getField() + " "
                        + err.getDefaultMessage())
                .collect(Collectors.toList());

        ErrorResponse response = new ErrorResponse(
                message, ex, HttpStatus.BAD_REQUEST, 400
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    // exceptions for constraints and other validations
    @ExceptionHandler(value =
            {
                    MethodArgumentTypeMismatchException.class,
                    ConstraintViolationException.class,
                    ValidationException.class})
    public ResponseEntity<Object> handleValidationExceptions(
            Exception ex
    ) {
        List<String> messages;

        if (ex instanceof ConstraintViolationException cve) {
            messages = cve.getConstraintViolations()
                    .stream()
                    .map(violation ->
                            violation.getPropertyPath() + " " +
                                    violation.getMessage())
                    .collect(Collectors.toList());
        } else {
            messages = List.of(ex.getMessage());
        }

        ErrorResponse response =
                new ErrorResponse(messages, ex, HttpStatus.BAD_REQUEST,
                        400);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
