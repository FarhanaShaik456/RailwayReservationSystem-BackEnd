package com.registerwithemail.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.registerwithemail.model.UserDetails;

@Repository
public interface UserRepository extends MongoRepository<UserDetails, String> {

	Optional<UserDetails> findByEmail(String email);

	UserDetails findByUsername(String username);
}
