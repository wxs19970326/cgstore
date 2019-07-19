package com.cgstore.dao;

import java.sql.SQLException;

import com.cgstore.domain.User;

public interface UserDao {
	/**
	 * 添加用户
	 * @param user
	 * @throws SQLException
	 */
	void addUser(User user) throws SQLException;
	
	/**
	 * 修改密码的方法
	 * @param newPwd
	 * @param username
	 * @param emil
	 * @param telephone
	 * @throws SQLException
	 */
	void resetUserPwd(String newPwd, String username, String emil, String telephone) throws SQLException;
	
	/**
	 * 根据用户名密码查找用户的方法
	 * @param username
	 * @param password
	 * @return user
	 * @throws SQLException
	 */
	User findUserByUsernameAndPassword(String username, String password) throws SQLException;
}
