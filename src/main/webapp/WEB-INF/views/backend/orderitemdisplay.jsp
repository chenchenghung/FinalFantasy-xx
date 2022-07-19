<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> -->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width,initial-scale=1, maximum-scale=3, minimum-scale=1, user-scalable=no">
<link rel="stylesheet" type="text/css" href="../css/table.css" />

</head>
<body>
	<div class="navbar">
	<form action="<c:url value="/secure/login.controller" />"method="get">
		<h1>
			後台管理系統
			<button name="log" value="Logout">登出</button>
			
		</h1>
	</form>
	</div>
	<br>
	<hr>
	<div class="sidebar">
		<form action="<c:url value="/pages/product.controller" />"
			method="get">
			<button name="prodaction" value="Select">商品管理</button>
		</form>
		<br>
		<form action="<c:url value="/pages/staff.controller" />" method="get">
			<button name="staffction" value="Select">員工管理</button>
		</form>
		<br>
		<form action="<c:url value="/pages/orders.controller" />" method="get">
			<button name="orderction" value="Select">訂單管理</button>
		</form>
	</div>
	<div class="sqlContentBox">
		<div class="context">
			<c:if test="${not empty show}">
				<table>
					<thead>
						<tr>
							<th>訂單明細編號</th>
							<th>訂單編號</th>
							<th>商品編號</th>
							<th>首圖</th>
							<th>商品名稱</th>
							<th>價格</th>
							<th>數量</th>
							<th>折扣</th>
						</tr>
					</thead>
					<tbody>

						<c:forEach var="row" items="${show}">
							<c:url value="/backend/orderitem.view" var="path">
								<c:param name="id" value="${row.id}" />
								<c:param name="orderID" value="${row.orderid}" />
								<c:param name="pid" value="${row.pid}" />
								<c:param name="picmain" value="${row.picmain}" />
								<c:param name="productname" value="${row.productname}" />
								<c:param name="price" value="${row.price}" />
								<c:param name="qty" value="${row.qty}" />
								<c:param name="discount" value="${row.discount}" />
							</c:url>
							<tr>
								<td>${row.id}</td>
								<td>${row.orderid}</td>
								<td>${row.pid}</td>
								<td><img src="${row.picmain}" style="width:150px"/></td>
								<td>${row.productname}</td>
								<td>${row.price}</td>
								<td>${row.qty}</td>
								<td>${row.discount}</td>
							</tr>
						</c:forEach>

					</tbody>
				</table>
			</c:if>
			<table>
				<tr>
					<td>
						<button>
							<a href="<c:url value="/backend/orderiteminsert.view" />">新增</a>
						</button>
					</td>
				</tr>
			</table>
		</div>
	</div>

</body>

</html>