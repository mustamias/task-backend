package com.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.dao.UserRepository;
import com.model.AppUser;

@Service
public class UserServiceImpl implements IUserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;


	@Override
	public AppUser findUserByUserName(String userName) {
		return userRepository.findByUsername(userName);
	}

	@Override
	public AppUser saveUser(AppUser user) {

		AppUser existUser = userRepository.findByUsername(user.getUsername());
		if(existUser != null) throw new RuntimeException("user exist");
		user.setId(UUID.randomUUID().toString());
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

}
