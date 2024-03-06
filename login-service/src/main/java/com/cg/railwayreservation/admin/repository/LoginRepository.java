package com.cg.railwayreservation.admin.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.cg.railwayreservation.admin.model.LoginModel;

@Repository
public interface LoginRepository extends MongoRepository<LoginModel, String> {
	LoginModel findByUsername(String username);

}
