<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 찜 목록</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/kts.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header_test.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/kyj.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>
</head>
<body>
	<div class="page-main">
		<jsp:include page="/WEB-INF/views/member/mypageheader.jsp" />
		<div class="post-header">
			<h2>회원 찜 목록</h2>
			<hr size="1" noshade width="100%">
			<c:if test="${fn:length(bookList)==0}">
			<div class="align-center">
				<h3 style="margin-top:50px;">찜한 도서가 없습니다</h3>
			</div>
			<div class="fav-button">
				<input type="button" value="책 목록" onclick="location.href='${pageContext.request.contextPath}/book/list.jsp'">
				<input type="button" value="마이페이지" onclick="location.href='${pageContext.request.contextPath}/member/mypage.do'">
			</div>
			</c:if>
			<c:if test="${fn:length(bookList)!=0}">
			<table class="fav-table">
				<tr class="fav-tr">
					<th class="fav-td">제목</th>
					<th class="fav-td">저자</th>
					<th class="fav-td">출판사</th>
					<th class="fav-td">가격</th>
					<th class="fav-td">출판일</th>
				</tr>
				<c:forEach var="book" items="${bookList}">
					<tr class="fav-tr">
						<td class="fav-td"><a href="${pageContext.request.contextPath}/book/detail.do?book_num=${book.book_num}">${book.book_name}</a></td>
						<td class="fav-td">${book.author}</td>
						<td class="fav-td">${book.publisher}</td>
						<td class="fav-td"><fmt:formatNumber value="${book.price}"/>원</td>
						<td class="fav-td">${book.publish_date}</td>
					</tr>
				</c:forEach>
			</table>
			<div class="fav-button">
				<input type="button" value="마이페이지" onclick="location.href='${pageContext.request.contextPath}/member/mypage.do'">
			</div>
			</c:if>
		</div>
	</div>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>