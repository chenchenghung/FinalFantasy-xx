<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> -->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1, maximum-scale=3, minimum-scale=1, user-scalable=no">
<link rel="stylesheet" type="text/css" href="css/page.css" />
<title>Home</title>
</head>
<body>
<div class="bg">
	<div class="navbar">
	<form action="<c:url value="/secure/login.controller" />"
						method="get">
		<h1>
			後台管理系統
			<button name="log" value="Logout">登出</button>
			
		</h1>
	</form>
	</div>
	<br>
	<hr>
	<div class="sidebar">
		<table>
			<tr>
				<td>
					<form action="<c:url value="/pages/product.controller" />"
						method="get">
						<button name="prodaction" value="Select">商品管理</button>
					</form> <br>
					<form action="<c:url value="/pages/staff.controller" />"
						method="get">
						<button name="staffction" value="Select">員工管理</button>
					</form> <br>
					<form action="<c:url value="/pages/orders.controller" />"
						method="get">
						<button name="orderction" value="Select">訂單管理</button>
					</form>
				</td>
			</tr>
		</table>
	</div>
	<div class="sqlContentBox">
        <div>
            <div class="context">
        
            </div>
        </div>
    </div>
</div>

</body>
</html>