<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관심도서 목록</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/kts.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/kyj.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header_test.css" type="text/css">
</head>
<body>
<div class="page-main">
	<jsp:include page="/WEB-INF/views/member/mypageheader.jsp"/>
	<h2>${user_id}님을 위한 추천도서</h2>
	<hr width="100%" size="1" noshade="noshade">
		<div class="content-main">
		<h2>도서 목록</h2>
		<div class="image-space">
			<c:forEach var="book" items="${bookList}">
				<div class="horizontal-area" id="book_item">
					<a href="${pageContext.request.contextPath}/book/detail.do?book_num=${book.book_num}">
						<c:if test="${book.book_auth ==0}">
						<img src="${pageContext.request.contextPath}/upload/${book.book_img}">
						</c:if>
						<c:if test="${book.book_auth !=0}">
						<div style="opacity:0.4;">
						<div style="postion:absolute; z-index:998;">품절되었습니다</div>
						<img src="${pageContext.request.contextPath}/upload/${book.book_img}">
						</div>
						</c:if>
					<span>${book.book_name}</span>
					<b><fmt:formatNumber value="${book.price}"/>원</b>
					</a>
				</div>
			</c:forEach>
			<div class="float-clear">
				<br>
				<hr width="100%" size="1" noshade="noshade">
			</div>
		</div>
		<div class="align-center">${page}</div>
	</div>
</div>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>


