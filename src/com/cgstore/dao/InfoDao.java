package com.cgstore.dao;

import com.cgstore.domain.Product;

public interface InfoDao {
	/**
	 * 查询单个商品具体信息的方法
	 * @param pid
	 * @return product
	 * @throws Exception
	 */
	Product findByPid(String pid) throws Exception;
}
