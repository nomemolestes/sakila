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
	List<Map<String,Object>> list = (List<Map<String, Object>>)map.get("list");
	//디버깅
	System.out.println(minMonthlyPurchase+ "<- minMonthlyPurchase");
	System.out.println(minDollarAmountPurchase+ "<- minDollarAmountPurchase");
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
 	 <table>
      	<tr class="table-warning">
	  		<td>구매건수<%=minMonthlyPurchase%>&nbsp;회&nbsp;&nbsp;구매금액<%=minDollarAmountPurchase%>&nbsp;달러 이상&nbsp;&nbsp; 구매자의 수&nbsp;&nbsp;총 <%=map.get("count")%>명</td>
	  	</tr>
	 </table>
	  	<h4>구매자 정보</h4>
 	 <table class="table table-dark table-hover">
 	 <thead>
	  	<tr>
	  		<th>customerId</th>	
	  		<th>storeId</th>  
	  		<th>firstName</th>  
	  		<th>lastName</th>  
	  		<th>email</th>  
	  		<th>addressId</th> 
	  		<th>active</th>   
	  		<th>createDate</th>  
	  		<th>lastUpdate</th>  		
	  	</tr>
	  	</thead>
	  	<tbody>
	  		<%
				for(Map<String, Object> m : list){
			%>
			<tr>
			<td><%=m.get("customerId") %></td>
			<td><%=m.get("storeId") %></td>
			<td><%=m.get("firstName") %></td>
			<td><%=m.get("lastName") %></td>
			<td><%=m.get("email") %></td>
			<td><%=m.get("addressId") %></td>
			<td><%=m.get("active") %></td>
			<td><%=m.get("createDate") %></td>
			<td><%=m.get("lastUpdate") %></td>
			<%
				}
			%>
		</tr>
		</tbody>
	  </table>
</body>
</html>