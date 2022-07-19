<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> -->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

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
			<form action="<c:url value="/pages/productinsertimage.controller" />"
				method="post" enctype="multipart/form-data">
				<table>
					<th>商品新增</th>
					<th/>
					<th/>
					<tr>
						<td>商品編號 :</td>
						<td><input type="text" name="pid" value="${param.pid}"></td>
						<td><span class="error">${errors.pid}</span></td>
					</tr>
					<tr>
						<td>首圖 :</td>
						<td><input type="file" name="upload" /></td>
						<td><span class="error">${errors.picmain}</span></td>
					</tr>
					<tr>
						<td>圖一 :</td>
						<td><input type="file" name="upload1" /></td>
						<td><span class="error">${errors.picfirst}</span></td>
					</tr>
					<tr>
						<td>圖二 :</td>
						<td><input type="file" name="upload2" /></td>
						<td><span class="error">${errors.picsecond}</span></td>
					</tr>
					<tr>
						<td>圖三 :</td>
						<td><input type="file" name="upload3" /></td>
						<td><span class="error">${errors.picthird}</span></td>
					</tr>
					<tr>
						<td>圖四 :</td>
						<td><input type="file" name="upload4" /></td>
						<td><span class="error">${errors.picfourth}</span></td>
					</tr>
					<tr>
						<td>商品名稱 :</td>
						<td><input type="text" name="productname" value="${param.productname}"></td>
						<td><span class="error">${errors.productname}</span></td>
					</tr>
					<tr>
						<td>商品描述 :</td>
						<td><input type="text" name="productdesc" value="${param.productdesc}"></td>
						<td><span class="error">${errors.productdesc}</span></td>
					</tr>
					<tr>
						<td>商品分類 :</td>
						<td><input type="text" name="cateid" value="${param.cateid}"></td>
						<td><span class="error">${errors.cateid}</span></td>
					</tr>
					<tr>
						<td>顏色 :</td>
						<td><input type="text" name="spec" value="${param.spec}"></td>
						<td><span class="error">${errors.spec}</span></td>
					</tr>
					<tr>
						<td>材質 :</td>
						<td><input type="text" name="material"
							value="${param.material}"></td>
						<td><span class="error">${errors.material}</span></td>
					</tr>
					<tr>
						<td>價格 :</td>
						<td><input type="text" name="price" value="${param.price}"></td>
						<td><span class="error">${errors.price}</span></td>
					</tr>
					<tr>
						<td>尺寸 :</td>
						<td><input type="text" name="size" value="${param.size}"></td>
						<td><span class="error">${errors.size}</span></td>
					</tr>
					<tr>
						<td>重量 :</td>
						<td><input type="text" name="weight" value="${param.weight}"></td>
						<td><span class="error">${errors.weight}</span></td>
					</tr>
					<tr>
						<td><input type="submit" name="prodaction" value="Insert">
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


</body>
</html>