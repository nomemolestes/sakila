<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ page import="vo.*" %>
<%@ page import="dao.*" %>
<%@ page import="java.util.*" %>
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
	SalesByFilmCategoryDao sbfcd = new SalesByFilmCategoryDao();
	SalesByFilmCategory sbfc = new SalesByFilmCategory();
	List<SalesByFilmCategory> list = sbfcd.selectSalesByFilmCategoryByPage(beginRow, rowPerPage);
	list = sbfcd.selectSalesByFilmCategoryByPage(beginRow, rowPerPage);
	
	int lastPage = 0;//마지막페이지
	int totalCount = sbfcd.selectSalesByFilmCategoryTotalRow();//전체 글의수, 전체행반환메서드 호출
	lastPage = (int)(Math.ceil((double)totalCount/(double)rowPerPage));//형변환,올림, 입력받은 숫자보다 크거나 같은 정수 중 가장 작은 정수를 리턴
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>salesByFilmCategory</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
	<button type="button">
		<a class="btn btn-outline-dark" href="<%=request.getContextPath()%>/index.jsp">index</a>
	</button>
	<div class="jumbotron">
  		<h2>salesByFilmCategory</h2>
	</div>
	<table>
		<thead>
			<tr>
				<td>category</td>
				<td>totalSales</td>
			</tr>
		</thead>
		<tbody>
			<%
				for(SalesByFilmCategory s : list) {
			%>
				<tr>
					<td><%=s.getCategory() %></td>
					<td><%=s.getTotalSales() %></td>
				</tr>
			<%		
				}
			%>
		</tbody>
	</table>
	
</body>
</html>