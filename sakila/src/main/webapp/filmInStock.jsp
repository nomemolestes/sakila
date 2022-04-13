<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "java.util.*" %>
<%@ page import = "dao.*" %>
<%@ page import = "vo.*" %>

	<%
	int filmId = 0;//초기화
	if(request.getParameter("filmId") !=null) { //filmId값이 널이 아니라면
		filmId = Integer.parseInt(request.getParameter("filmId"));//요청받은 값은 int타입으로 변경
	}
	int storeId = 0;
	if(request.getParameter("storeId") != null) {
		storeId = Integer.parseInt(request.getParameter("storeId"));
	}
	int count = 0;
	//호출
	FilmInStockDao filmInStockDao = new FilmInStockDao();
	Map<String, Object> map = filmInStockDao.filmInStockCall(filmId, storeId);
	List<Integer> inventoryList = (List<Integer>)(map.get("list"));
	count = (Integer)map.get("count");
	
	%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>filmInStock</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
	<button type="button" class="btn btn-outline-dark">
		<a href="<%=request.getContextPath()%>/index.jsp">index</a>
	</button>
		<div class="jumbotron">
  		<h2>filmInStock</h2>
		</div>
		  <table class="table table-dark table-striped">
		  	<thead>
		  		<tr>
		  			<th>상점번호</th>
		  			<td>재고목록</td>
		  			<td>재고수량 : <%=count %></td>
		  		</tr>
		  	</thead>
		  	<tbody>
				<%
					for(int i : inventoryList) {
				%>
					<tr>
						<td><%=storeId %></td>
						<td><%=i%></td>
					</tr>

				<%		
					}
				%>
			</tbody>
		</table>
</body>
</html>