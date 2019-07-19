package com.cgstore.dao.impl;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.cgstore.dao.CategoryDao;
import com.cgstore.domain.Category;
import com.cgstore.utils.DataSourceUtil;


public class CategoryDaoImpl implements CategoryDao{
	/**
	 * 查询所有商品分类的方法
	 * @return category
	 * @throws Exception
	 */
	@Override
	public List<Category> findAll() throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtil.getDataSource());
		String sql="select * from category";
		return qr.query(sql, new BeanListHandler<>(Category.class));
	}

}
