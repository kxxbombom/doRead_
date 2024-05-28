<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/ysw.css" type="text/css">
</head> 
<body>
<div class="page-main">
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<div class="content-main">
		<h4>이벤트</h4>
		<div class="image-space">
			<c:forEach var="event" items="${eventList}">
			<div class="horizontal-area">
				<a href="${pageContext.request.contextPath}/event/detailEvent.do?e_num=${event.e_num}">
					<img src="${pageContext.request.contextPath}/upload/${event.e_image}">
					<span>${event.e_title}</span>				
					<br>
				</a>
			</div>
			</c:forEach>
			<div class="float-clear">
				<hr width="100%" size="1" noshade="noshade">
			</div>
		</div>
		<h4>최신 상품</h4>
		<div class="image-space">
			<c:forEach var="book" items="${bookList}">
			<div class="horizontal-area">
				<a href="${pageContext.request.contextPath}/book/detail.do?book_num=${book.book_num}">
					<img src="${pageContext.request.contextPath}/upload/${book.book_img}">
					<span>${book.book_name}</span>				
					<br>
				</a>
			</div>
			</c:forEach>
			<div class="float-clear">
				<hr width="100%" size="1" noshade="noshade">
			</div>
		</div>
	</div>
</div>
</body>
</html>







