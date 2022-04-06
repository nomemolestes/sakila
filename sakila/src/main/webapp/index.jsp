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
	<h4>테이블 리스트</h4>
	<ol>
			<li><a href="<%=request.getContextPath()%>/storeList.jsp">Store List</a></li>
			<li><a href="<%=request.getContextPath()%>/staffList.jsp">Staff List</a></li>
		<!-- 뷰7개 리스트만들기 -->
		<h4>뷰 리스트</h4>
			<li><a href="<%=request.getContextPath()%>/actorInfoList.jsp">ActorInfo List</a></li>
			<li><a href="<%=request.getContextPath()%>/customerList.jsp">customerList</a></li>
	<!-- *******작성중******* -->
			<li><a href="<%=request.getContextPath()%>/filmList.jsp">filmList</a></li>
			<li><a href="<%=request.getContextPath()%>/nicerButSlowerFilmList.jsp">nicerButSlowerFilmList</a></li>
			<li><a href="<%=request.getContextPath()%>/salesByFilmCategoryList.jsp">salesByFilmCategoryList</a></li>
			<li><a href="<%=request.getContextPath()%>/salesByStoreList.jsp">salesByStoreList</a></li>
			<li><a href="<%=request.getContextPath()%>/staffViewList.jsp">staffViewList</a></li>
		<!-- 프로시져 3개 결과화면 -->	
		<h4>프로시져</h4>
			<li><a href="<%=request.getContextPath()%>/filmInStock.jsp">filmInStock</a></li>
			<li><a href="<%=request.getContextPath()%>/filmNotInStock.jsp">filmNotInStock</a></li>
			<li><a href="<%=request.getContextPath()%>/rewardsReport.jsp">rewardsReport</a></li>
		<h4>상세검색</h4>
			<li><a href="<%=request.getContextPath()%>/filmSearchForm.jsp">filmSearchForm</a></li>
			
	</ol>
</body>
</html>