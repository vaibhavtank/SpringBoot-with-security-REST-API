package com.spring.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.core.dao.UserRoleDAO;
import com.spring.core.model.UserRole;

@Transactional
@Service("userRoleService")
public class UserRoleServiceImpl implements UserRoleService {

	@Autowired
	 private UserRoleDAO userRoleDao;
	
	@Override
	public void save(UserRole userRole) {
		userRoleDao.save(userRole);
	}

}
