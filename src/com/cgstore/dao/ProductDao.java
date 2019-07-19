package com.cgstore.dao;

import java.util.List;

import com.cgstore.domain.Product;

public interface ProductDao {
	/**
	 * 查询单个商品详情
	 */
	Product getByPid(String pid) throws Exception;
	
	/**
	 * 查询当前页需要展示的数据
	 * @param currPage 当前页
	 * @param pageSize 页面大小
	 * @param cid 商品种类id
	 * @return product 商品实体
	 * @throws Exception
	 */
	List<Product> findByPage(int currPage,int pageSize,String cid) throws Exception;
	
	/**
	 * 查询当前类别的总条数
	 */
	int getTotalCount(String cid) throws Exception;
}
