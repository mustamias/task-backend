package com.security;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.model.AppUser;
import com.service.IUserService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private IUserService userService;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		AppUser user = userService.findUserByUserName(username);
		if (user == null) throw new UsernameNotFoundException("invalid user");
	
		return new User(user.getUsername(), user.getPassword(), new ArrayList<>());
	}

}
