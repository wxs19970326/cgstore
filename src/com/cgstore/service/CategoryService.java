package com.cgstore.service;

import java.util.List;

import com.cgstore.domain.Category;

public interface CategoryService {
	List<Category> findAll() throws Exception;
}
