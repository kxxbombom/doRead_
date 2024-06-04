<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내가 참여한 이벤트 내역</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/kts.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header_test.css" type="text/css">
<script type="text/javascript"src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>
</head>
<body>
<div class="page-main">
		<jsp:include page="/WEB-INF/views/member/mypageheader.jsp" />
		<div class="post-header">
		</div>
		<h4>참여 이벤트 내역</h4>
		<table>
			<tr>
				<th>이벤트명</th>
				<th>마감일</th>
			</tr>
			<c:forEach var="event" items="${list}">
			<tr>
				<td>${event.e_title}</td>
				<td>${event.e_deadline}</td>
			</tr>
			</c:forEach>		
		</table>
		<div style="text-align:center;">
			${page}
		</div>
		
			 
		
	</div>
	<div class="hide gide" style="font-size:10pxt;  z-index:999; position:absolute; bottom:350px; left:20%; border:1px solid #d4d2d2; border-radius:15px; background:white;">***게시글/댓글당 신고 누적횟수가 5회이상이면 정지게시글/댓글이되며, 정지게시글/댓글이 5개이상이면 회원 정지가 될 수 있습니다.</div>
</body>
</html>