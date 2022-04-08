<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "java.util.*" %>
<%@ page import = "dao.*" %>
<%@ page import = "vo.*" %>
<%
//페이징 묶어서처리, 이전 다음페이지 넘길때 값이 함꼐
	int currentPage = 1;//현재페이지의 기본값
	if(request.getParameter("currentPage") != null) {//현재페이지 값이 널이 아니면
		//다음페이지로 넘기면 오류, ㅣsql에서 값을 못 받는중......
		currentPage = Integer.parseInt(request.getParameter("currentpage"));
	System.out.println(currentPage + "<-currentPage");//디버깅	
	}
	int rowPerPage = 10;//한페이지에 보여줄 글의 목록수
	int beginRow = (currentPage-1)*rowPerPage;//알고리즘
	System.out.println(beginRow + "<-beginRow");//디버깅
	//호출
	FilmDao filmDao = new FilmDao(); //dao
	FilmList film = new FilmList(); //vo
	List<FilmList> list = filmDao.selectFilmListByPage(beginRow, rowPerPage);//페이징
	list = filmDao.selectFilmListByPage(beginRow, rowPerPage);//페이징
	
	int lastPage = 0;//마지막페이지
	int totalCount = filmDao.selectFilmTotalRow();//전체 글의수, 전체행반환메서드 호출
	lastPage = (int)(Math.ceil((double)totalCount/(double)rowPerPage));//형변환,올림, 입력받은 숫자보다 크거나 같은 정수 중 가장 작은 정수를 리턴
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>filmList</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
	<button type="button" class="btn btn-outline-dark">
		<a href="<%=request.getContextPath()%>/index.jsp">index</a>
	</button>
		<div class="jumbotron">
  		<h2>Film List</h2>
		</div>
		<table>
			<thead>
				<tr class="table-secondary">
					<td>fid</td>
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
				<tr class="table-secondary">
					<td><%=f.getFid()%></td>
					<td><%=f.getTitle() %></td>
					<td><%=f.getDescription() %></td>
					<td><%=f.getCategory() %></td>
					<td><%=f.getPrice() %></td>
					<td><%=f.getLength() %></td>
					<td><%=f.getRating() %></td>
					<td><%=f.getActors() %></td>
				</tr>
			<%
				}
			%>	
			</tbody>
		</table>
		<div>
		<!-- 다음페이지로 넘겨도 값이 나오도록 수정 -->
			<%
				if(currentPage > 1) {
			%>
			<button type="button" class="btn btn-outline-secondary">
					<a href="<%=request.getContextPath()%>/filmList.jsp?currentPage=<%=currentPage-1%>">이전</a>
			</button>	
			<%		
				}
			%>
		<!-- 다음페이지로 넘겨도 값이 나오도록 수정 -->
			<%
				if(currentPage < lastPage) {
			%>
				<button button type="button" class="btn btn-outline-secondary">
					<a href="<%=request.getContextPath()%>/filmList.jsp?currentPage=<%=currentPage+1%>">다음</a>
				</button>
			<%		
				}
			%>
		</div>
</body>
</html>