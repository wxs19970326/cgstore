package com.cgstore.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SessionFilter implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		/**
		 * 判断用户有没有登陆
		 * 	依据：session（会自动创建）
		 */
		// 1、获取session对象
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)resp;
		HttpSession session = request.getSession();
		// 获得当前的请求信息（判断需不需要拦截）index.jsp
		String path = request.getServletPath();
		System.out.println("path:::"+path);
		
		/** 扩展内容学习：
		 * 1、读取url.properties文件
		 * 2、获取文件中所有的key==》set集合
		 * 3、遍历set集合
		 * 
		 * while(){
		 * 	if（xx.equals(path)）{
		 * 	
		 * 	}
		 * }
		 * 
		 */
		//ResourceBundle rb = ResourceBundle.getBundle("url");
		
		
		/*if("/img/logo_bg.jpg".equals(path)||"/img/logowz.png".equals(path)||"/fonts/glyphicons-halflings-regular.ttf".equals(path)||"/fonts/Login.html".equals(path)||"/fonts/glyphicons-halflings-regular.woff".equals(path)||"/fonts/glyphicons-halflings-regular.eot".equals(path)||"/img/header1.png".equals(path)||"/img/header.jpg".equals(path)||"/img/1.jpg".equals(path)||"/img/2.jpg".equals(path)||"/img/3.jpg".equals(path)||"/css/bootstrap.min.css".equals(path)||"/img/Login.html".equals(path)||"/js/Login.html".equals(path)||"/css/font-awesome.min.css".equals(path)||"/css/reset.css".equals(path)||"/css/common.css".equals(path)||"/css/Login.html".equals(path)||"/js/jquery-1.11.3.min.js".equals(path)||"/js/bootstrap.min.js".equals(path)||"/category".equals(path)||"/index".equals(path)||"/legend".equals(path)||"/register".equals(path)||"/register.html".equals(path)||"/infor.jsp".equals(path)||"/head.jsp".equals(path)||"/index.jsp".equals(path)||"/Login.html".equals(path)||"/goindex.jsp".equals(path)||"/login".equals(path)||"/product.jsp".equals(path)||"/product".equals(path)||"/info".equals(path)||"/store_cart.jsp".equals(path)) {
			chain.doFilter(request, response);
		}else {*/
			// 2、判断是否登陆(可以通过session中是否有用户的信息)
			Object obj = session.getAttribute("user");
			System.out.println("Object:::"+obj);
			if(obj==null) {//-没有登陆
				response.sendRedirect("Login.html");
			}else {//-已经登陆
				// FilterChain 过滤器链
				chain.doFilter(request, response);
			}
//		}
		
		
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
