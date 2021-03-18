package com.spring.core.dao;

import com.spring.core.model.User;

public interface UserDao {
	
	/**
	 * @param username
	 * @return User
	 */
	User findByUserName(String username);
	
	public void save(User user);

    User getUserByEmailId(String emailId);

	void update(User user);

	User getByUsername(String username);

	User getByUserNameOrEmailId(String username, String emailId);
}