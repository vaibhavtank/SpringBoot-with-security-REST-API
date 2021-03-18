package com.spring.core.service;

import org.springframework.security.core.userdetails.UserDetails;

import com.spring.core.model.User;

public interface UserService {

	User getUserByEmailId(String emailId);

	void update(User user);

	UserDetails loadUserByUsername(String username);

	User getByUsername(String username);

	User getByUserNameOrEmailId(String username, String emailId);

	void save(User user);

}
