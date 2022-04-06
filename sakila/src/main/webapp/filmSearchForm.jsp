<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@ page import = "java.util.*" %>
<%@ page import = "vo.*" %>
<%@ page import = "dao.*" %>
<%
	//카테고리선택 dao
	CategoryDao categoryDao = new CategoryDao();
	List<Category> list = categoryDao.selectCategoryList();
	//대여료 dao
	FilmDao filmDao = new FilmDao();
	List<Double> priceList = filmDao.selectFilmPriceList();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>filmSearchForm</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
	<div class="jumbotron">
  		<h2>filmSearchForm</h2>
	</div>
	<form action="<%=request.getContextPath()%>/filmSearchAction.jsp" method="post">
		<!-- 부트스트랩적용필요 -->
	  <table class="table table-dark table-striped">
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
						<option value = "">등급선택</option>
						<option value = "G">G</option>
						<option value = "PG">PG</option>
						<option value = "PG-13">PG-13</option>
						<option value = "R">R</option>
						<option value = "NC-17">NC-17</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>대여료</td>
				<td>
					<div><input type="radio" name="length" value="" checked="checked">선택안함</div>
					<%
						for(Double p : priceList) {
					%>
						<div><input type="radio" name="price" value=<%=p%>"><%=p%></div>
					<%		
						}
					%>
				</td>
			</tr>
			<tr>
				<td>영화시간</td>
				<td> <!-- length가 60미만 & 60이상 -->
					<div><input type="radio" name="length" value="" checked="checked">선택안함</div>
					<div><input type="radio" name="length" value="0">1시간미만</div>
					<div><input type="radio" name="length" value="1">1시간이상</div>
				</td>
			</tr>
			<tr>
				<td>제목 검색</td><!-- 매개값에 따라 다른 쿼리가 선택되는 것은 동적쿼리라고 함 -->
				<td> <!-- 제목검색은 공백을 입력하면  모든 게 나옴, 굳이 가져오지 않아도 됨/하나의 메서드에서 쿼리를 고르는 걸로 작업-->
				<!-- SELECT *
				FROM film_list
				WHERE actors LIKE '%%' AND title LIKE '%%'; -->
					<input type="text" name="title">
				</td>
			</tr>
			<tr>
				<td>배우 검색</td>
				<td>
				<!-- SELECT *
				FROM film_list
				WHERE actors LIKE '%%' AND title LIKE '%%'; -->
					<input type="text" name="actor">
				</td>
				</tr>
		</table>
			<button type="submit">검색</button>
	</form>
</body>
</html>