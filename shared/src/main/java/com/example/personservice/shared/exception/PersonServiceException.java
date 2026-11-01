package com.example.personservice.shared.exception;

public class PersonServiceException extends RuntimeException {

    private final String errorCode;

    public PersonServiceException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
