package com.cgstore.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cgstore.domain.User;
import com.cgstore.service.impl.UserServiceImpl;


/**
 * 注册模块
 * @author 王
 *
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {

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
		PrintWriter out = response.getWriter();
		User user = new User();
		// 获取请求参数
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String repassword = request.getParameter("repassword");
		String emil = request.getParameter("emil");

		user.setUsername(username);
		user.setPassword(password);
		user.setEmil(emil);

		// 注册验证
		UserServiceImpl us = new UserServiceImpl();
		if (us.addUser(user)) {
			if (password.equals(repassword)) {
				out.print("<script type='text/javascript'>");
				out.print("alert('注册成功！');");
				out.print("window.location='Login.html';");
				out.print("</script>");

			} else {
				out.print("<script type='text/javascript'>");
				out.print("alert('两次密码输入不一致！');");
				out.print("window.location='register.html';");
				out.print("</script>");
			}
		} else {
			out.print("<script type='text/javascript'>");
			out.print("alert('出錯啦！！');");
			out.print("window.location='register.html';");
			out.print("</script>");
		}
	}

}
