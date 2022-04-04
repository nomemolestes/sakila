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
		<li><a href="<%=request.getContextPath()%>/storeList.jsp">Store List</a></li>
		<li><a href="<%=request.getContextPath()%>/staffList.jsp">Staff List</a></li>
	<!-- 뷰7개 리스트만들기 -->
		<li><a href="<%=request.getContextPath()%>/actorInfoList.jsp">ActorInfo List</a></li>
		<li><a href="<%=request.getContextPath()%>/customerList.jsp">customerList</a></li>
		<li><a href="<%=request.getContextPath()%>/filmList.jsp">filmList</a></li>
		<li><a href="<%=request.getContextPath()%>/nicerButSlowerFilmList.jsp">nicerButSlowerFilmList</a></li>
		<li><a href="<%=request.getContextPath()%>/salesByFilmCategoryList.jsp">salesByFilmCategoryList</a></li>
		<li><a href="<%=request.getContextPath()%>/salesByStoreList.jsp">salesByStoreList</a></li>
		<li><a href="<%=request.getContextPath()%>/staffViewList.jsp">staffViewList</a></li>
	</ol>
</body>
</html>