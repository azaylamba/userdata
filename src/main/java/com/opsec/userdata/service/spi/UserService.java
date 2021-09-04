package com.opsec.userdata.service.spi;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.opsec.userdata.exception.UserAlreadyExistsException;
import com.opsec.userdata.exception.UserException;
import com.opsec.userdata.exception.UserNotFoundException;
import com.opsec.userdata.model.User;
import com.opsec.userdata.persistence.UserRepository;
import com.opsec.userdata.service.api.IUserService;

/**
 * Service class for accessing business layer from controller.
 *
 */
@Service
public class UserService implements IUserService {
    
    private final UserRepository repository;
    
    public UserService(UserRepository repository) {
        this.repository = repository;
    }
    
    @Override
    public User createUser(User user) {
        try {
            if (repository.findById(user.getId()).isPresent()) {
                throw new UserAlreadyExistsException();
            }
            return repository.insert(user);
        } catch (UserAlreadyExistsException userAlreadyExistsException) {
            throw userAlreadyExistsException;
        } catch (Exception e) {
            throw new UserException(e.toString());
        }
    }
    
    @Override
    public User getUser(long userId) {
        try {
            Optional<User> user = repository.findById(userId);
            if (user.isPresent()) {
                return user.get();
            } else {
                throw new UserNotFoundException();
            }
        } catch (UserNotFoundException userNotFoundException) {
            throw userNotFoundException;
        } catch (Exception e) {
            throw new UserException(e.toString());
        }
    }
    
    @Override
    public User updateUser(User user) {
        try {
            if (repository.findById(user.getId()).isPresent()) {
                throw new UserAlreadyExistsException();
            }
            return repository.save(user);
        } catch (UserAlreadyExistsException userAlreadyExistsException) {
            throw userAlreadyExistsException;
        } catch (Exception e) {
            throw new UserException(e.toString());
        }
    }
    
    @Override
    public void deleteUser(long userId) {
        try {
            if (!repository.findById(userId).isPresent()) {
                throw new UserNotFoundException();
            }
            repository.deleteById(userId);
        } catch (UserNotFoundException userNotFoundException) {
            throw userNotFoundException;
        } catch (Exception e) {
            throw new UserException(e.toString());
        }
    }
}
