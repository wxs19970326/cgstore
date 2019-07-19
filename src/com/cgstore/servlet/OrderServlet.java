package com.cgstore.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cgstore.domain.Cart;
import com.cgstore.domain.CartItem;
import com.cgstore.domain.Order;
import com.cgstore.domain.OrderItem;
import com.cgstore.domain.PageBean;
import com.cgstore.domain.User;
import com.cgstore.service.impl.OrderServiceImpl;
import com.cgstore.utils.UUIDUtils;

/**
 * 订单模块
 * 
 * @author 王
 *
 */
public class OrderServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 0.设置请求和响应编码
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		String method = request.getParameter("method");
		if (method.equals("add")) {
			add(request, response);
		} else if (method.equals("orderList")) {
			orderList(request, response);
		} else if (method.equals("findByOid")) {
			try {
				findByOid(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (method.equals("pay")) {
			try {
				pay(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (method.equals("edit")) {
			try {
				edit(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(method.equals("confirmEdit")){
			try {
				confirmEdit(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(method.equals("cancel")) {
			try {
				cancel(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			try {
				reback(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * 提交订单并清空购物车
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		// 1.判断用户是否登陆
		// 1.1此处过滤器已经判断是否登陆
		User user = (User) request.getSession().getAttribute("user");
		/*
		 * if(user == null) { out.print("<script type='text/javascript'>");
		 * out.print("alert('请先登录！');"); out.print("window.location='Login.html';");
		 * out.print("</script>"); }
		 */

		// 1.封装数据
		Order order = new Order();
		// 1.1 订单id
		order.setOid(UUIDUtils.getId());

		// 1.2 订单时间
		order.setOrdertime(new Date());

		// 1.3 总金额
		// 获取session中cart
		Cart cart = (Cart) request.getSession().getAttribute("cart");

		order.setTotal(cart.getTotal());

		// 1.4 订单的所有订单项
		/*
		 * 先获取cart中itmes 遍历itmes 组装成orderItem 将orderItem添加到list(items)中
		 */
		for (CartItem cartItem : cart.getItmes()) {
			OrderItem oi = new OrderItem();

			// 设置id
			oi.setItemid(UUIDUtils.getId());

			// 设置购买数量
			oi.setCount(cartItem.getCount());

			// 设置小计
			oi.setSubtotal(cartItem.getSubtotal());

			// 设置product
			oi.setProduct(cartItem.getProduct());

			// 设置order
			oi.setOrder(order);

			// 添加到list中
			order.getItems().add(oi);
		}

		// 1.5 设置用户
		order.setUser(user);

		// 2.调用service 添加订单
		OrderServiceImpl os = new OrderServiceImpl();
		try {
			os.add(order);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 3.将order放入request域中,请求转发
		request.setAttribute("bean", order);

		// 4.清空购物车
		request.getSession().removeAttribute("cart");
		request.getRequestDispatcher("/order.jsp").forward(request, response);
	}

	/**
	 * 我的订单
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void orderList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		

		// 1.获取当前页
		int currentPage = Integer.parseInt(request.getParameter("currentPage"));
		// 设置页面大小
		int pageSize = 5;

		// 2.获取user
		User user = (User) request.getSession().getAttribute("user");

		// 3.调用orderService中的findByPage(currentPage,pageSize,user),返回值PageBean<Order>
		OrderServiceImpl os = new OrderServiceImpl();
		PageBean<Order> bean = null;
		try {
			bean = os.findByPage(currentPage, pageSize, user.getUsername());
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

		// 4.将bean写入request域中
		request.setAttribute("pb", bean);

		// 5.请求转发
		request.getRequestDispatcher("order_list.jsp").forward(request, response);
	}

	/**
	 * 查看订单详情
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void findByOid(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 1.获取oid
		String oid = request.getParameter("oid");

		// 2.调用OrderService查询订单详情 返回Order
		OrderServiceImpl os = new OrderServiceImpl();
		Order order = os.findByOid(oid);

		// 3.将Order写入request域中
		request.setAttribute("bean", order);
		// 4.请求转发
		request.getRequestDispatcher("order.jsp").forward(request, response);
	}

	/**
	 * 付款
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void pay(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 1.获取参数//接受参数
		String address = request.getParameter("address");
		String name = request.getParameter("name");
		String telephone = request.getParameter("telephone");
		String oid = request.getParameter("oid");

		// 通过id获取order
		OrderServiceImpl os = new OrderServiceImpl();
		Order order = os.findByOid(oid);

		order.setAddress(address);
		order.setName(name);
		order.setTelephone(telephone);
		order.setState(1);
		System.out.println(address);
		// 更新order
		os.update(order);

		request.setAttribute("msg", "您的订单号为:" + oid + ",金额为:¥" + order.getTotal() + "已经支付成功,等待发货~~");
		request.getRequestDispatcher("msg.jsp").forward(request, response);
	}

	/**
	 * 编辑订单
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void edit(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String oid = request.getParameter("oid");

		// 通过id获取order
		OrderServiceImpl os = new OrderServiceImpl();
		Order order = os.findByOid(oid);
		request.setAttribute("bean", order);
		System.out.println(order.getAddress());

		// 4.请求转发
		request.getRequestDispatcher("edit_order.jsp").forward(request, response);
	}

	private void confirmEdit(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 1.获取参数//接受参数
		String address = request.getParameter("address");
		String name = request.getParameter("name");
		String telephone = request.getParameter("telephone");
		String oid = request.getParameter("oid");

		// 通过id获取order
		OrderServiceImpl os = new OrderServiceImpl();
		Order order = os.findByOid(oid);

		order.setAddress(address);
		order.setName(name);
		order.setTelephone(telephone);
		order.setState(order.getState());
		System.out.println(order.getAddress());
		// 更新order
		os.update(order);

		request.setAttribute("msg", "您的订单号为:<" + oid +  ">的订单已经修改成功！！");
		request.getRequestDispatcher("msg.jsp").forward(request, response);
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void cancel(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String oid = request.getParameter("oid");
		OrderServiceImpl os = new OrderServiceImpl();
		os.delate(oid);
		request.setAttribute("msg", "您的订单号为:" + oid +  "的订单已经取消成功！！");
		request.getRequestDispatcher("msg.jsp").forward(request, response);
	}
	
	private void reback(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String oid = request.getParameter("oid");

		// 通过id获取order
		OrderServiceImpl os = new OrderServiceImpl();
		Order order = os.findByOid(oid);

		order.setAddress(order.getAddress());
		order.setName(order.getName());
		order.setTelephone(order.getTelephone());
		order.setState(2);
//		System.out.println();
		// 更新order
		os.update(order);

		request.setAttribute("msg", "您的订单号为:" + oid + ",金额为:¥" + order.getTotal() + "已经申请退款,请耐心等待哦~~~");
		request.getRequestDispatcher("msg.jsp").forward(request, response);
	}
}
