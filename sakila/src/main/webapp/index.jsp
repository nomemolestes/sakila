<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>인덱스</h2>
	<ol>
		<li><a href="<%=request.getContextPath()%>/StoreList.jsp">Store List</a></li>
		<li><a href="<%=request.getContextPath()%>/StaffList.jsp">Staff List</a></li>
	<!-- 뷰7개 리스트만들기 -->
		<li><a href="<%=request.getContextPath()%>/actorInflist.jsp">ActorInfo List</a></li>
		<li><a href="<%=request.getContextPath()%>/customerList.jsp">ActorInfo List</a></li>
		<li><a href="<%=request.getContextPath()%>/filmList.jsp">ActorInfo List</a></li>
		<li><a href="<%=request.getContextPath()%>/nicerButSlowerFilmList.jsp">ActorInfo List</a></li>
		<li><a href="<%=request.getContextPath()%>/salesByFilmCategoryList.jsp">ActorInfo List</a></li>
		<li><a href="<%=request.getContextPath()%>/salesByStoreList.jsp">ActorInfo List</a></li>
		<li><a href="<%=request.getContextPath()%>/staffViewList.jsp">ActorInfo List</a></li>
	
	</ol>
</body>
</html>