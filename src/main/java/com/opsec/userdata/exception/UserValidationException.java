package com.opsec.userdata.exception;

/**
 * Validation exception for the request body values of the User information.
 */
public class UserValidationException extends RuntimeException {

    public UserValidationException(String message) {
        super(message);
    }
}
