<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>rewardsReportForm</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
	<button type="button" class="btn btn-outline-dark">
		<a href="<%=request.getContextPath()%>/index.jsp">index</a>
	</button>
	<div class="jumbotron">
		<h2>rewardsReportForm</h2>
	</div>
	  <table class="table table-dark table-striped">
	  	<form action ="<%=request.getContextPath()%>/rewardsReport.jsp" method = "post">
	  		<table class = "table table-hover">
	  			<tr>
	  				<td>minMonthlyPurchase</td>
	  				<td>
	  					<input type="text" name="minMonthlyPurchase">
	  				</td>	
	  				<td>minDollarAmountPurchase</td>
	  				<td>
	  					<input type="text" name="minDollarAmountPurchase">
	  				</td>
	  			</tr>
	  		</table>
	  		<button type="submit">검색</button>
	  	</form>
	  </table>
</body>
</html>