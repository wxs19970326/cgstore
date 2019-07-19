package com.cgstore.domain;


/**
 * 用户实体类
 * @author 王
 *
 */
public class User {

	//用户名
	private String username;
	//密码
	private String password;
	//邮箱
	private String emil;

	public User() {

	}
	
	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public User(String username, String password,String emil) {
		this.username = username;
		this.password = password;
		this.emil = emil;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getEmil() {
		return emil;
	}
	
	public void setEmil(String emil) {
		this.emil = emil;
	}

}
