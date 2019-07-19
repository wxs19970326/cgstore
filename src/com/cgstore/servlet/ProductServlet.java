package com.cgstore.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cgstore.domain.PageBean;
import com.cgstore.domain.Product;
import com.cgstore.service.impl.ProductServiceImpl;

/**
 * 商品模块
 * @author 王
 *
 */
public class ProductServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 设置请求和响应编码
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		// 1.获取类别 当前页 设置一个pagesize
		String cid = request.getParameter("cid");
//		System.out.println("产品类别是"+cid);
		int currPage = Integer.parseInt(request.getParameter("currPage"));
//		System.out.println("当前页是"+currPage);
		int pageSize = 12;

		// 2.调用service 返回值pagebean
		ProductServiceImpl ps = new ProductServiceImpl();
		PageBean<Product> bean = null;
		try {
			bean = ps.findByPage(currPage, pageSize, cid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		System.out.println("总页数为："+bean.getTotalPage());
		// 3.将结果放入request中 请求转发
		request.setAttribute("pb", bean);
		request.getRequestDispatcher("/product.jsp").forward(request, response);
	}
}
