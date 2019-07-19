package com.cgstore.servlet;

import java.io.IOException;
//import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cgstore.domain.User;
import com.cgstore.service.impl.UserServiceImpl;

/**
 * 登陆模块
 * @author 王
 *
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 设置请求和响应编码
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// PrintWriter out = response.getWriter();
		// 获取请求参数
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		// 登录验证
		UserServiceImpl us = new UserServiceImpl();
		User user = new User();
		try {
			user = us.findUser(username, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (user != null) {
			request.getSession().setAttribute("user", user);
			response.sendRedirect("goindex.jsp");// /store
			return;
		} else {
			response.getWriter().print("<h2>登陆失败,3秒后跳转</h2>");
			// 定时刷新回到登陆页面
			response.setHeader("refresh", "3;url=/cgstore/Login.html");
		}

	}

/*	public String logout(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 干掉session
		request.getSession().invalidate();

		// 重定向
		response.sendRedirect(request.getContextPath());

		// 处理自动登录

		return null;
	}*/
}
