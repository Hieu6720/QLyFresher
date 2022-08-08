package com.management.fresher.exception;

public class CustomException extends RuntimeException{
    private final Integer status;
    private final String message;

    public CustomException(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

    public Integer getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
