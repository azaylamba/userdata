package com.opsec.userdata.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.opsec.userdata.constants.UserConstants;
import com.opsec.userdata.exception.UserException;
import com.opsec.userdata.exception.UserValidationException;
import com.opsec.userdata.model.User;
import com.opsec.userdata.model.UserDTO;
import com.opsec.userdata.service.api.IUserService;

/**
 * Controller class for exposing REST endpoints.
 *
 */
@RestController
public class UserController {
    
    private final IUserService userService;
    
    public UserController(IUserService userService) {
        this.userService = userService;
    }
    
    /**
     * Creates a user in the database by using the information passed in the request body in JSON format.
     *
     * @param userDTO User data passed in the request body
     */
    @PostMapping("/user/create")
    public ResponseEntity<User> create(@RequestBody UserDTO userDTO) {
        User user = mapUser(userDTO);
        return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
    }
    
    /**
     * Returns user for the given id passed as path variable.
     *
     * @param id user id passed as parameter
     * @return User object
     */
    @GetMapping("/user/find/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        return new ResponseEntity<>(userService.getUser(id), HttpStatus.OK);
    }
    
    /**
     * Updates a user in the database by using the information passed in the request body in JSON format.
     *
     * @param userDTO User object passed in the request body
     */
    @PostMapping("/user/update")
    public ResponseEntity<User> update(@RequestBody UserDTO userDTO) {
        User user = mapUser(userDTO);
        return new ResponseEntity<>(userService.updateUser(user), HttpStatus.ACCEPTED);
    }
    
    /**
     * Deletes a user from the database for the user id passed as path variable.
     *
     * @param id user id passed in the parameter
     */
    @DeleteMapping("user/delete/{id}")
    public void delete(@PathVariable Long id) {
        userService.deleteUser(id);
    }
    
    /**
     * Maps {@link UserDTO} object to {@link User} object.
     *
     * @param userDTO User data passed in the request body
     * @return returns the mapped User object
     */
    public static User mapUser(UserDTO userDTO) {
        validateInput(userDTO);
        User user = new User();
        user.setId(userDTO.getId());
        user.setDob(userDTO.getDob());
        user.setFirstName(userDTO.getFirstName());
        user.setSurname(userDTO.getSurname());
        user.setTitle(userDTO.getTitle());
        return user;
    }
    
    /**
     * Validates the request body.
     *
     * @param userDTO User data passed in the request body
     */
    private static void validateInput(UserDTO userDTO) {
        StringBuilder validationErrors = new StringBuilder();
        if(userDTO == null) {
            validationErrors.append(UserConstants.USER_DTO_NULL);
            throw new UserException(validationErrors.toString());
        }
        if(userDTO.getId() == 0) {
            validationErrors.append(UserConstants.ID_SHOULD_BE_NULL);
        }
        if(userDTO.getFirstName() == null || userDTO.getFirstName().equals("")) {
            validationErrors.append(UserConstants.FIRST_NAME_SHOULD_NOT_BE_BLANK);
        }
        if(userDTO.getSurname() == null || userDTO.getSurname().equals("")) {
            validationErrors.append(UserConstants.SURNAME_SHOULD_NOT_BE_BLANK);
        }
        if(userDTO.getTitle() == null || userDTO.getTitle().equals("")) {
            validationErrors.append(UserConstants.TITLE_SHOULD_NOT_BE_BLANK);
        }
        if(userDTO.getDob() == null || userDTO.getDob().toString().equals("")) {
            validationErrors.append(UserConstants.DOB_SHOULD_BE_VALID);
        }
        if(validationErrors.length() > 0) {
            throw new UserValidationException(validationErrors.toString());
        }
    }

}
