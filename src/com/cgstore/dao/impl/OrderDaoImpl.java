package com.cgstore.dao.impl;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.cgstore.dao.OrderDao;
import com.cgstore.domain.Order;
import com.cgstore.domain.OrderItem;
import com.cgstore.domain.Product;
import com.cgstore.domain.User;
import com.cgstore.utils.DataSourceUtil;

/*
 * 订单dao层
 */
public class OrderDaoImpl implements OrderDao{


	/**
	 * 添加一条订单
	 */
	@Override
	public void add(Order order) throws Exception {
		QueryRunner qr = new QueryRunner();
		
		/*
		 * `oid` varchar(32) NOT NULL,
		  `ordertime` datetime DEFAULT NULL,
		  `total` double DEFAULT NULL,
		  
		  `state` int(11) DEFAULT NULL,
		  `address` varchar(30) DEFAULT NULL,
		  `name` varchar(20) DEFAULT NULL,
		  
		  `telephone` varchar(20) DEFAULT NULL,
		  `uid` varchar(32) DEFAULT NULL,
		 */
		String sql="insert into orders values(?,?,?,?,?,?,?,?)";
		qr.update(DataSourceUtil.getConnection(),sql, order.getOid(),order.getOrdertime(),order.getTotal(),order.getState(),
				order.getAddress(),order.getName(),order.getTelephone(),order.getUser().getUsername());
	}

	/**
	 * 添加一条订单项
	 */
	@Override
	public void addItem(OrderItem oi) throws Exception {
		QueryRunner qr = new QueryRunner();
		 /**
		 * `itemid` varchar(32) NOT NULL,
		  `count` int(11) DEFAULT NULL,
		  `subtotal` double DEFAULT NULL,
		  `pid` varchar(32) DEFAULT NULL,
		  `oid` varchar(32) DEFAULT NULL,
		 */
		String sql="insert into orderitem values(?,?,?,?,?)";
		qr.update(DataSourceUtil.getConnection(),sql, oi.getItemid(),oi.getCount(),oi.getSubtotal(),oi.getProduct().getPid(),oi.getOrder().getOid());
	}
	
	/**
	 * 分页查询我的订单
	 * @throws SQLException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	@Override
	public List<Order> findByPage(int currentPage, int pageSize, String username) throws SQLException, IllegalAccessException, InvocationTargetException {
		QueryRunner qr = new QueryRunner(DataSourceUtil.getDataSource());
		//从orders表中查询username为?的用户的所有订单，并按照ordertime逆序排列
		String sql = "select * from orders where username=? order by ordertime desc limit ?,?";
		List<Order> list = qr.query(sql, new BeanListHandler<>(Order.class),username,(currentPage-1)*pageSize,pageSize);
		
		//遍历该用户所有的订单,根据该用户订单中的订单编号oid查询出所有订单项并封装添加到改用户的相应的订单中
		for (Order order : list) {
			//多表查询:orderitem和product表连接,查询oid=?的所有订单项和订单项包含的商品
			sql = "select * from orderitem oi,product p where oi.pid=p.pid and oi.oid = ?";
			
			//MapListHandler, 将查询结果的每一条记录封装map集合,将每一个map集合放入list中返回
			List<Map<String, Object>> mList = qr.query(sql, new MapListHandler(), order.getOid());
			
			//遍历list集合，分别封装成product和orderitem
			//map的key:字段名  value:字段值
			for (Map<String, Object> map : mList) {
				//封装product
				Product p=new Product();
				BeanUtils.populate(p, map);
				
				//封装orderItem
				OrderItem oi = new OrderItem();
				BeanUtils.populate(oi, map);
				
				oi.setProduct(p);
				
				//将orderItem对象添加到对应的order对象的list集合中
				order.getItems().add(oi);
			}
		}
		return list;
	}
	
	/**
	 * 查询我的订单总记录条数
	 * @throws SQLException 
	 */
	@Override
	public int getTotalCount(String username) throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtil.getDataSource());
		String sql = "select count(*) from orders where username=?";
		return ((Long)qr.query(sql, new ScalarHandler(), username)).intValue();
	}

	/**
	 * 查询订单详情
	 */
	public Order findByOid(String oid) throws Exception{
		QueryRunner qr = new QueryRunner(DataSourceUtil.getDataSource());
		String sql="select * from orders where oid = ?";
		Order order = qr.query(sql, new BeanHandler<>(Order.class), oid);
		
		//封装orderitems
		sql="select * from orderitem oi,product p where oi.pid = p.pid and oi.oid = ?";
		List<Map<String, Object>> query = qr.query(sql, new MapListHandler(), oid);
		for (Map<String, Object> map : query) {
			//封装product
			Product product = new Product();
			BeanUtils.populate(product, map);
			
			//封装orderitem
			OrderItem oi = new OrderItem();
			BeanUtils.populate(oi, map);
			oi.setProduct(product);
			
			//将orderitem假如中order的items中
			order.getItems().add(oi);
		}
		return order;
	}

	/**
	 * 修改订单
	 */
	@Override
	public void update(Order order) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtil.getDataSource());
		String sql="update orders set state=?,address=?,name=?,telephone=? where oid=?";
		qr.update(sql, order.getState(),order.getAddress(),order.getName(),order.getTelephone(),order.getOid());
	}

	/**
	 * 取消订单
	 */
	@Override
	public void delate(String oid) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtil.getDataSource());
		String sql = "delete from orders where oid=?";
		qr.update(sql, oid);
	}

	
}
