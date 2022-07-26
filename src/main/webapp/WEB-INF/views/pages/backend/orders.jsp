<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
	<div class="SqlContentBox">
		<div class="context">
			<form action="<c:url value="/pages/orders.controller" />"
				method="get">
				<table>
					<th>訂單修改/刪除</th>
					<th/>
					<th/>
					<tr>
						<td>訂單編號 :</td>
						<td><input type="text" name="orderid"
							value="${param.orderid}"></td>
						<td><span class="error">${errors.orderid}</span></td>
					</tr>
					<tr>
						<td>帳號名稱 :</td>
						<td><input type="text" name="uid" value="${param.uid}"></td>
						<td><span class="error">${errors.uid}</span></td>
					</tr>

					<tr>
						<td>姓名 :</td>
						<td><input type="text" name="ordername"
							value="${param.ordername}"></td>
						<td><span class="error">${errors.ordername}</span></td>
					</tr>
					<tr>
						<td>電話號碼 :</td>
						<td><input type="text" name="phone" value="${param.phone}"></td>
						<td><span class="error">${errors.phone}</span></td>
					</tr>
					<tr>
						<td>訂單日期 :</td>
						<td><input type="text" name="orderdate"
							value="${param.orderdate}"></td>
						<td><span class="error">${errors.orderdate}</span></td>
					</tr>
					<tr>
						<td>郵遞區號 :</td>
						<td><input type="text" name="code" value="${param.code}"></td>
						<td><span class="error">${errors.code}</span></td>
					</tr>
					<tr>
						<td>城市 :</td>
						<td><input type="text" name="city" value="${param.city}"></td>
						<td><span class="error">${errors.city}</span></td>
					</tr>
					<tr>
						<td>地址 :</td>
						<td><input type="text" name="address"
							value="${param.address}"></td>
						<td><span class="error">${errors.address}</span></td>
					</tr>
					<tr>
						<td>訂單金額 :</td>
						<td><input type="text" name="totalamount"
							value="${param.totalamount}"></td>
						<td><span class="error">${errors.totalamount}</span></td>
					</tr>
					<tr>
						<td>訂單狀況 :</td>
						<td><input type="text" name="orderstatus"
							value="${param.orderstatus}"></td>
						<td><span class="error">${errors.orderstatus}</span></td>
					</tr>
					<tr>
						<td><input type="submit" name="orderction" value="Update">
						</td>
						<td><input type="submit" name="orderction" value="Delete">
							<input type="button" value="Clear" onclick="clearForm()">
						</td>
						<td/>
					</tr>
				</table>

			</form>
		</div>
	</div>
</body>
</html>