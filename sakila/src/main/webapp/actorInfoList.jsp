<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="dao.*"%>
<%@ page import="vo.*"%>

<%
	//페이지 ,묶어서 처리, 이전다음페이지로 넘길때 값도 함꼐 바껴야함.
		int currentPage = 1;//현재페이지의 기본값이 1페이지임.
		if(request.getParameter("currentPage") != null) {//이전, 다음링크를 통해서 들어온거라면
			currentPage = Integer.parseInt(request.getParameter("currentPage"));//현재페이지는 요청받은 current page값인 문자열 숫자를 정수타입으로 바꿈.
		}
			System.out.println(currentPage + "<-currentPage");//디버깅
		
		int rowPerPage = 10;//10개씩 글을 묶어서 하나의 페이지로 보여줌.
		System.out.println(rowPerPage + "<-rowPerPage");//디버깅
		
		int beginRow = (currentPage-1)*rowPerPage;//알고리즘생성
			System.out.println(beginRow + "<-beginRow");//디버깅
		//호출
		ActorInfoDao actorInfoDao = new ActorInfoDao();
		ActorInfo actorInfo = new ActorInfo();
		List<ActorInfo> list= actorInfoDao.selectActorInfoListByPage(beginRow, rowPerPage);
		list = actorInfoDao.selectActorInfoListByPage(beginRow, rowPerPage);
		

		int lastPage = 0;//마지막 페이지 초기화
		int totalCount = actorInfoDao.selectactorInfoTotalRow();
		lastPage = (int)(Math.ceil((double)totalCount / (double)rowPerPage)); 
			
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ActorInfo List</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
	<button type="button" class="btn btn-outline-dark">
		<a href="<%=request.getContextPath()%>/index.jsp">index</a>
	</button>
		<div class="jumbotron">
  		<h2>ActorInfo List</h2>
		</div>
		  <table class="table table-dark table-striped">
			<thead>
				<tr>
					<th>actorId</th>
					<th>firstName</th>
					<th>lastName</th>
					<th>filmInfo</th>
				</tr>
			</thead>
			<tbody>
				<%
					for(ActorInfo a : list) {
				%>
					<tr>
						<td><%=a.getActorId()%></td>
						<td><%=a.getFirstName()%></td>
						<td><%=a.getLastName()%></td>
						<td><%=a.getFilmInfo()%></td>						
					</tr>
				<%		
					}
				%>
				


			</tbody>
		</table>
			<div> <!-- 현재페이지가 10p였다면 이전은 9p, 다음은 11p -->
		<%
			if(currentPage > 1) { //현재페이지가 1이면 이전페이지가 존재하면 안됨.
		%>
		<button type="button" class="btn btn-outline-secondary">
			<a href="<%=request.getContextPath()%>/boardList.jsp?currentPage=<%=currentPage-1%>">이전</a> <!-- 현재페이지값을넘겨줌/현재페이지에서 1을빼줌-->
		</button>
		<%		
			}
		%>
			<!-- 마지막 페이지 구하기
					전체행  마지막페이지
					10개		1p
					11-20	2p
					21-30	3p
					31-40	4p
				마지막 페이지 = 전체행/rowPerPage
			. -->
			<%
			
				//
				if(currentPage < lastPage) {
			%>	
			<button type="button" class="btn btn-outline-secondary btn-sm">
			<a href="<%=request.getContextPath()%>/actorInfoList.jsp?currentPage=<%=currentPage+1%>">다음</a>	 <!-- 현재페이지값을넘겨줌/현재페이지에서 1을빼줌-->
			</button>
			<%
				}
			%>
	</div>
	
</body>
</html>