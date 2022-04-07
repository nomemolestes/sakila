<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dao.*" %>
<%@ page import="java.util.*" %>
<%
	//form을 기준으로 rental dao를 호출해서 action을 출력
	storeId
	customerName
	beginDate
	endDate
	
	RentalDao rentaldao = new  Rentaldao();
	List<Map<String, Object>> list = rental.RenselectRentalSearchList(storeId, customerName, beginDate, endDate);
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	for(Map<String, Object> m : list) {
	
</body>
</html>