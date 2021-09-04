package com.opsec.userdata.exception;

/**
 * Custom exception class for the scenarios where user is not found in the database.
 *
 */
public class UserNotFoundException extends RuntimeException {
    
    private static final String USER_NOT_FOUND = "User not found in the database";

    @Override
    public String getMessage() {
        return USER_NOT_FOUND;
    }
}
