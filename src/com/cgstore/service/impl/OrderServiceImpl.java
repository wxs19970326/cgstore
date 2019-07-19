package com.cgstore.service.impl;


import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

import com.cgstore.dao.OrderDao;
import com.cgstore.dao.impl.OrderDaoImpl;
import com.cgstore.domain.Order;
import com.cgstore.domain.OrderItem;
import com.cgstore.domain.PageBean;
import com.cgstore.domain.User;
import com.cgstore.service.OrderService;
import com.cgstore.utils.DataSourceUtil;

public class OrderServiceImpl implements OrderService{
	
	/**
	 * 生成订单
	 * @param order 
	 * @throws Exception
	 */
	public void add(Order order) throws Exception{
		try {
			//1.开启事务
			DataSourceUtil.startTransaction();
			
			OrderDaoImpl od=new OrderDaoImpl();
			//2.向orders表中添加一个数据
			od.add(order);
			
			//int i=1/0;
			
			//3.向orderitem中添加n条数据
			for (OrderItem oi : order.getItems()) {
				od.addItem(oi);
			}
			
			//4.事务处理
			DataSourceUtil.commitAndClose();
		} catch (Exception e) {
			e.printStackTrace();
			DataSourceUtil.rollbackAndClose();
			throw e;
		}
		
	}
	
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
	public PageBean<Order> findByPage(int currentPage, int pageSize, String username) throws SQLException, IllegalAccessException, InvocationTargetException {
		OrderDaoImpl od = new OrderDaoImpl();
		List<Order> list = od.findByPage(currentPage,pageSize,username);
		
		//查询总记录条数
		int totalCount = od.getTotalCount(username);
		return new PageBean<>(list, currentPage, pageSize, totalCount);
	}

	/**
	 * 查询订单详情
	 */
	public Order findByOid(String oid) throws Exception{
		OrderDaoImpl od = new OrderDaoImpl();
		return od.findByOid(oid);
	}

	/**
	 * 修改订单
	 */
	@Override
	public void update(Order order) throws Exception {
		OrderDaoImpl od = new OrderDaoImpl();
		od.update(order);
	}

	/**
	 * 退款或取消订单
	 */
	public void delate(String oid) throws Exception{
		OrderDaoImpl od = new OrderDaoImpl();
		od.delate(oid);
		
	}
	
	

}
