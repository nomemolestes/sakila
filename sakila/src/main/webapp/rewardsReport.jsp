<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "vo.*" %>
<%@ page import = "dao.*" %>
<%@ page import = "java.util.*" %>
<%
	//초기화
	int minMonthlyPurchase = 0;
	double minDollarAmountPurchase = 0;
	//유효성검사
	if(request.getParameter("minMonthlyPurchase") != null) {
		minMonthlyPurchase = Integer.parseInt(request.getParameter("minMonthlyPurchase"));//값 받아옴
	}
	if(request.getParameter("minDollarAmountPurchase") != null) {
		minDollarAmountPurchase = Double.parseDouble(request.getParameter("minDollarAmountPurchase"));//값 받아옴
	}
	//호출
	RewardDao rewardDao = new RewardDao();
	Map<String, Object> map = rewardDao.rewardsReportCall(minMonthlyPurchase, minDollarAmountPurchase);
	ArrayList<HashMap<String,Object>> list = (ArrayList<HashMap<String, Object>>)(map.get("list"));
	int count = (int) map.get("count");
	//디버깅
	System.out.println(minMonthlyPurchase+ "<- minMonthlyPurchase");
	System.out.println(minDollarAmountPurchase+ "<- minDollarAmountPurchase");
	System.out.println(count+ "<- count");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>rewardsReport</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
	<button type="button" class="btn btn-outline-dark">
		<a href="<%=request.getContextPath()%>/index.jsp">index</a>
	</button>
	<div class="jumbotron">
		<h2>rewardsReport</h2>
	</div>
	  <table class="table table-hover">
	  	<tr>
	  		<td>minMonthlyPurchase</td>
	  		<td><%=minMonthlyPurchase%></td>
	  		<td>minDollarAmountPurchase</td>
	  		<td><%=minDollarAmountPurchase%></td>
	  	</tr>
	  </table>
</body>
</html>