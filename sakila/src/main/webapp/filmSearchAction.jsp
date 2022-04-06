<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "dao.*" %>
<%@ page import = "vo.*" %>
<%@ page import = "java.util.*" %>
<%
	//값을 받아옴
	String category = request.getParameter("category");
	String rating = request.getParameter("rating");
	
	double price = -1; //0이 존재가능하므로, price데이턱 입력되지 않았을떄
	if(!request.getParameter("price").equals("")) {//공백이 아니라면
		//price값을 받아와서 더블타입으로 변경함
		price = Double.parseDouble(request.getParameter("price"));
	}
	
	int length = -1;//0이 존재가능하므로, price데이턱 입력되지 않았을떄
	if(!request.getParameter("length").equals("")) {//공백이 아니라면
		//length값을 받아오서 정수타입으로 변경함
		length = Integer.parseInt(request.getParameter("length"));
	}
	
	String title = request.getParameter("title");
	String actors = request.getParameter("actors");
	
	int beginRow = 0;
	int rowPerPage = 10;
	
	//dao호출
	FilmDao filmDao = new FilmDao();
	List<Film> list = filmDao.selectFilmListSearch(beginRow, rowPerPage, category, rating, price, length, title, actors);
	System.out.println(list.size());//0임
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>filmSearchAction</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
	<div class="jumbotron">
  		<h2>filmSearchAction</h2>
	</div>
	  <table class="table table-dark table-striped">
	  <%
	  	for(Film f : list) {
	  %>
	  
			<tr>
				<td><%=f.getFid()%></td> 
				<td><%=f.getTitle()%></td> 
				<td><%=f.getDescription()%></td> 
				<td><%=f.getCategory()%></td> 
				<td><%=f.getPrice()%></td> 
				<td><%=f.getLength()%></td> 
				<td><%=f.getRating()%></td> 
				<td><%=f.getActors()%></td> 
			</tr>	
		<%
	  	}
		%>	
		</table>
</body>
</html>
