package com.aungkyawoo.wallet_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * User Already Exists Exception
 * Author : Aung Kyaw Oo
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class WalletAlreadyExistsException extends RuntimeException {

    /**
     * User Already Exists Exception
     * @param message String
     */
    public WalletAlreadyExistsException(String message) {
        super(message);
    }
}
