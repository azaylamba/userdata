package com.opsec.userdata.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Exception handler class for the application. It propagates a meaningful message to the user.
 */
@ControllerAdvice
public class UserExceptionHandler {
    
    /**
     * Handler for the user not found exception.
     *
     * @param userNotFoundException user not found exception
     * @return ResponseEntity
     */
    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity<String> userNotFoundException(UserNotFoundException userNotFoundException) {
        return new ResponseEntity<>(userNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
    }
    
    /**
     * Handler for the user already exists exception
     *
     * @param userAlreadyExistsException exception denoting user already exists
     * @return ResponseEntity
     */
    @ExceptionHandler(value = UserAlreadyExistsException.class)
    public ResponseEntity<String> userAlreadyExistsException(UserAlreadyExistsException userAlreadyExistsException) {
        return new ResponseEntity<>(userAlreadyExistsException.getMessage(), HttpStatus.EXPECTATION_FAILED);
    }
    
    /**
     * Generic exception handler.
     *
     * @param userException generic exception thrown
     * @return ResponseEntity
     */
    @ExceptionHandler(value = UserException.class)
    public ResponseEntity<String> userException(UserException userException) {
        return new ResponseEntity<>(userException.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    /**
     * Validation exception handler.
     *
     * @param userValidationException user validation exception
     * @return ResponseEntity
     */
    @ExceptionHandler(value = UserValidationException.class)
    public ResponseEntity<String> userValidationException(UserValidationException userValidationException) {
        return new ResponseEntity<>(userValidationException.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
