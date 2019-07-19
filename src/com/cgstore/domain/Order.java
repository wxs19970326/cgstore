package com.cgstore.domain;
/**
 * 我的订单实体类
 * @author 王
 *
 */

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javafx.scene.chart.PieChart.Data;

public class Order {
	//订单id
	private String oid;
	
	//订单总金额
	private Date ordertime;
	
	private Double total;
	
	//订单状态 0:未支付 1:已支付
	private Integer state=0;
	
	//收件人姓名
	private String name;
	
	//收件人地址
	private String address;
	
	//收件人电话
	private String telephone;
	
	//那位用户的订单
	private User user;
	
	//包含哪些订单项
	private List<OrderItem> items = new LinkedList<>();
	
	public Order(String oid, Date ordertime, Double total, Integer state, String name, String address,
			String telephone, User user, List<OrderItem> items) {
		this.oid = oid;
		this.ordertime = ordertime;
		this.total = total;
		this.state = state;
		this.name = name;
		this.address = address;
		this.telephone = telephone;
		this.user = user;
		this.items = items;
	}

	public Order() {
	}

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public Date getOrdertime() {
		return ordertime;
	}

	public void setOrdertime(Date ordertime) {
		this.ordertime = ordertime;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<OrderItem> getItems() {
		return items;
	}

	public void setItems(List<OrderItem> items) {
		this.items = items;
	}

	
	
	
}
