package com.cgstore.service.impl;

import com.cgstore.dao.impl.InfoDaoImpl;
import com.cgstore.domain.Product;
import com.cgstore.service.InfoService;

public class InfoServiceImpl implements InfoService{

	public Product findByPid(String pid) throws Exception {
		InfoDaoImpl ido = new InfoDaoImpl();
		return ido.findByPid(pid);
	}

}
