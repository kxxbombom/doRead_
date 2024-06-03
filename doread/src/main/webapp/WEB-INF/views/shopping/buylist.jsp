<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>구매 내역 조회</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/kts.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header_test.css" type="text/css">
</head>
<body>
<div class="page-main">
	<div class="content-main">
	<jsp:include page="/WEB-INF/views/news/test_header.jsp"/>
	<jsp:include page="/WEB-INF/views/member/mypageheader.jsp"/>
		<h2>구매 내역 조회</h2>
		<hr size="1" noshade width="100%">
		<table>
			<tr>
				<th>주문번호</th>
				<th>도서명</th>
				<th>구매일</th>
				
			</tr>
			<c:forEach var="order" items="${list}">
			<tr>
				<td>${Storylist.s_num}</td>
				<td><a href="${pageContext.request.contextPath}/story/storyDetail.do?s_num=${Storylist.s_num}">${Storylist.s_title}</a></td>
				<td>${Storylist.s_rdate}</td>
				<td style="color:red;">
					
				</td>
			</tr>
			</c:forEach>		
		</table>
		<div style="text-align:center;">
			${page}
		</div>
		
		
		<hr size="1" noshade width="100%">
	</div>
</div>
</body>
</html>