package com.cgstore.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.cgstore.domain.Category;
import com.cgstore.service.impl.CategoryServiceImpl;
import com.cgstore.utils.JsonUtil;
/**
 * 商品种类servlet(导航栏内商品分类)
 * @author 王
 *
 */
public class CategoryServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
		// 1.调用categoryservice 查询所有的分类 返回值list
				CategoryServiceImpl cs = new CategoryServiceImpl();;
				List<Category> clist = null;
				try {
					clist = cs.findAll();
				} catch (Exception e) {
					e.printStackTrace();
				}

				// 2.将返回值转成json格式 返回到页面上
				//request.setAttribute("clist", clist);
				String json = JsonUtil.list2json(clist);
				
				//3.写回去
				response.setContentType("text/html;charset=utf-8");
				response.getWriter().println(json);
				
				return;
	}
}
