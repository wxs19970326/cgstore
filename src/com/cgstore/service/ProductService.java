package com.cgstore.service;

import com.cgstore.domain.PageBean;
import com.cgstore.domain.Product;

public interface ProductService {
	/**
	 * 根据pid查询单个商品详情
	 * @throws Exception 
	 */
	public Product findByPid(String pid) throws Exception;
	
	/**
	 * 按类别分页查询商品
	 */
	public PageBean<Product> findByPage(int currPage, int pageSize, String cid) throws Exception;
}
