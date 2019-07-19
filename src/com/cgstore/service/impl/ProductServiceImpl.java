package com.cgstore.service.impl;

import java.util.List;

import com.cgstore.dao.impl.ProductDaoImpl;
import com.cgstore.domain.PageBean;
import com.cgstore.domain.Product;
import com.cgstore.service.ProductService;



public class ProductServiceImpl implements ProductService{
	
	/**
	 * 根据pid查询单个商品详情
	 * @throws Exception 
	 */
	public Product findByPid(String pid) throws Exception {
		ProductDaoImpl pd = new ProductDaoImpl();
		return pd.getByPid(pid);
	}
	
	/**
	 * 按类别分页查询商品
	 */
	public PageBean<Product> findByPage(int currPage, int pageSize, String cid) throws Exception {
		ProductDaoImpl pdao=new ProductDaoImpl();
		//当前页数据
		List<Product> list=pdao.findByPage(currPage,pageSize,cid);
		
		//总条数
		int totalCount = pdao.getTotalCount(cid);
		
		PageBean<Product> pp = new PageBean<>(list, currPage, pageSize, totalCount);
		return pp;
	}
}
