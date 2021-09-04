package com.opsec.userdata.controller;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.fail;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import com.opsec.userdata.constants.UserConstants;
import com.opsec.userdata.exception.UserValidationException;
import com.opsec.userdata.model.User;
import com.opsec.userdata.model.UserDTO;
import com.opsec.userdata.service.spi.UserService;

/**
 * Test class for {@link UserController}.
 */
class UserControllerTest {
    
    private UserController userController;
    private UserService userService;
    
    @BeforeEach
    public void initTest() {
        userService = Mockito.mock(UserService.class);
        userController = new UserController(userService);
    }
    
    /**
     * Test to verify the behavior of {@link UserController#create(com.opsec.userdata.model.UserDTO)}.
     */
    @Test
    void testCreateUser() {
        UserDTO userDTO = getUserDTOInstance();
        Mockito.when(userService.createUser(Mockito.any(User.class))).thenReturn(UserController.mapUser(userDTO));
        
        ResponseEntity<User> responseEntity = userController.create(userDTO);
        assertThat(responseEntity).isNotNull();
        assertThat(responseEntity.getBody()).isNotNull();
        assertThat(responseEntity.getBody()).isEqualTo(UserController.mapUser(userDTO));
        Mockito.verify(userService, Mockito.times(1)).createUser(Mockito.any(User.class));
    }
    
    /**
     * Test to verify the behavior of {@link UserController#update(com.opsec.userdata.model.UserDTO)}.
     */
    @Test
    void testUpdateUser() {
        UserDTO userDTO = getUserDTOInstance();
        Mockito.when(userService.updateUser(Mockito.any(User.class))).thenReturn(UserController.mapUser(userDTO));
        
        ResponseEntity<User> responseEntity = userController.update(userDTO);
        assertThat(responseEntity).isNotNull();
        assertThat(responseEntity.getBody()).isNotNull();
        assertThat(responseEntity.getBody()).isEqualTo(UserController.mapUser(userDTO));
        Mockito.verify(userService, Mockito.times(1)).updateUser(Mockito.any(User.class));
    }
    
    /**
     * Test to verify the behavior of {@link UserController#delete(Long)}.
     */
    @Test
    void testDeleteUser() {
        Mockito.doNothing().when(userService).deleteUser(Mockito.anyLong());
        userController.delete(10l);
        Mockito.verify(userService, Mockito.times(1)).deleteUser(Mockito.anyLong());
    }
    
    /**
     * Test to verify the behavior of {@link UserController#getUser(Long)}.
     */
    @Test
    void testGetUser() {
        UserDTO userDTO = getUserDTOInstance();
        Mockito.when(userService.getUser(Mockito.anyLong())).thenReturn(UserController.mapUser(userDTO));
        
        ResponseEntity<User> responseEntity = userController.getUser(10l);
        assertThat(responseEntity).isNotNull();
        assertThat(responseEntity.getBody()).isNotNull();
        assertThat(responseEntity.getBody()).isEqualTo(UserController.mapUser(userDTO));
        Mockito.verify(userService, Mockito.times(1)).getUser(Mockito.anyLong());
    }
    
    /**
     * Test to verify that controller input is validated correctly.
     */
    @Test
    void testInputValidation() {
        UserDTO userDTO = new UserDTO();
        
        StringBuilder expectedValidationError = new StringBuilder();
        expectedValidationError.append(UserConstants.ID_SHOULD_BE_NULL);
        expectedValidationError.append(UserConstants.FIRST_NAME_SHOULD_NOT_BE_BLANK);
        expectedValidationError.append(UserConstants.SURNAME_SHOULD_NOT_BE_BLANK);
        expectedValidationError.append(UserConstants.TITLE_SHOULD_NOT_BE_BLANK);
        expectedValidationError.append(UserConstants.DOB_SHOULD_BE_VALID);
        try {
            ResponseEntity<User> responseEntity = userController.create(userDTO);
            fail("Exception should have been thrown");
        } catch (UserValidationException userValidationException) {
            assertThat(userValidationException).isNotNull();
            assertThat(userValidationException.getMessage()).isEqualTo(expectedValidationError.toString());
        }
    }
    
    /**
     * Returns instance of {@link UserDTO} object.
     *
     * @return User DTO object
     */
    private UserDTO getUserDTOInstance() {
        UserDTO userDTO = new UserDTO();
        userDTO.setDob(new Date());
        userDTO.setFirstName("Ajay");
        userDTO.setSurname("Kumar");
        userDTO.setId(10);
        userDTO.setTitle("Mr");
        return userDTO;
    }
    
}
