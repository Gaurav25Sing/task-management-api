package com.example.task.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<Object> handleNotFound(TaskNotFoundException ex) {

        ErrorResponse response = new ErrorResponse(
            ErrorCode.TASK_NOT_FOUND.name(),
            ex.getMessage()
        );

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGeneric(Exception ex) {

        ErrorResponse response = new ErrorResponse(
            ErrorCode.INTERNAL_SERVER_ERROR.name(),
            "Unexpected error occurred"
        );

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(org.springframework.web.bind.MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidation(
        org.springframework.web.bind.MethodArgumentNotValidException ex) {
        
        String message = ex.getBindingResult()
                       .getFieldError()
                       .getDefaultMessage();

        ErrorResponse response = new ErrorResponse(
            ErrorCode.VALIDATION_ERROR.name(),
            message
    );

    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
}
}