package org.com.dao;

import org.com.entity.User;

public interface UserDao {

	public User findUser(String username)throws Exception;
	public Long saveUser(User user)throws Exception;
}
