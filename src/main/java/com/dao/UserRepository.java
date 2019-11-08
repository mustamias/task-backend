package com.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.model.AppUser;

@Repository
public interface UserRepository extends MongoRepository<AppUser, String>{
	
	public AppUser findByUsername(String username);

}
