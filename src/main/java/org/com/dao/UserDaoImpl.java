package org.com.dao;

import org.com.entity.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
@Transactional
@Repository
public class UserDaoImpl implements UserDao{

	@Autowired
	private SessionFactory factory;

	@Override
	public User findUser(String username) throws Exception {
		
		return null;
	}

	@Override
	public Long saveUser(User user) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
