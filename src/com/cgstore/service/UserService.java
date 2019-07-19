package com.cgstore.service;

import java.sql.SQLException;

import com.cgstore.domain.User;

public interface UserService {
	boolean addUser(User user);
	
	/**
	 * 根据用户名密码查找用户
	 * @param username
	 * @param password
	 * @return user
	 * @throws SQLException
	 */
	public User findUser(String username,String password) throws SQLException;
}
