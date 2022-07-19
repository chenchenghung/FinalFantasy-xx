<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> -->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="../css/table.css" />

</head>
<body>
	<div class="navbar">
		<h1>
			後台管理系統
			<button>登出</button>
		</h1>
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
		<form action="<c:url value="/pages/member.controller" />" method="get">
			<button name="memberction" value="Select">會員管理</button>
		</form>
		<br>
		<form action="<c:url value="/pages/orders.controller" />" method="get">
			<button name="orderction" value="Select">訂單管理</button>
		</form>
		<br>
<%-- 		<form action="<c:url value="/pages/orderitem.controller" />" --%>
<!-- 			method="get"> -->
<!-- 			<button name="orderitemction" value="Select">訂單明細管理</button> -->
<!-- 		</form> -->
<!-- 		<br> -->
		<form action="<c:url value="/pages/productsnapshot.controller" />"
			method="get">
			<button name="productsnapshoction" value="Select">商品圖片管理</button>
		</form>
	</div>
	<div class="sqlContentBox">
		<div class="context">
			<c:if test="${not empty select}">
				<table>
					<thead>
						<tr>
							<th>訂單編號</th>
							<th>帳號名稱</th>
							<th>姓名</th>
							<th>電話號碼</th>
							<th>訂單日期</th>
							<th>郵遞區號</th>
							<th>城市</th>
							<th>地址</th>
							<th>訂單金額</th>
							<th>訂單狀況</th>
							<th/>
							<th/>
						</tr>
					</thead>
					<tbody>

						<c:forEach var="row" items="${select}">
							<c:url value="/pages/orders.view" var="path">
								<c:param name="orderid" value="${row.orderid}" />
								<c:param name="uid" value="${row.uid}" />
								<c:param name="ordername" value="${row.ordername}" />
								<c:param name="phone" value="${row.phone}" />
								<c:param name="orderdate" value="${row.orderdate}" />
								<c:param name="code" value="${row.code}" />
								<c:param name="city" value="${row.city}" />
								<c:param name="address" value="${row.address}" />
								<c:param name="totalamount" value="${row.totalamount}" />
								<c:param name="orderstatus" value="${row.orderstatus}" />
							</c:url>
							<c:url value="/pages/orderitem.controller" var="show">
								<c:param name="orderID" value="${row.orderid}"></c:param>
								<c:param name="orderitemction" value="Show"></c:param>
							</c:url>
							<tr>
								<td>${row.orderid}</td>
								<td>${row.uid}</td>
								<td>${row.ordername}</td>
								<td>${row.phone}</td>
								<td><fmt:formatDate value="${row.orderdate}" pattern="yyyy-MM-dd"/></td>
								<td>${row.code}</td>
								<td>${row.city}</td>
								<td>${row.address}</td>
								<td>${row.totalamount}</td>
								<td>${row.orderstatus}</td>
								<td><a href="${path}">修改/刪除</a></td>
								<td><a href="${show}">訂單明細</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</c:if>
			<table>
				<tr>
					<td>
						<button>
							<a href="<c:url value="/pages/ordersinsert.view" />">新增</a>
						</button>
					</td>
				</tr>
			</table>
		</div>
	</div>

</body>
</html>