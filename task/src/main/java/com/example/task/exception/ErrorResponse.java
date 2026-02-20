package com.example.task.exception;

import java.time.LocalDateTime;

public class ErrorResponse {

    private LocalDateTime timestamp;
    private String errorCode;
    private String message;
    private String path;
    private String requestId;

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

    public String getPath() { return path; }
    public String getRequestId() { return requestId; }

    public void setPath(String path) { this.path = path; }
    public void setRequestId(String requestId) { this.requestId = requestId; }
}