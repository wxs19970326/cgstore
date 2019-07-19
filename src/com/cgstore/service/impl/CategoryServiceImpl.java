package com.cgstore.service.impl;

import java.util.List;

import com.cgstore.dao.impl.CategoryDaoImpl;
import com.cgstore.domain.Category;
import com.cgstore.service.CategoryService;


public class CategoryServiceImpl implements CategoryService{

	public List<Category> findAll() throws Exception {
		CategoryDaoImpl cd = new CategoryDaoImpl();
		return cd.findAll();
	}

}
