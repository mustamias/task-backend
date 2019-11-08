package com.service;

import com.model.AppUser;

public interface IUserService {

	public AppUser findUserByUserName(String userName);
	
	public AppUser saveUser(AppUser user);
}
