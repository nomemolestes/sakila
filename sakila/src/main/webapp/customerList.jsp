<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "java.util.*" %>
<%@ page import = "dao.*" %>
<%@ page import = "vo.*" %>
<%
	//페이징, 묶어서 처리, 이전 다음페이지로 넘길떄 값도 함께 바껴야함
	int currentPage = 1;//현재페이지의 기본값은 1임
	if(request.getParameter("currentPage") != null) {
		currentPage = Integer.parseInt(request.getParameter("currentPage"));//받아온 값을 정수타입으로 바꿈
	}
	System.out.println("currentPage");//디버깅
	int rowPerPage = 10;//한페이지의 보여줄 글의 수
	System.out.println("rowPerPage");//디버깅
	
	int beginRow = (currentPage-1)*rowPerPage;//알고리즘, 시작페이지
	System.out.println("beginRow");//디버깅
	
	//호출 !
	CustomerDao customerDao = new CustomerDao();//dao호출 객체생성
	Customer customer = new Customer();//vo호출
	List<Customer> list = customerDao.selectCustomerListByPage(beginRow, rowPerPage);//페이징 메서드 호출
	list = customerDao.selectCustomerListByPage(beginRow, rowPerPage);

	int lastPage = 0;//마지막페이지
	int totalCount = customerDao.selectCustomerTotalRow();//전체행의수반환메서드
	lastPage = (int)(Math.ceil((double)totalCount/(double)rowPerPage));//형변환
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>customerList</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
	<button type="button" class="btn btn-outline-dark">
		<a href="<%=request.getContextPath()%>/index.jsp">index</a>
	</button>
		<div class="jumbotron">
  		<h2>Customer List</h2>
		</div>
		<table>
			<thead>
				<tr>
					<th>ID</th>
					<th>name</th>
					<th>address</th>
					<th>zipCode</th>
					<th>phone</th>
					<th>city</th>
					<th>country</th>
					<th>notes</th>
					<th>SID</th>
				</tr>
			</thead>
			<tbody>
			<%
				for(Customer c : list) {
			%>
				<tr>
					<td><%=c.getId()%></td>
					<td><%=c.getName() %></td>
					<td><%=c.getAddress() %></td>
					<td><%=c.getZipCode() %></td>
					<td><%=c.getPhone() %></td>
					<td><%=c.getCity() %></td>
					<td><%=c.getCountry() %></td>
					<td><%=c.getNotes()%></td>
					<td><%=c.getSID()%></td>
				</tr>
				
				<%
				}
				%>
			</tbody>
		</table>
		<div>
		<%
			if(currentPage > 1) {
		%>
		<button type="button" class="btn btn-outline-secondary">
			<a href="<%=request.getContextPath()%>/customerList.jsp?currentPage=<%=currentPage-1%>">이전</a>
		</button>
		<%
			}
		%>
		<%
			if(currentPage < lastPage) {
		%>
			<button>
				<a href="<%=request.getContextPath()%>/customerList.jsp?currentPage=<%=currentPage+1%>">다음</a>
			</button>
		<%		
			}
		%>
	</div>
</body>
</html>