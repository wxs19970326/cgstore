package com.cgstore.dao.impl;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.cgstore.dao.ProductDao;
import com.cgstore.domain.Product;
import com.cgstore.utils.DataSourceUtil;


public class ProductDaoImpl implements ProductDao{
	
	/**
	 * 查询单个商品详情
	 */
	@Override
	public Product getByPid(String pid) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtil.getDataSource());
		
		String sql="select * from product where pid = ? limit 1";
		return qr.query(sql, new BeanHandler<>(Product.class), pid);
	}
	
	
	/**
	 * 查询当前页需要展示的数据
	 * @param currPage 当前页
	 * @param pageSize 页面大小
	 * @param cid 商品种类id
	 * @return product 商品实体
	 * @throws Exception
	 */
	@Override
	public List<Product> findByPage(int currPage,int pageSize,String cid) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtil.getDataSource());
		String sql="select * from product where cid=? limit ?,?";
		return qr.query(sql, new BeanListHandler<>(Product.class),cid,(currPage-1)*pageSize,pageSize);
	}
	
	/**
	 * 查询当前类别的总条数
	 */
	@Override
	public int getTotalCount(String cid) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtil.getDataSource());
		String sql="select count(*) from product where cid = ?";
		return ((Long)qr.query(sql, new ScalarHandler(), cid)).intValue();
	}

}
