package com.cgstore.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cgstore.domain.Product;
import com.cgstore.service.impl.InfoServiceImpl;

/**
 * 单个商品详情模块
 * @author 王
 *
 */
public class InfoServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String pid = req.getParameter("pid");
		
		InfoServiceImpl is = new InfoServiceImpl();
		
		Product pro = new Product();
		//根据请求的pid查询商品详情
		try {
			pro = is.findByPid(pid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//将plist写入req中
		req.setAttribute("pro", pro);
		
		//请求转发
		req.getRequestDispatcher("/infor.jsp").forward(req, resp);
	}
}
