package com.cgstore.dao;

import java.util.List;

import com.cgstore.domain.Order;
import com.cgstore.domain.OrderItem;
import com.cgstore.domain.User;

public interface OrderDao {

	/**
	 * 添加一条订单
	 */
	void add(Order order) throws Exception;
	
	/**
	 * 添加一条订单项
	 */
	void addItem(OrderItem oi) throws Exception;
	
	/**
	 * 分页查询我的订单
	 */
	 List<Order> findByPage(int currentPage, int pageSize, String username) throws Exception;
	 
	 /**
	  * 查询我的订单的记录条数
	  */
	 int getTotalCount(String username) throws Exception;
	 
	 /**
	  * 查询订单详情
	  */
	 Order findByOid(String oid)throws Exception;
	 
	 /**
	  * 修改订单
	  * @param order
	  * @throws Exception
	  */
	 void update(Order order)throws Exception;
	 
	 /**
	  * 取消订单
	  * @param oid
	  * @throws Exception
	  */
	 void delate(String oid) throws Exception;
}
