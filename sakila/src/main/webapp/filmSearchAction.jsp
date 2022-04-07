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
	String actor = request.getParameter("actor");
	
	//페이징, 묶어서 처리, 이전 다음페이지로 넘길떄 값도 함께 바껴야함
	int currentPage = 1;//현재페이지의 기본값은 1임
	if(request.getParameter("currentPage") != null) {
		currentPage = Integer.parseInt(request.getParameter("currentPage"));//받아온 값을 정수타입으로 바꿈
	}
	System.out.println(currentPage + "<-currentPage");//디버깅
	int rowPerPage = 10;//한페이지의 보여줄 글의 수
	
	int beginRow = (currentPage-1)*rowPerPage;//알고리즘, 시작페이지
	System.out.println(beginRow + "<-beginRow");//디버깅
	
	int lastPage = 0;
	
	//호출 !
	FilmDao filmDao = new FilmDao();
	List<FilmList> list = filmDao.selectFilmListSearch(beginRow, rowPerPage,  title, category, price, length, rating, actor);
	int totalCount = filmDao.selectFilmSearchTotalRow(category, rating, price, length, title, actor);
	System.out.println(list.size()); // 0
	if (totalCount % rowPerPage == 0) {
		lastPage = totalCount / rowPerPage;	
	} else {
		lastPage =(totalCount / rowPerPage) + 1;
	}
	
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
	<button type="button" class="btn btn-outline-dark">
		<a href="<%=request.getContextPath()%>/index.jsp">index</a>
	</button>
	<div class="jumbotron">
  		<h2>film search</h2>
	</div>
	<form  action="<%=request.getContextPath()%>/filmSearchAction.jsp" method="post">
	  <table class="table table-dark table-striped">
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
			<div> <!-- 현재페이지가 10p였다면 이전은 9p, 다음은 11p -->
		<%
			if(currentPage > 1) { //현재페이지가 1이면 이전페이지가 존재하면 안됨.
		%>
		<button type="button" class="btn btn-outline-secondary">
			<a href="<%=request.getContextPath()%>/filmSearchAction.jsp?currentPage=<%=currentPage-1%>&category=<%=category%>&rating=<%=rating%>&price=<%=price%>&length=<%=length%>&title=<%=title%>&actor=<%=actor%>">이전</a> <!-- 현재페이지값을넘겨줌/현재페이지에서 1을빼줌-->
		</button>
		<%		
			}
		%>
	
		<%
			if(currentPage < lastPage) {
		%>	
			<button type="button" class="btn btn-outline-secondary btn-sm">
				<a href="<%=request.getContextPath()%>/filmSearchAction.jsp?currentPage=<%=currentPage+1%>&category=<%=category%>&rating=<%=rating%>&price=<%=price%>&length=<%=length%>&title=<%=title%>&actor=<%=actor%>">다음</a>	 <!-- 현재페이지값을넘겨줌/현재페이지에서 1을빼줌-->
			</button>
		<%
			}
		%>
	</div>
	
</body>
</html>
