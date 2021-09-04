package com.opsec.userdata.service.api;

import com.opsec.userdata.exception.UserAlreadyExistsException;
import com.opsec.userdata.exception.UserNotFoundException;
import com.opsec.userdata.model.User;

/**
 * Service interface for accessing business layer.
 */
public interface IUserService {
    
    /**
     * Creates a user with the given {@link User} object.
     *
     * @param user User
     * @return User object
     * @throws UserAlreadyExistsException User already exists exception
     */
    User createUser(User user) throws UserAlreadyExistsException;
    
    /**
     * Returns user object for the given user id. Returns null if user object not found.
     *
     * @param userId user id
     * @return User object
     * @throws UserNotFoundException User not found exception
     */
    User getUser(long userId) throws UserNotFoundException;
    
    /**
     * Updates existing user with the given with information.
     *
     * @param user User object
     * @return User object
     * @throws UserAlreadyExistsException User already exists exception
     */
    User updateUser(User user) throws UserAlreadyExistsException;
    
    /**
     * Deletes the user from database for the given user id.
     *
     * @param userId user id
     * @throws UserNotFoundException user not found exception
     */
    void deleteUser(long userId) throws UserNotFoundException;
}
