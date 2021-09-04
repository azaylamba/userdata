package com.opsec.userdata.persistence;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.opsec.userdata.model.User;

/**
 * Interface to perform database operations using {@link MongoRepository}.
 *
 */
@Repository
public interface UserRepository extends MongoRepository<User, Long> {
    
}
