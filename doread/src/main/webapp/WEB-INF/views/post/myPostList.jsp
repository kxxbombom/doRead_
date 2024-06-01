<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 내역</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/kts.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header_test.css" type="text/css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>
</head>
<body>
<div id="post-main">
		<jsp:include page="/WEB-INF/views/news/test_header.jsp"/>
		<jsp:include page="/WEB-INF/views/member/mypageheader.jsp" />
		<div class="post-header">
			<h2>내가 쓴 글</h2>
			<hr size="1" noshade width="100%">
		</div>
		<h4>스토리 게시판</h4>
		<table>
			<tr>
				<th>글번호</th>
				<th>제목</th>
				<th>작성일</th>
			</tr>
			<c:forEach var="Storylist" items="${Storylist}">
			<tr>
				<td>${Storylist.s_num}</td>
				<td><a href="${pageContext.request.contextPath}/story/storyDetail.do?s_num=${Storylist.s_num}">${Storylist.s_title}</a></td>
				<td>${Storylist.s_rdate}</td>
			</tr>
			</c:forEach>		
		</table>
		<hr size="1" noshade width="100%">
		<h4>Qna 게시판</h4>
		<table>
			<tr>
				<th>글번호</th>
				<th>제목</th>
				<th>작성일</th>
			</tr>
			<c:forEach var="qna" items="${qnalist}">
			<tr>
				<td>${qna.q_num}</td>
				<td><a href="${pageContext.request.contextPath}/qna/qnaDetail.do?q_num=${qna.q_num}">${qna.q_title}</a></td>
				<td>${qna.q_rdate}</td>
			</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>