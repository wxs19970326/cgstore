package com.cgstore.dao.impl;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.cgstore.dao.UserDao;
import com.cgstore.domain.User;
import com.cgstore.utils.DataSourceUtil;


public class UserDaoImpl implements UserDao{
	/**
	 * 添加用户
	 * @param user
	 * @throws SQLException
	 */
	@Override
	public void addUser(User user) throws SQLException {
		String sql = "insert into user(username,password,email) values(?,?,?)";
		QueryRunner runner = new QueryRunner(DataSourceUtil.getDataSource());
		int row = runner.update(sql, user.getUsername(), user.getPassword(), user.getEmil());
		if (row == 0) {
			throw new RuntimeException();
		}
	}

	/*
	 * public User findUserByActiveCode(String activeCode) throws SQLException {
	 * String sql = "select * from user where activecode=?"; QueryRunner runner =
	 * new QueryRunner(DataSourceUtil.getDataSource()); return runner.query(sql, new
	 * BeanHandler<User>(User.class), activeCode);
	 * 
	 * }
	 */

	/**
	 * 修改密码的方法
	 * @param newPwd
	 * @param username
	 * @param emil
	 * @param telephone
	 * @throws SQLException
	 */
	@Override
	public void resetUserPwd(String newPwd, String username, String emil, String telephone) throws SQLException {
		String sql = "update user set password=? where username=? and emil=? and telephone=?";
		QueryRunner runner = new QueryRunner(DataSourceUtil.getDataSource());
		runner.update(sql, newPwd, username, emil, telephone);
	}

	/**
	 * 根据用户名密码查找用户的方法
	 * @param username
	 * @param password
	 * @return user
	 * @throws SQLException
	 */
	@Override
	public User findUserByUsernameAndPassword(String username, String password) throws SQLException {
		String sql = "select * from user where username=? and password=?";
		QueryRunner runner = new QueryRunner(DataSourceUtil.getDataSource());
		return runner.query(sql, new BeanHandler<User>(User.class), username, password);
	}

}
