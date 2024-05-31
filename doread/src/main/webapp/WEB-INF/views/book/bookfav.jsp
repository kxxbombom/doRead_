<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 찜 목록</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/kts.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/ysw.css" type="text/css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>
</head>
<body>
	<div class="page-main">
		<jsp:include page="/WEB-INF/views/common/header.jsp" />
		<jsp:include page="/WEB-INF/views/member/mypageheader.jsp" />
		<div class="detail-header">
			<h3>회원 찜 목록</h3>
			<hr size="1" noshade width="100%">
			<table>
				<tr>
					<th>제목</th>
					<th>저자</th>
					<th>출판사</th>
					<th>가격</th>
					<th>출판일</th>
				</tr>
				<c:forEach var="book" items="${bookList}">
					<tr>
						<td><a href="${pageContext.request.contextPath}/book/detail.do?book_num=${book.book_num}">${book.book_name}</a></td>
						<td>${book.author}</td>
						<td>${book.publisher}</td>
						<td><fmt:formatNumber value="${book.price}"/>원</td>
						<td>${book.publish_date}</td>
					</tr>
				</c:forEach>
			</table>
		</div>
			<div class="fav-button">
				<input type="button" value="마이페이지" onclick="location.href='${pageContext.request.contextPath}/member/mypage.do'">
				<input type="button" value="장바구니담기" >
			</div>
	</div>
</body>
</html>