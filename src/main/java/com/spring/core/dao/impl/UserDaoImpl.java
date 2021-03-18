package com.spring.core.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.spring.core.dao.AbstractDao;
import com.spring.core.dao.UserDao;
import com.spring.core.model.User;

@Repository
public class UserDaoImpl extends AbstractDao implements UserDao {
 
 /*
  * (non-Javadoc)
  * 
  * @see com.spring.core.dao.UserDao #findByUserName(java.lang.String)
  */
 @SuppressWarnings("unchecked")
 public User findByUserName(final String username) {
  List<User> users = new ArrayList<User>();
  users = getSession().createQuery("from User where username=? and enabled=true").setParameter(0, username).list();
  if (users.size() > 0) {
   return users.get(0);
  } else {
   return null;
  }
 }
 	
 @Override
 public void save(User e) {
	 getSession().save(e);
 }

 @Override
 public User getUserByEmailId(String emailId) {
  return (User) getSession().createQuery("from User where emailId=?").setParameter(0,emailId).uniqueResult();
 }

 @Override
 public void update(User user) {
   getSession().update(user);
 }

@Override
public User getByUsername(String username) {
	// TODO Auto-generated method stub
	return (User) getSession().createQuery("from User where username=? and enabled=true").setParameter(0, username).uniqueResult();
}

@Override
public User getByUserNameOrEmailId(String username, String emailId) {
	// TODO Auto-generated method stub
	return (User) getSession().createQuery("from User where username=? or emailId=?").setParameter(0, username).setParameter(1, emailId).uniqueResult();
}
 
}