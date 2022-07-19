<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> -->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1, maximum-scale=3, minimum-scale=1, user-scalable=no">
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
							<th>員工編號</th>
							<th>英文姓名</th>
							<th>中文姓名</th>
							<th>性別</th>
							<th>年齡</th>
							<th>職位</th>
							<th/>
						</tr>
					</thead>
					<tbody>

						<c:forEach var="row" items="${select}">
							<c:url value="/pages/staff.view" var="path">
								<c:param name="id" value="${row.id}" />
								<c:param name="engname" value="${row.engname}" />
								<c:param name="name" value="${row.name}" />
								<c:param name="sex" value="${row.sex}" />
								<c:param name="age" value="${row.age}" />
								<c:param name="position" value="${row.position}" />
							</c:url>
							<tr>
								<td>${row.id}</td>
								<td>${row.name}</td>
								<td>${row.engname}</td>
								<td>${row.sex}</td>
								<td>${row.age}</td>
								<td>${row.position}</td>
								<td><a href="${path}">修改/刪除</a></td>
							</tr>
						</c:forEach>

					</tbody>
				</table>
			</c:if>
			<table>
				<tr>
					<td>
						<button>
							<a href="<c:url value="/pages/staffinsert.view" />">新增</a>
						</button>
					</td>
				</tr>
			</table>

		</div>
	</div>

</body>
</html>