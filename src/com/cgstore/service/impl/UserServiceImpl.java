package com.cgstore.service.impl;

import java.sql.SQLException;

import com.cgstore.dao.impl.UserDaoImpl;
import com.cgstore.domain.User;
import com.cgstore.service.UserService;


public class UserServiceImpl implements UserService{
	private UserDaoImpl dao = new UserDaoImpl();
	private boolean t = false;
	
	/**
	 * 注册用户
	 * @param user
	 * @return boolean
	 */
	public boolean addUser(User user) {
		try {
			dao.addUser(user);
			t = true;
			return t;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 根据用户名密码查找用户
	 * @param username
	 * @param password
	 * @return user
	 * @throws SQLException
	 */
	public User findUser(String username,String password) throws SQLException {
		return dao.findUserByUsernameAndPassword(username, password);
	}
}
