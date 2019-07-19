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
</head>
<body>
	<div class="container">

		<!-- 静态包含 -->
		<%@include file="/head.jsp"%>
		<div align="center">
			<div class="row" style="width: 1210px; margin: 0 auto;">
				<c:forEach items="${pb.list }" var="p">
					<div class="col-md-2">
						<a href="${pageContext.request.contextPath}/info?pid=${p.pid}">
							<img src="${pageContext.request.contextPath}/img/${p.pimage}"
							width="170" height="170" style="display: inline-block;">
						</a>

						<p>
							<a href="${pageContext.request.contextPath}/info?pid=${p.pid}"
								style='color: red'>${fn:substring(p.pname,0,10) }...</a>
						</p>
						<p>
							<font color="#FF0000">商城价：&yen;${p.shop_price }</font>
						</p>
					</div>
				</c:forEach>



			</div>

			<!--分页 -->
			<div style="width: 380px; margin: 0 auto; margin-top: 50px;">
				<ul class="pagination" style="text-align: center; margin-top: 10px;">
					<!-- 判断当前页是否是首页  -->
					<c:if test="${pb.currPage == 1 }">
						<li class="disabled"><a href="javascript:void(0)"
							aria-label="Previous"><span aria-hidden="true">&laquo;</span></a>
						</li>
					</c:if>

					<c:if test="${pb.currPage != 1 }">
						<li><a
							href="${pageContext.request.contextPath}/product?currPage=${pb.currPage-1}&cid=${param.cid}"
							aria-label="Previous"><span aria-hidden="true">&laquo;</span></a>
						</li>
					</c:if>



					<!-- 展示所有页码 -->
					<c:forEach begin="${pb.currPage-5>0?pb.currPage-5:1 }"
						end="${pb.currPage+4>pb.totalPage?pb.totalPage:pb.currPage+4 }"
						var="n">
						<!-- 判断是否是当前页 -->
						<c:if test="${pb.currPage==n }">
							<li class="active"><a href="javascript:void(0)">${n }</a></li>
						</c:if>
						<c:if test="${pb.currPage!=n }">
							<li><a
								href="${pageContext.request.contextPath}/product?currPage=${n}&cid=${param.cid}">${n }</a></li>
						</c:if>
					</c:forEach>


					<!-- 判断是否是最后一页 -->
					<c:if test="${pb.currPage == pb.totalPage }">
						<li class="disabled"><a href="javascript:void(0)"
							aria-label="Next"> <span aria-hidden="true">&raquo;</span>
						</a></li>
					</c:if>
					<c:if test="${pb.currPage != pb.totalPage }">
						<li><a
							href="${pageContext.request.contextPath}/product?currPage=${pb.currPage+1}&cid=${param.cid}"
							aria-label="Next"> <span aria-hidden="true">&raquo;</span>
						</a></li>
					</c:if>

				</ul>
			</div>
		</div>

		<!-- <hr style="height: 1px; border: none; border-top: 1px solid gray;" /> -->

		<!--foot-->
		<div class="row">
			<div style="margin-top:0px;">
					<img src="${pageContext.request.contextPath}/img/footer.jpg" width="100%" height="68" alt="我们的优势" title="我们的优势" />
				</div>
			<div style="text-align: center;margin-top: 5px;">
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
	</div>
</body>
</html>