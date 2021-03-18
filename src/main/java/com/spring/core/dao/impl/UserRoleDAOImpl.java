package com.spring.core.dao.impl;

import org.springframework.stereotype.Repository;

import com.spring.core.dao.AbstractDao;
import com.spring.core.dao.UserRoleDAO;
import com.spring.core.model.UserRole;

@Repository
public class UserRoleDAOImpl extends AbstractDao implements UserRoleDAO {

	@Override
	public void save(UserRole userRole) {
		getSession().save(userRole);
	}

}
