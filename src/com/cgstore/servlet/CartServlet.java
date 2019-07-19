package com.cgstore.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cgstore.domain.Cart;
import com.cgstore.domain.CartItem;
import com.cgstore.domain.Product;
import com.cgstore.service.impl.ProductServiceImpl;

/**
 * 购物车模块
 * 
 * @author 王
 *
 */
public class CartServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 获取购物车
	 * 
	 * @param request
	 * @return
	 */
	public Cart getCart(HttpServletRequest request) {
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		// 判断购物车是否为空
		if (cart == null) {
			// 创建一个cart
			cart = new Cart();

			// 添加到session中
			request.getSession().setAttribute("cart", cart);
		}
		return cart;
	}

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method = request.getParameter("method");
		// System.out.println("method为"+method);
		if (method.equals("addCart")) {
			addCart(request, response);
		} else if (method.equals("remove")) {
			remove(request, response);
		} else {
			clear(request, response);
		}
	}

	/**
	 * 添加到购物车
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void addCart(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1.获取商品 pid 和 购买的商品数量count
		String pid = request.getParameter("pid");
		int count = Integer.parseInt(request.getParameter("count"));
		// System.out.println(pid+":::"+count);

		// 2.通过productservice 和 pid 查询商品
		ProductServiceImpl ps = new ProductServiceImpl();
		Product product = new Product();
		try {
			product = ps.findByPid(pid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 3.封裝成CartItem
		CartItem cartItem = new CartItem(product, count);

		// 4.添加到购物车
		Cart cart = getCart(request);
		cart.add2Cart(cartItem);
		// System.out.println("购物车cart"+cart);

		// 5.重定向
		response.sendRedirect("store_cart.jsp");
	}

	/**
	 * 删除购物项
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void remove(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1.获取商品 pid
		String pid = request.getParameter("pid");
		/*
		 * int count = Integer.parseInt(request.getParameter("count")); //
		 * System.out.println(pid+":::"+count);
		 * 
		 * // 2.通过productservice 和 pid 查询商品 ProductServiceImpl ps = new
		 * ProductServiceImpl(); Product product = new Product(); try { product =
		 * ps.findByPid(pid); } catch (Exception e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 * 
		 * // 3.封裝成CartItem CartItem cartItem = new CartItem(product, count);
		 */

		// 4.获取购物车
		Cart cart = getCart(request);
		cart.removeFromCart(pid);
		// System.out.println("购物车cart"+cart);

		// 5.重定向
		response.sendRedirect("store_cart.jsp");
	}

	/**
	 * 清空购物车
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void clear(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cart cart = getCart(request);
		cart.clearCart();
		response.sendRedirect("store_cart.jsp");
	}
}
