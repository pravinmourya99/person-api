package com.example.personservice.shared.exception;

import com.example.personservice.shared.constant.ErrorCodes;

public class ResourceNotFoundException extends PersonServiceException {

    public ResourceNotFoundException(String message) {
        super(ErrorCodes.RESOURCE_NOT_FOUND, message);
    }
}
