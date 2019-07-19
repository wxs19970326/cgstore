<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>订单详情</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bootstrap.min.css"
	type="text/css" />
<script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"
	type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"
	type="text/javascript"></script>
<!-- 引入自定义css文件 style.css -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/style.css" type="text/css" />
<style>
body {
	margin-top: 20px;
	margin: 0 auto;
}

.carousel-inner .item img {
	width: 100%;
	height: 300px;
}
</style>
</head>

<body>


	<!-- 动态包含 -->
	<div class="container">
		<jsp:include page="/head.jsp"></jsp:include>


		<div class="row">

			<div style="margin: 0 auto; margin-top: 10px; width: 950px;">
				<strong>订单详情</strong>
				<table class="table table-bordered">
					<tbody>
						<tr class="warning">
							<th colspan="5">订单编号:${bean.oid }</th>
						</tr>
						<tr class="warning">
							<th>图片</th>
							<th>商品</th>
							<th>价格</th>
							<th>数量</th>
							<th>小计</th>
						</tr>
						<c:forEach items="${bean.items}" var="oi">
							<tr class="active">
								<td width="60" width="40%"><input type="hidden" name="id"
									value="22"> <img
									src="${pageContext.request.contextPath}/img/${oi.product.pimage}"
									width="70" height="60"></td>
								<td width="30%"><a target="_blank">${oi.product.pname}</a>
								</td>
								<td width="20%">￥${oi.product.shop_price}</td>
								<td width="10%">${oi.count }</td>
								<td width="15%"><span class="subtotal">￥${oi.subtotal}</span>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>

			<div style="text-align: right; margin-right: 120px;">
				商品金额: <strong style="color: #ff6600;">￥${bean.total }元</strong>
			</div>

		</div>

		<div>
			<hr />
			<form id="orderForm" action="${pageContext.request.contextPath}/order?method=confirmEdit&oid=${bean.oid}" method="post" class="form-horizontal"
				style="margin-top: 5px; margin-left: 150px;">
				<div class="form-group">
					<label for="username" class="col-sm-1 control-label">地址</label>
					<div class="col-sm-5">
						<input type="text" class="form-control" id="address"
							name="address" placeholder="请输入收货地址" value="${bean.address }">
					</div>
				</div>
				<div class="form-group">
					<label for="inputPassword3" class="col-sm-1 control-label">收货人</label>
					<div class="col-sm-5">
						<input type="text" name="name" class="form-control" id="name"
							placeholder="请输收货人" value="${bean.name }">
					</div>
				</div>
				<div class="form-group">
					<label for="confirmpwd" class="col-sm-1 control-label">电话</label>
					<div class="col-sm-5">
						<input type="text" name="telephone" class="form-control" id="telephone"
							placeholder="请输入联系方式" value="${bean.telephone }">
					</div>
				</div>

			<hr />


			<hr />
			<p style="text-align: right; margin-right: 100px;">
				<a href="javascript:document.getElementById('orderForm').submit();">
			      <input type="submit"  width="100" value="确认修改" name="submit" border="0"
				    style="background: url('${pageContext.request.contextPath}/images/register.gif') no-repeat scroll 0 0 rgba(0, 0, 0, 0);
				    height:35px;width:100px;color:white;">
				</a>
			</p>
			</form>
			<hr />

		</div>


		<div style="margin-top: 50px;">
			<img src="${pageContext.request.contextPath}/img/footer.jpg"
				width="100%" height="68" alt="我们的优势" title="我们的优势" />
		</div>
	</div>

	<div style="text-align: center; margin-top: 5px;">
		<ul class="list-inline">
			<li><a>关于我们</a></li>
			<li><a>联系我们</a></li>
			<li><a>招贤纳士</a></li>
			<li><a>法律声明</a></li>
			<li><a>友情链接</a></li>
			<li><a target="_blank">支付方式</a></li>
			<li><a target="_blank">配送方式</a></li>
			<li><a>服务声明</a></li>
			<li><a>广告声明</a></li>
		</ul>
	</div>
	<div style="text-align: center; margin-top: 5px; margin-bottom: 20px;">
		Copyright &copy; 2018 JavaWeb课程设计</div>

</body>

</html>