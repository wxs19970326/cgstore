package com.cgstore.service;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

import com.cgstore.domain.Order;
import com.cgstore.domain.PageBean;

public interface OrderService {
	
	/**
	 * 生成订单
	 * @param order 
	 * @throws Exception
	 */
	void add(Order order) throws Exception;
	
	/**
	 * 分页查询我的订单
	 * @param currentPage
	 * @param pageSize
	 * @param user
	 * @return
	 * @throws SQLException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	PageBean<Order> findByPage(int currentPage, int pageSize, String username) throws SQLException, IllegalAccessException, InvocationTargetException;
	
	/**
	 * 查询订单详情
	 * @param oid
	 * @return Order
	 * @throws Exception
	 */
	Order findByOid(String oid) throws Exception;
	
	/**
	 * 修改订单详情
	 * @param order
	 * @throws Exception
	 */
	void update(Order order) throws Exception;
	
	/**
	 * 退款或取消订单
	 * @param oid
	 * @throws Exception
	 */
	void delate(String oid) throws Exception;
}
