package com.opsec.userdata.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.fail;

import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.opsec.userdata.exception.UserAlreadyExistsException;
import com.opsec.userdata.exception.UserNotFoundException;
import com.opsec.userdata.model.User;
import com.opsec.userdata.persistence.UserRepository;
import com.opsec.userdata.service.spi.UserService;

/**
 * Test class for {@link com.opsec.userdata.service.spi.UserService}
 */
class UserServiceTest {
    
    private UserRepository userRepository;
    private UserService userService;
    
    @BeforeEach
    public void initTest() {
        userRepository = Mockito.mock(UserRepository.class);
        userService = new UserService(userRepository);
    }
    
    /**
     * Tests the behavior of {@link UserService#createUser(com.opsec.userdata.model.User)}
     */
    @Test
    void testCreateUser() {
        User user = getUserInstance();
        Mockito.when(userRepository.findById(user.getId())).thenReturn(Optional.empty());
        Mockito.when(userRepository.insert(user)).thenReturn(user);
        
        User insertedUser = userService.createUser(user);
        assertThat(insertedUser).isNotNull();
        assertThat(insertedUser).isEqualTo(user);
        Mockito.verify(userRepository, Mockito.times(1)).findById(user.getId());
        Mockito.verify(userRepository, Mockito.times(1)).insert(user);
    }
    
    /**
     * Tests the behavior of {@link UserService#createUser(com.opsec.userdata.model.User)} to verify that {@link UserAlreadyExistsException} is
     * thrown.
     */
    @Test
    void testCreateUser_UserAlreadyExistsExceptionThrown() {
        User user = getUserInstance();
        Mockito.when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        
        try {
            User insertedUser = userService.createUser(user);
            fail("UserAlreadyExistsException should have been thrown");
        } catch (UserAlreadyExistsException userAlreadyExistsException) {
            Mockito.verify(userRepository, Mockito.times(1)).findById(user.getId());
            Mockito.verify(userRepository, Mockito.never()).insert(user);
        }
    }
    
    /**
     * Tests the behavior of {@link UserService#updateUser(User)}
     */
    @Test
    void testUpdateUser() {
        User user = getUserInstance();
        Mockito.when(userRepository.findById(user.getId())).thenReturn(Optional.empty());
        Mockito.when(userRepository.save(user)).thenReturn(user);
        
        User insertedUser = userService.updateUser(user);
        assertThat(insertedUser).isNotNull();
        assertThat(insertedUser).isEqualTo(user);
        Mockito.verify(userRepository, Mockito.times(1)).findById(user.getId());
        Mockito.verify(userRepository, Mockito.times(1)).save(user);
    }
    
    /**
     * Tests the behavior of {@link UserService#updateUser(User)} to verify that {@link UserAlreadyExistsException} is thrown.
     */
    @Test
    void testUpdateUser_UserAlreadyExistsExceptionThrown() {
        User user = getUserInstance();
        Mockito.when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        
        try {
            User insertedUser = userService.updateUser(user);
            fail("UserAlreadyExistsException should have been thrown");
        } catch (UserAlreadyExistsException userAlreadyExistsException) {
            Mockito.verify(userRepository, Mockito.times(1)).findById(user.getId());
            Mockito.verify(userRepository, Mockito.never()).save(user);
        }
    }
    
    /**
     * Tests the behavior of {@link UserService#deleteUser(long)}
     */
    @Test
    void testDeleteUser() {
        User user = getUserInstance();
        Mockito.when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        Mockito.doNothing().when(userRepository).deleteById(user.getId());
        
        userService.deleteUser(user.getId());
        Mockito.verify(userRepository, Mockito.times(1)).findById(user.getId());
        Mockito.verify(userRepository, Mockito.times(1)).deleteById(user.getId());
    }
    
    /**
     * Tests the behavior of {@link UserService#deleteUser(long)} to verify that UserNotFoundException is thrown when user not found.
     */
    @Test
    void testDeleteUser_UserNotFoundExceptionThrown() {
        User user = getUserInstance();
        Mockito.when(userRepository.findById(user.getId())).thenReturn(Optional.empty());
        try {
            userService.deleteUser(user.getId());
            fail("UserNotFoundException should have been thrown");
        } catch (UserNotFoundException userNotFoundException) {
            Mockito.verify(userRepository, Mockito.times(1)).findById(user.getId());
            Mockito.verify(userRepository, Mockito.never()).deleteById(user.getId());
        }
    }
    
    /**
     * Tests the behavior of {@link UserService#getUser(long)}
     */
    @Test
    void testGetUser() {
        User user = getUserInstance();
        Mockito.when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        
        User insertedUser = userService.getUser(user.getId());
        assertThat(insertedUser).isNotNull();
        assertThat(insertedUser).isEqualTo(user);
        Mockito.verify(userRepository, Mockito.times(1)).findById(user.getId());
    }
    
    /**
     * Tests the behavior of {@link UserService#getUser(long)} to verify that UserNotFoundException is thrown when user not found.
     */
    @Test
    void testGetUser_UserNotFoundExceptionThrown() {
        User user = getUserInstance();
        Mockito.when(userRepository.findById(user.getId())).thenReturn(Optional.empty());
        
        try {
            userService.getUser(user.getId());
            fail("UserNotFoundException should have been thrown");
        } catch (UserNotFoundException userNotFoundException) {
            Mockito.verify(userRepository, Mockito.times(1)).findById(user.getId());
        }
    }
    
    /**
     * Returns instance of {@link User} object.
     *
     * @return User object
     */
    private User getUserInstance() {
        User user = new User();
        user.setDob(new Date());
        user.setFirstName("Ajay");
        user.setSurname("Kumar");
        user.setId(10);
        user.setTitle("Mr");
        return user;
    }
}
