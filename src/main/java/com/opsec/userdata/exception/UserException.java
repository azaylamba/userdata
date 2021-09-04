package com.opsec.userdata.exception;

/**
 * Generic exception for user related operations.
 *
 */
public class UserException extends RuntimeException {
    
    public UserException(String message) {
        super(message);
    }
}
