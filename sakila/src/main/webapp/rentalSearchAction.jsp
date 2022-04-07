<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dao.*" %>
<%@ page import="java.util.*" %>
<%
	//form을 기준으로 rental dao를 호출해서 action을 출력
	//값 받아오기
	int storeId = -1;
	if(request.getParameter("storeId") != null ) {
		storeId = Integer.parseInt(request.getParameter("storeId")); //받아온 문자열 숫자값 정수타입으로 변화
	}
	System.out.println(storeId + "<-storeId");//디버깅
	String customerName = request.getParameter("customerName");
	System.out.println(customerName + "<-customerName");//디버깅
	String beginDate = request.getParameter("beginDate");
	System.out.println(beginDate + "<-beginDate");//디버깅
	String endDate = request.getParameter("endDate");
	System.out.println(endDate + "<-endDate");//디버깅

	
	//페이징, 묶어서 처리, 이전 다음페이지로 넘길떄 값도 함께 바껴야함
	int currentPage = 1;//현재페이지의 기본값은 1임
	if(request.getParameter("currentPage") != null) {
		currentPage = Integer.parseInt(request.getParameter("currentPage"));//받아온 값을 정수타입으로 바꿈
		System.out.println(currentPage + "<-currentPage");//디버깅
	}
	int rowPerPage = 10;//한페이지의 보여줄 글의 수
	int beginRow = 0;
	beginRow = (currentPage-1)*rowPerPage;//알고리즘, 시작페이지
	System.out.println(beginRow + "<-beginRow");//디버깅
	//호출
	RentalDao rentaldao = new RentalDao();//dao
	List<Map<String, Object>> rentalList = rentaldao.selectRentalSearchList(beginRow, rowPerPage, storeId, customerName, beginDate, endDate);//결과리스트
	int totalCount = rentaldao.selectRentalSearchTotalRow(storeId, customerName, beginDate, endDate);
	
	int lastPage = 0;
	if (totalCount % rowPerPage == 0) {
		lastPage = totalCount / rowPerPage;	
	} else {
		lastPage =(totalCount / rowPerPage) + 1;
	
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
	  <table class="table table-dark table-striped">
	
			<%
			for(Map<String, Object> m : rentalList) {
			%>
				<tr>
						<td><%=m.get("rentalId")%></td>
						<td><%=m.get("rentalDate")%></td>
						<td><%=m.get("inventoryId")%></td>
						<td><%=m.get("customerId")%></td>
						<td><%=m.get("returnDate")%></td>
						<td><%=m.get("staffId")%></td>
						<td><%=m.get("lastUpdate")%></td>
						<td><%=m.get("cName")%></td>
						<td><%=m.get("storeId")%></td>	
						<td><%=m.get("filmId")%></td>
						<td><%=m.get("title")%></td>
					</tr>
			<%	
			}
			%>				
		</table>
			<div> <!-- 현재페이지가 10p였다면 이전은 9p, 다음은 11p -->
				<%
					if(currentPage > 1) { //현재페이지가 1이면 이전페이지가 존재하면 안됨.
				%>
					<a href="<%=request.getContextPath()%>/rentalSearchAction.jsp?currentPage=<%=currentPage-1%>&storeId=<%=storeId%>&customerName=<%=customerName%>&beginDate=<%=beginDate%>&endDate=<%=endDate%>">이전</a> <!-- 현재페이지값을넘겨줌/현재페이지에서 1을빼줌-->
				<%		
					}
				%>
			
				<%
					if(currentPage < lastPage) {
				%>	
						<a href="<%=request.getContextPath()%>/rentalSearchAction.jsp?currentPage=<%=currentPage+1%>&storeId=<%=storeId%>&customerName=<%=customerName%>&beginDate=<%=beginDate%>&endDate=<%=endDate%>">다음</a> <!-- 현재페이지값을넘겨줌/현재페이지에서 1을더함-->
				<%
					}
				%>
			</div>
</body>
</html>