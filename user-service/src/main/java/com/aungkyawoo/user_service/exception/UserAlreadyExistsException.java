package com.aungkyawoo.user_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * User Already Exists Exception
 * Author : Aung Kyaw Oo
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class UserAlreadyExistsException extends RuntimeException {

    /**
     * User Already Exists Exception
     * @param message String
     */
    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
