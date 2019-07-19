<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!--logo
				
				小屏幕 2个一行 
				超小屏幕一个一行
			-->
<div class="row">
	<div class="col-md-4 col-sm-6 col-xs-12">
		<img style="padding-top: 5px; padding-left: 20px"
			src="img/header1.png" />
	</div>
	<div class="col-md-4 col-sm-6 col-xs-12">
		<img src="img/header.jpg" />
	</div>
	<div class="col-md-3" style="padding-top: 20px">
		<ol class="list-inline">
			<c:if test="${empty user }">
				<li><a href="Login.html">登录</a></li>
				<li><a href="register.html">注册</a></li>
			</c:if>
			<c:if test="${not empty user }">
							Hi!&nbsp${user.username }
							<li><a href="${pageContext.request.contextPath }/legend">退出</a></li>
				<li><a href="${pageContext.request.contextPath }/order?method=orderList&currentPage=1">我的订单</a></li>
			</c:if>
			<li><a href="${pageContext.request.contextPath }/store_cart.jsp">购物车</a></li>
		</ol>
	</div>
</div>



<!--导航条-->
<div class="row">
	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
					aria-expanded="false">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand"
					href="${pageContext.request.contextPath}/index">首页</a>
			</div>

			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul id="menuId" class="nav navbar-nav">
					<c:forEach items="${clist}" var="c">
						<li><a href="#">${c.cname}</a></li>
					</c:forEach>
				</ul>
				<form class="navbar-form navbar-left pull-right" role="search">
					<div class="form-group">
						<input type="text" class="form-control" placeholder="Search">
					</div>
					<button type="submit" class="btn btn-default">Submit</button>
				</form>

			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container-fluid -->
	</nav>
</div>

<script>
	$(function() {
		//发送ajax请求
		$
				.get(
						//请求categoryServlet
						"${pageContext.request.contextPath}/category",
						function(data) {
							//获取menu的ul标签
							var $ul = $("#menuId");

							//遍历数组
							$(data)
									.each(
											function() {
												$ul
														.append($("<li><a href='${pageContext.request.contextPath}/product?cid="
																+ this.cid
																+ "&currPage=1'>"
																+ this.cname
																+ "</a></li>"));
											});
						}, "json");
	});
</script>