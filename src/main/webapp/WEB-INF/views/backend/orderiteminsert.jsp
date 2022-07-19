<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> -->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width,initial-scale=1, maximum-scale=3, minimum-scale=1, user-scalable=no">
<link rel="stylesheet" type="text/css" href="../css/table.css" />

<script type="text/javascript">
	function clearForm() {
		var inputs = document.getElementsByTagName("input");
		for (var i = 0; i < inputs.length; i++) {
			if (inputs[i].type == "text") {
				inputs[i].value = "";
			}
		}
	}
</script>
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
			<form action="<c:url value="/pages/orderitem.controller" />"
				method="get">
				<table>
					<th>訂單明細新增</th>
					<th/>
					<th/>
					<tr>
						<td>訂單明細編號 :</td>
						<td><input type="text" name="id" value="${param.id}"></td>
						<td><span class="error">${errors.id}</span></td>
					</tr>
					<tr>
						<td>訂單編號 :</td>
						<td><input type="text" name="orderid"
							value="${param.orderid}"></td>
						<td><span class="error">${errors.orderid}</span></td>
					</tr>
					<tr>
						<td>商品編號 :</td>
						<td><input type="text" name="pid" value="${param.pid}"></td>
						<td><span class="error">${errors.pid}</span></td>
					</tr>
					<tr>
						<td>首圖 :</td>
						<td><input type="text" name="picmain"
							value="${param.picmain}"></td>
						<td><span class="error">${errors.picmain}</span></td>
					</tr>
					<tr>
						<td>商品名稱 :</td>
						<td><input type="text" name="productname"
							value="${param.productname}"></td>
						<td><span class="error">${errors.productname}</span></td>
					</tr>
					<tr>
						<td>價格 :</td>
						<td><input type="text" name="price" value="${param.price}"></td>
						<td><span class="error">${errors.price}</span></td>
					</tr>
					<tr>
						<td>數量 :</td>
						<td><input type="text" name="qty" value="${param.qty}"></td>
						<td><span class="error">${errors.qty}</span></td>
					</tr>
					<tr>
						<td>折扣 :</td>
						<td><input type="text" name="discount"
							value="${param.discount}"></td>
						<td><span class="error">${errors.discount}</span></td>
					</tr>
					<tr>
						<td><input type="submit" name="orderitemction" value="Insert">
							<input type="button" value="Clear" onclick="clearForm()">
						</td>
						<td/>
						<td/>
					</tr>
				</table>

			</form>

		</div>
	</div>
</body>
</html>