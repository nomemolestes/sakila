<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "java.util.*"%>
<%@ page import = "dao.*"%>
<%@ page import = "vo.*"%>
<%
	String category = request.getParameter("category");
	String title = request.getParameter("title");

	double price = -1; // price 데이터가 입력되지 않았을때
	if(!request.getParameter("price").equals("")) {
		price = Double.parseDouble(request.getParameter("price"));
	}
	int length = -1; // length 데이터가 입력되지 않았을때
	if(!request.getParameter("length").equals("")) {
		length = Integer.parseInt(request.getParameter("length"));
	}
	String rating = request.getParameter("rating");
	String actors = request.getParameter("actors");
	
	//페이징, 묶어서 처리, 이전 다음페이지로 넘길떄 값도 함께 바껴야함
		int currentPage = 1;//현재페이지의 기본값은 1임
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));//받아온 값을 정수타입으로 바꿈
		}
		System.out.println("currentPage");//디버깅
		int rowPerPage = 10;//한페이지의 보여줄 글의 수
		
		int beginRow = 0;
		beginRow = (currentPage-1)*rowPerPage;//알고리즘, 시작페이지
		System.out.println("beginRow");//디버깅
		
		//호출 !
		FilmDao filmDao = new FilmDao();
		List<FilmList> list = filmDao.selectFilmListSearch(beginRow ,rowPerPage ,category, rating, price, length, title, actor);
		System.out.println(list.size()); // 0

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table border="1">
	<thead>
		<tr>
			<td>FID</td>
			<td>title</td>
			<td>description</td>
			<td>category</td>
			<td>price</td>
			<td>length</td>
			<td>rating</td>
			<td>actors</td>
		</tr>
	</thead>
	<tbody>
		<%
		for(FilmList f : list) {
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
		</tbody>
	</table>
</body>
</html>
