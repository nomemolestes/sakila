<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@ page import = "java.util.*" %>
<%@ page import = "vo.*" %>
<%@ page import = "dao.*" %>
<%
	CategoryDao categoryDao = new CategoryDao();
	List<Category> list = categoryDao.selectCategoryList();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>filmSearchForm</title>
</head>
<body>
	<h2>필름 리스트 뷰 검색</h2>
	<form action="<%=request.getContextPath()%>/filmSearchAction.jsp" method="post">
		<!-- 부트스트랩적용필요 -->
		<table>
			<tr>
				<td>category</td> <!-- category table에서 select 必 -->
				<td>
					<select name="categoryName">
						<option value="">카테고리 선택</option>
					<%
						for(Category c : list) {
					%>
						<option value="<%=c.getName()%>"><%=c.getName()%></option>
					<%		
						}
					%>
					</select>
				</td>
			</tr>	
			<tr>	
				<td>rating</td> <!-- SELECT DISTINCT rating
									from film_list / rating 의 길이/설정 값-->
				<td>
					<select name = "rating">
						<option value = "G">G</option>
						<option value = "PG">PG</option>
						<option value = "PG-13">PG-13</option>
						<option value = "R">R</option>
						<option value = "NC-17">NC-17</option>
					</select>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>