package com.cgstore.dao.impl;


import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.cgstore.dao.InfoDao;
import com.cgstore.domain.Product;
import com.cgstore.utils.DataSourceUtil;

/**
 * 单个商品详情查询类
 * @author 王
 *
 */
public class InfoDaoImpl implements InfoDao{
	/**
	 * 查询单个商品具体信息的方法
	 * @param pid
	 * @return product
	 * @throws Exception
	 */
	@Override
	public Product findByPid(String pid) throws Exception{
		QueryRunner qr = new QueryRunner(DataSourceUtil.getDataSource());
		String sql="select * from product where pid=?";
		return qr.query(sql,new BeanHandler<>(Product.class),pid);
	}

}
