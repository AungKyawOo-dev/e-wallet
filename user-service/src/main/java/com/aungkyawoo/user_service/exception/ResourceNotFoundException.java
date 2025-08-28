package com.aungkyawoo.user_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * ResourceNotFoundException
 * Author : Aung Kyaw Oo
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    /**
     * ResourceNotFoundException
     * @param resourceName String
     * @param fieldName String
     * @param value String
     */
    public ResourceNotFoundException(String resourceName, String fieldName, String value) {
        super(String.format("%s not found with %s : '%s'", resourceName, fieldName, value));
    }
}
