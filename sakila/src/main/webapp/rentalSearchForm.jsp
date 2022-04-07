<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@ page import = "dao.*" %>
<%@ page import = "java.util.*" %>
<%
	StoreDao storeDao = new StoreDao();
	List<Integer> storeIdList = storeDao.selectStoreIdList();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>rentalSearchForm</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
	<button type="button" class="btn btn-outline-dark">
		<a href="<%=request.getContextPath()%>/index.jsp">index</a>
	</button>
		<div class="jumbotron">
  	<h2>대여 상세검색</h2>
		</div>
		<form  action="<%=request.getContextPath()%>/rentalSearchAction.jsp" method="post">
	  		<table class="table table-dark table-striped">
	  			<tr>
	  				<td>가게번호</td>
	  				<td>
	  					<%
	  						for(int i : storeIdList) {
	  					%>
	  						<div><input type="radio" name="storeId" value="<%=i%>"><%=i%>번 가게</div>
	  					<%		
	  						}
	  					%>
	  				</td>
	  			</tr>
	  			<tr>
	  				<td>고객이름</td>
	  				<td><input type="text" name="customerName"></td>
	  			</tr>
	  			<tr>
	  				<td>대여일자</td>
	  				<td><input type="date" name="beginDate"></td>
	  			</tr>
	  			<tr>
	  				<td colspan="2">
	  					<button type="submit">검색</button>
	  				</td>
	  			</tr>
	  		</table>
	  	</form>
</body>
</html>