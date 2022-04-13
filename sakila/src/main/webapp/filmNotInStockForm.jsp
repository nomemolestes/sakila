<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>filmNotInStockForm</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
	<button type="button" class="btn btn-outline-dark">
		<a href="<%=request.getContextPath()%>/index.jsp">index</a>
	</button>
	<div class="jumbotron">
		<h2>filmNotInStockForm</h2>
	</div>
		<form action="<%=request.getContextPath()%>/filmNotInStock.jsp" method="post"></form>
  				<table class="table table-hover">
					<tr>
						<td>filmId</td>
						<td>
							<input type="text" name="filmId">
						</td>
					</tr>
					<tr>
						<td>storeId</td>
						<td>
							<input type="text" name="storeId">
						</td>
					</tr>
				</table>
				<button type="submit">검색</button>	
</body>
</html>