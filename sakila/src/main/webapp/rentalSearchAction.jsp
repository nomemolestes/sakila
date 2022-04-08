<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dao.*" %>
<%@ page import="java.util.*" %>
<%
	//form을 기준으로 rental dao를 호출해서 action을 출력
	//값 받아오기
	int storeId = -1; //입력되지 않았을때
	if(request.getParameter("storeId") != null){ //스토어ID를 선택했다면 선택한값 받아서 int로 형변환
		storeId = Integer.parseInt(request.getParameter("storeId"));
	}
	String customerName = request.getParameter("customerName");
		System.out.println(customerName + "<-customerName");
		
	String beginDate = request.getParameter("beginDate");
		System.out.println(beginDate + "<-beginDate");
	String endDate = request.getParameter("endDate");
		System.out.println(endDate + "<-endDate");
	
	//페이징
	int currentPage = 1;
	if(request.getParameter("currentPage") != null){
		currentPage = Integer.parseInt(request.getParameter("currentPage"));
		System.out.println(currentPage + "<-currentPage");
	}
	int rowPerPage = 10;
	if(request.getParameter("rowPerPage") != null){
		rowPerPage = Integer.parseInt(request.getParameter("rowPerPage"));
		System.out.println(rowPerPage + "<-rowPerPage");
	}
	int beginRow = (currentPage -1) * rowPerPage;
	System.out.println(beginRow + "<-beginRow");
	//호출
	RentalDao rentalDao = new RentalDao();
	List<Map<String, Object>> list = rentalDao.selectRentalSearchList(storeId, customerName, beginDate, endDate, beginRow, rowPerPage);
	int totalRow = rentalDao.selectRentalTotalRow(storeId, customerName, beginDate, endDate);
	int lastPage = (int)(Math.ceil((double)totalRow / (double)rowPerPage));//형변환
	
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>rentalSearchAction</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
	<button type="button" class="btn btn-outline-dark">
		<a href="<%=request.getContextPath()%>/index.jsp">index</a>
	</button>
	<div class="jumbotron">
			<h3>rentalSearch</h3>
		</div>
		<table class="table table-hover">
			<thead>
				<tr>
					<th>rentalId</th>
					<th>storeId</th>
					<th>staffId</th>
					<th>inventoryId</th>
					<th>customerId</th>
					<th>customerName</th>
					<th>rentalDate</th>
					<th>returnDate</th>
					<th>filmId</th>
					<th>title</th>

				</tr>
			</thead>
			<tbody>
				<%
					for(Map<String,Object> map : list){
				%>
					<tr>
						<td><%=map.get("rentalId")%></td>
						<td><%=map.get("storeId")%></td>
						<td><%=map.get("staffId")%></td>
						<td><%=map.get("inventoryId")%></td>
						<td><%=map.get("customerId")%></td>
						<td><%=map.get("customerName")%></td>
						<td><%=map.get("rentalDate")%></td>
						<td><%=map.get("returnDate")%></td>
						<td><%=map.get("filmId")%></td>
						<td><%=map.get("title")%></td>
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
					<a href="<%=request.getContextPath()%>/rentalSearchAction.jsp?currentPage=<%=currentPage-1%>&storeId=<%=storeId%>&customerName=<%=customerName%>&beginDate=<%=beginDate%>&endDate=<%=endDate%>" class="btn btn-outline-info">이전</a>
			<%
				}
				if(currentPage < lastPage) {
			%>
					<a href="<%=request.getContextPath()%>/rentalSearchAction.jsp?currentPage=<%=currentPage+1%>&storeId=<%=storeId%>&customerName=<%=customerName%>&beginDate=<%=beginDate%>&endDate=<%=endDate%>" class="btn btn-outline-info">다음</a>
			<%
				}
			%>
		</div>
</body>
</html> 