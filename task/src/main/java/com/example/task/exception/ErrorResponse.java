package com.example.task.exception;

import java.time.LocalDateTime;

public class ErrorResponse {

    private LocalDateTime timestamp;
    private String errorCode;
    private String message;

    public ErrorResponse(String errorCode, String message) {
        this.timestamp = LocalDateTime.now();
        this.errorCode = errorCode;
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getMessage() {
        return message;
    }
}