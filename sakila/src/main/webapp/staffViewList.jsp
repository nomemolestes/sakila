<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="vo.*" %>
<%@ page import="dao.*" %>
<%@ page import="java.util.*" %>
<%
	//페이징 묶어서처리, 이전 다음페이지 넘길때 값이 함꼐
	int currentPage = 1;//현재페이지의 기본값
	if(request.getParameter("currentPage") != null) {//현재페이지 값이 널이 아니면

	currentPage = Integer.parseInt(request.getParameter("currentpage"));
	System.out.println(currentPage + "<-currentPage");//디버깅	
	}
	int rowPerPage = 10;//한페이지에 보여줄 글의 목록수
	int beginRow = (currentPage-1)*rowPerPage;//알고리즘
	System.out.println(beginRow + "<-beginRow");//디버깅
	//호출
	StaffViewDao svd = new StaffViewDao();//dao
	StaffViewList svl = new StaffViewList(); //vo
	List<StaffViewList> list = svd.selectStaffViewListByPage(beginRow, rowPerPage);
	list = svd.selectStaffViewListByPage(beginRow, rowPerPage);
			
	int lastPage = 0;//마지막페이지
	int totalCount = svd.selectSalesByStoreTotalRow();//전체 글의수, 전체행반환메서드 호출
	lastPage = (int)(Math.ceil((double)totalCount/(double)rowPerPage));//형변환,올림, 입력받은 숫자보다 크거나 같은 정수 중 가장 작은 정수를 리턴
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>staffViewList</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
	<button type="button" >
		<a href="<%=request.getContextPath()%>/index.jsp">index</a>
	</button>
	<div class="jumbotron">
  		<h2>staffViewList</h2>
	</div>
	<table>
		<thead>
      <tr>
				<td>ID</td>
				<td>name</td>
				<td>address</td>
				<td>zipCode</td>
				<td>phone</td>
				<td>city</td>
				<td>country</td>
				<td>SID</td>
			</tr>
		</thead>
		<tbody>
		<%
			for(StaffViewList s : list) {
		%>
      <tr>
				<td><%=s.getId() %></td>
				<td><%=s.getName() %></td>
				<td><%=s.getAddress() %></td>
				<td><%=s.getZipCode() %></td>
				<td><%=s.getPhone() %></td>
				<td><%=s.getCity() %></td>
				<td><%=s.getCountry() %></td>
				<td><%=s.getSid() %></td>
			</tr>
		<%		
			}
		%>
			
		</tbody>
	</table>	
</body>
</html>