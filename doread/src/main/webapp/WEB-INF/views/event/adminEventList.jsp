<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이벤트 메인</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/kbm.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header_test.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>
</head>
<body>
<div class="page-main">
	<jsp:include page="/WEB-INF/views/news/test_header.jsp"/>
 			<h2 style="text-align:center;">EVENT[관리자 이벤트 응모내역 관리]</h2>
		<div class="align-center eventmain">
			<hr width="40%" size="1" noshade="noshade">
			<div  class="eventmain">
				<c:if test="${check == 1}">
				<c:forEach var="event" items="${List}">
				<div class="horizontal-area">
					<p>
					<a href="${pageContext.request.contextPath}/event/detailAdminEvent.do?e_num=${event.e_num}">
						<img src="${pageContext.request.contextPath}/upload/${event.e_image}" width="360px" height="460"><br>
						<span id ="e_title" ><b style="font-size:15pt;">${event.e_title}</b></span>
						<br>
						이벤트기간 ${event.e_rdate} ~ ${event.e_deadline}
					</a>
					</p>
					<hr width="40%" size="1" noshade="noshade">
					<br><br><br>
				</div>
				</c:forEach>
				</c:if>
				
				<c:if test="${check == 2 }">
				<c:forEach var="event" items="${List}">
				<div class="horizontal-area">
					<a href="${pageContext.request.contextPath}/event/detailAdminEvent.do?e_num=${event.e_num}">
						<img src="${pageContext.request.contextPath}/upload/${event.e_image}" width="360px" height="460"><br>
						<span id ="e_title"><b>${event.e_title}</b></span>
						<br>
						이벤트기간 ${event.e_rdate} ~ ${event.e_deadline}
					</a>
				</div>
				</c:forEach>
				</c:if>
				
				<c:if test="${check == 3}">
					<div class="align-center">
					<c:if test="${empty List}">
								<tr><td><b>당첨자가 없습니다.</b><td></tr>
					</c:if>
					<c:if test="${!empty List}">
						<table>
						<tr>
							<th>이벤트</th>
							<th>당첨자 id</th>
						</tr>
							<c:forEach var="event" items="${List}">
							<tr>
							<td>${event.e_title}</td>
							<td>${event.id}</td>
							</tr>
							</c:forEach>
						
							
						</table>
					</c:if>
					</div>
				</c:if>
				<div class="float-clear">
					<hr width="100%" size="1" noshade="noshade">
				</div>
			
	 			<span>${page}</span>
 			</div>
 	</div>	
</div>

<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>

