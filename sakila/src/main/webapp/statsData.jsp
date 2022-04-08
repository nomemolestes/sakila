<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dao.*" %>
<%@ page import="java.util.*" %>
<%
	StatsDataDao statsDataDao = new StatsDataDao();
	//customer별 총 amount
	List<Map<String, Object>> amountByCoustomer = statsDataDao.amountByCoustomer();
	//대여료별 빌린횟수
	List<Map<String, Object>> amountByRentalRate = statsDataDao.amountByRentalRate();
	//등급별 빌린횟수
	List<Map<String, Object>> amountByRating = statsDataDao.amountByRating();
	//언어별 영화의 수
	List<Map<String, Object>> amountByLanguage = statsDataDao.amountByLanguage();
	//길이별 영화의수(구간;기준을 정해서)
	List<Map<String, Object>> amountByLength = statsDataDao.amountByLength();

	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">

</head>
<body>
	<h2>amountByCustomer(내림차순 상위10명)</h2>
		<table class="table table-hover">
			<tr>
				<th>고객아이디</th>
				<th>고객이름</th>
				<th>총지불액</th>
			</tr>
		<%
			for(Map<String, Object> m : amountByCoustomer) {
		%>
			<tr>
				<td><%=m.get("customerId") %></td>
				<td><%=m.get("name") %></td>
				<td><%=m.get("total") %></td>
			</tr>
		<%		
			}
		%>
		</table>
	<h2>amountByRentalRate</h2>
 		 <table class="table table-dark table-striped">
			<tr>
				<th>대여료</th>
				<th>빌린횟수</th>
			</tr>
		<%
			for(Map<String, Object> m : amountByRentalRate) {
		%>
			<tr>
				<th><%=m.get("rentalRate")%></th>
				<th><%=m.get("cnt")%></th>
			</tr>
		<%		
			}
		%>
		</table>
	<h2>amountByRating</h2>
		<table class="table table-hover">
			<tr>
				<th>등급</th>
				<th>빌린횟수</th>
			</tr>
			<%
				for(Map<String, Object> m : amountByRating) {
			%>
				<tr>
					<th><%=m.get("rating")%></th>
					<th><%=m.get("cnt")%></th>
				</tr>
			<%		
				}
			%>
		</table>
	<h3>amountByLanguage</h3>
		<table class="table table-dark table-striped">
		<tr>
			<th>영화언어</th>
			<th>영화의 수</th>
		</tr>
		<%
			for(Map<String, Object> m : amountByLanguage) {
		%>
			<tr>
				<th><%=m.get("name") %></th>
				<th><%=m.get("cnt") %></th>
			</tr>
		<%		
			}
		%>
		</table>
	<h3>amountByLength</h3>
		<table class="table table-hover">
	<tr>
			<th>런닝타임</th>
			<th>영화의 수</th>
		</tr>
		<%
			for(Map<String, Object> m : amountByLength) {
		%>
			<tr>
				<th><%=m.get("length2") %></th>
				<th><%=m.get("cnt") %></th>
			</tr>
		<%		
			}
		%>		
		</table>
</body>
</html>