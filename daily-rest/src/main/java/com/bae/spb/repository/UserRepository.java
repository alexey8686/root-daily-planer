package com.bae.spb.repository;

import com.bae.spb.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

    User getUserByEmail(String email);
}
