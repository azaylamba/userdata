package com.opsec.userdata.exception;

/**
 * Custom exception for the scenarios when user already exists for the given id.
 *
 */
public class UserAlreadyExistsException extends RuntimeException {
    
    private static final String USER_ALREADY_EXISTS = "User already exists for the given id";
    
    @Override
    public String getMessage() {
        return USER_ALREADY_EXISTS;
    }
}
