<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title></title>
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
	<div class="container">
		<!-- 静态包含 -->
		<%@include file="/head.jsp"%>
	</div>
	<div class="container">
		<div class="row">
			<div
				style="border: 1px solid #e4e4e4; width: 930px; margin-bottom: 10px; margin: 0 auto; padding: 10px; margin-bottom: 10px;">
				<a href="goindex.jsp">首页&nbsp;&nbsp;&gt;</a> <a href="#">商品详情&nbsp;&nbsp;&gt;</a>
			</div>

			<div style="margin: 0 auto; width: 950px;">
				<div class="col-md-6">
					<img style="opacity: 1; width: 360px; height: 330px;" title=""
						class="medium"
						src="${pageContext.request.contextPath}/img/${pro.pimage}">
				</div>

				<div class="col-md-6">
					<form id="formId" action="${pageContext.request.contextPath}/cart?method=addCart" method="post">
						<input type="hidden" name="pid" value="${pro.pid }">
						<div>
							<strong>${pro.pname }</strong>
						</div>
						<div
							style="border-bottom: 1px dotted #dddddd; width: 350px; margin: 10px 0 10px 0;">
							<div>编号：${pro.pid }</div>
						</div>

						<div style="margin: 10px 0 10px 0;">
							商城价: <strong style="color: #ef0101;">￥：${pro.shop_price }元/份</strong>
							参 考 价：
							<del>￥${pro.market_price }元/份</del>
						</div>

						<div style="margin: 10px 0 10px 0;">
							促销: <a target="_blank" title="限时抢购 (2014-07-30 ~ 2015-01-01)"
								style="background-color: #f07373;">限时抢购</a>
						</div>

						<div
							style="padding: 10px; border: 1px solid #e7dbb1; width: 330px; margin: 15px 0 10px 0;; background-color: #fffee6;">
							<div style="margin: 5px 0 10px 0;">白色</div>

							<div
								style="border-bottom: 1px solid #faeac7; margin-top: 20px; padding-left: 10px;">
								购买数量: <input id="quantity" name="count" value="1" maxlength="4"
									size="10" type="text">
							</div>

							<div style="margin: 20px 0 10px 0;; text-align: center;">
								<a href="javascript:void(0)" onclick="addCart()"> <input
									style="background: url('${pageContext.request.contextPath}/images/product.gif') no-repeat scroll 0 -600px rgba(0, 0, 0, 0);height:36px;width:127px;"
									value="加入购物车" type="button">
								</a> &nbsp;收藏商品
							</div>
						</div>
					</form>
				</div>
			</div>
			<div class="clear"></div>
			<div style="width: 950px; margin: 0 auto;">
				<div
					style="background-color: #d3d3d3; width: 930px; padding: 10px 10px; margin: 10px 0 10px 0;">
					<strong>商品介绍</strong>
				</div>

				<div>${pro.pdesc }</div>

				<div
					style="background-color: #d3d3d3; width: 930px; padding: 10px 10px; margin: 10px 0 10px 0;">
					<strong>商品参数</strong>
				</div>
				<div style="margin-top: 10px; width: 900px;">
					<table class="table table-bordered">
						<tbody>
							<tr class="active">
								<th colspan="2">基本参数</th>
							</tr>
							<tr>
								<th width="10%">级别</th>
								<td width="30%">标准</td>
							</tr>
							<tr>
								<th width="10%">标重</th>
								<td>500</td>
							</tr>
							<tr>
								<th width="10%">浮动</th>
								<td>200</td>
							</tr>
						</tbody>
					</table>
				</div>

				<div style="background-color: #d3d3d3; width: 900px;">
					<table class="table table-bordered">
						<tbody>
							<tr class="active">
								<th><strong>商品评论</strong></th>
							</tr>
							<tr class="warning">
								<th>暂无商品评论信息 <a>[发表商品评论]</a></th>
							</tr>
						</tbody>
					</table>
				</div>

				<div style="background-color: #d3d3d3; width: 900px;">
					<table class="table table-bordered">
						<tbody>
							<tr class="active">
								<th><strong>商品咨询</strong></th>
							</tr>
							<tr class="warning">
								<th>暂无商品咨询信息 <a>[发表商品咨询]</a></th>
							</tr>
						</tbody>
					</table>
				</div>
			</div>

		</div>
	</div>
	<div class="container">
		<hr style="height: 1px; border: none; border-top: 1px solid gray;" />
	</div>


	<!--foot-->
	<div class="row">
		<div style="text-align: center; margin-top: 5px;">
			<ul class="list-inline">
				<li><a href="#">关于我们</a></li>
				<li><a>联系我们</a></li>
				<li><a>招贤纳士</a></li>
				<li><a>法律声明</a></li>
				<li><a>友情链接</a></li>
				<li><a>支付方式</a></li>
				<li><a>配送方式</a></li>
				<li><a>服务声明</a></li>
				<li><a>广告声明</a></li>
			</ul>
		</div>
		<p align="center">Copyright &copy; 2018 JavaWeb课程设计</p>
	</div>
</body>
<script type="text/javascript">
	function addCart() {
		//将表单提交
		document.getElementById("formId").submit();
	}
</script>
</html>