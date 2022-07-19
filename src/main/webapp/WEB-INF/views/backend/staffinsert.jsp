<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> -->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1, maximum-scale=3, minimum-scale=1, user-scalable=no">
<link rel="stylesheet" type="text/css" href="../css/table.css" />

<title>Product</title>
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
			<form action="<c:url value="/pages/staff.controller" />" method="get">
				<table>
					<th>員工新增</th>
					<th/>
					<th/>
					<tr>
						<td>員工編號 :</td>
						<td><input type="text" name="id" value="${param.id}"></td>
						<td><span class="error">${errors.id}</span></td>
					</tr>
					<tr>
						<td>中文姓名 :</td>
						<td><input type="text" name="name" value="${param.name}"></td>
						<td></td>
					</tr>
					<tr>
						<td>英文姓名 :</td>
						<td><input type="text" name="engname"
							value="${param.engname}"></td>
						<td><span class="error">${errors.engname}</span></td>
					</tr>
					<tr>
						<td>性別 :</td>
						<td><input type="text" name="sex" value="${param.sex}"></td>
						<td><span class="error">${errors.sex}</span></td>
					</tr>
					<tr>
						<td>年齡 :</td>
						<td><input type="text" name="age" value="${param.age}"></td>
						<td><span class="error">${errors.age}</span></td>
					</tr>
					<tr>
						<td>電話號碼 :</td>
						<td><input type="text" name="phone" value="${param.phone}"></td>
						<td><span class="error">${errors.phone}</span></td>
					</tr>
					<tr>
						<td>職位 :</td>
						<td><input type="text" name="position"
							value="${param.position}"></td>
						<td><span class="error">${errors.position}</span></td>
					</tr>
					<tr>
						<td><input type="submit" name="staffction" value="Insert">
						</td>
						<td><input type="button" value="Clear" onclick="clearForm()">
						</td>
						<td/>
					</tr>
				</table>

			</form>
		</div>
	</div>
</body>

</body>
</html>