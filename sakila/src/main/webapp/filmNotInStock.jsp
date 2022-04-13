<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "java.util.*" %>
<%@ page import = "dao.*" %>
<%@ page import = "vo.*" %>
<%
	//초기화
	int filmId = 0;
	int storeId = 0;
	int count = 0;
	//유효성검사
	if(request.getParameter("filmId") != null) {
		filmId = Integer.parseInt(request.getParameter("filmId"));
	}
	if(request.getParameter("storeId") != null) {
		storeId =Integer.parseInt(request.getParameter("storeId"));
	}
	//호출
	FilmInStockDao filmInStockDao = new FilmInStockDao();
	Map<String, Object> map = filmInStockDao.filmNotInStockCall(filmId, storeId);
	List<Integer> invontoryList = (List<Integer>)(map.get("list"));
	count = (Integer)map.get("count");
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>filmNotInStock</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
	<button type="button" class="btn btn-outline-dark">
		<a href="<%=request.getContextPath()%>/index.jsp">index</a>
	</button>
	<div class="jumbotron">
		<h2>filmNotInStock</h2>
	</div>
	  <table class="table table-dark table-striped">
	  	<thead>
	  		<tr>
	  			<td>상점번호</td>
	  			<td>재고목록</td>
	  			<td>부족한재고수량</td>
	  		</tr>
	  	</thead>
	  </table>
	  <tbody>
	  	<%
	  		for(int i : invontoryList) {
	  	%>
	  		<tr>
	  			<td><%=storeId%></td>
	  			<td><%=i%></td>
	  			<td><%=count%></td>
	  		</tr>
	  	<%		
	  		}
	  	%>
	  </tbody>
		
</body>
</html>