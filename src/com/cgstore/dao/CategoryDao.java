package com.cgstore.dao;

import java.util.List;

import com.cgstore.domain.Category;

public interface CategoryDao {
	/**
	 * 查询所有商品分类的方法
	 * @return category
	 * @throws Exception
	 */
	List<Category> findAll() throws Exception;
}
