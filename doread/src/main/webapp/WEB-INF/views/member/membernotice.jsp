<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 알림</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/kts.css" type="text/css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/ysw.css" type="text/css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>
</head>
<body>
	<div class="page-main">
		<jsp:include page="/WEB-INF/views/common/header.jsp" />
		<jsp:include page="/WEB-INF/views/member/mypageheader.jsp" />
		<h3>회원 알림</h3>
		<hr size="1" noshade width="100%">	
		<table>
			<tr>
				<th>No.</th>
				<th>알림제목</th>
				<th>알림내용</th>
			</tr>
			<c:forEach var="board" items="${list}">
			<tr>
				<td>${notice.num}</td>
				<td>${notice.title}</td>
				<td>${notice.content}</td>
			</tr>
			</c:forEach>
		</table>
	</div>
		<hr size="1" noshade width="100%">
</body>
</html>