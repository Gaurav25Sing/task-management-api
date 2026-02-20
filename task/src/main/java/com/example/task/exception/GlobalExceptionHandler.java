package com.example.task.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<Object> handleApiException(ApiException ex, HttpServletRequest req) {
        ErrorResponse response = new ErrorResponse(
            ex.getErrorCode().name(),
            ex.getMessage()
        );
        response.setPath(req.getRequestURI());
        Object rid = req.getAttribute("requestId");
        if (rid != null) response.setRequestId(rid.toString());

        return new ResponseEntity<>(response, ex.getStatus());
    }

    @ExceptionHandler(org.springframework.web.bind.MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidation(
        org.springframework.web.bind.MethodArgumentNotValidException ex, HttpServletRequest req) {
        
        String message = ex.getBindingResult()
                       .getFieldError()
                       .getDefaultMessage();

        ErrorResponse response = new ErrorResponse(
            ErrorCode.VALIDATION_ERROR.name(),
            message
        );
        response.setPath(req.getRequestURI());
        Object rid = req.getAttribute("requestId");
        if (rid != null) response.setRequestId(rid.toString());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGeneric(Exception ex, HttpServletRequest req) {

        ErrorResponse response = new ErrorResponse(
            ErrorCode.INTERNAL_SERVER_ERROR.name(),
            "Unexpected error occurred"
        );
        response.setPath(req.getRequestURI());
        Object rid = req.getAttribute("requestId");
        if (rid != null) response.setRequestId(rid.toString());

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}