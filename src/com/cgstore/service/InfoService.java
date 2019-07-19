package com.cgstore.service;

import com.cgstore.domain.Product;

public interface InfoService {
	Product findByPid(String pid) throws Exception;
}
