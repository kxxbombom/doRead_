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
<script type="text/javascript"src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>
</head>
<body>
<div class="page-main">
		<jsp:include page="/WEB-INF/views/member/mypageheader.jsp" />
		<div class="post-header">
			<h2>내가 쓴 글</h2>
			<hr size="1" noshade width="100%">
			<div class="alertfont" style="font-size:10pxt; color:#d4d2d2;">***게시글/댓글당 신고 누적횟수가 5회이상이면 정지게시글/댓글이되며, 정지게시글/댓글이 5개이상이면 회원 정지가 될 수 있습니다.</div>
		</div>
		<h4>중고 게시판</h4>
		<table>
			<tr>
				<th>글번호</th>
				<th>제목</th>
				<th>작성일</th>
				<th>글등급
				</th>
			</tr>
			<c:forEach var="list" items="${list}">
			<tr>
				<td>${list.u_num}</td>
				<td><a href="${pageContext.request.contextPath}/used/usedDetail.do?u_num=${list.u_num}" class="board-title">${list.u_title}</a></td>
				<td>${list.u_rdate}</td>
				<td>
					<c:if test="${list.u_auth == 0}">일반게시물</c:if>
	 				<c:if test="${list.u_auth == 1}">신고게시물</c:if>
					<c:if test="${list.u_auth == 2}"><legend style="color:red;">정지게시물</legend></c:if>
				</td>
			</tr>
			</c:forEach>		
		</table>
		<div style="text-align:center;">
			${page4}
		</div>
		<hr size="1" noshade width="100%">
		<h4>스토리 게시판</h4>
		<table>
			<tr>
				<th>글번호</th>
				<th>제목</th>
				<th>작성일</th>
				<th>글등급<img class="gideimage" src="${pageContext.request.contextPath}/images/question_471664.png"  width="15px">
				</th>
			</tr>
			<c:forEach var="Storylist" items="${Storylist}">
			<tr>
				<td>${Storylist.s_num}</td>
				<td><a href="${pageContext.request.contextPath}/story/storyDetail.do?s_num=${Storylist.s_num}" class="board-title">${Storylist.s_title}</a></td>
				<td>${Storylist.s_rdate}</td>
				<td>
					<c:if test="${Storylist.s_auth == 0}">일반게시물</c:if>
	 				<c:if test="${Storylist.s_auth == 1}">신고게시물</c:if>
					<c:if test="${Storylist.s_auth == 2}"><legend style="color:red;">정지게시물</legend></c:if>
				</td>
			</tr>
			</c:forEach>		
		</table>
		<div style="text-align:center;">
			${page}
		</div>
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
				<td><a href="${pageContext.request.contextPath}/qna/qnaDetail.do?q_num=${qna.q_num}" class="QnA-title">${qna.q_title}</a></td>
				<td>${qna.q_rdate}</td>
			
			</tr>
			</c:forEach>
		</table>
		<div style="text-align:center;">
			${page3}
		</div>
			 
		
	</div>
	<div class="hide gide" style="font-size:10pxt;  z-index:999; position:absolute; bottom:350px; left:20%; border:1px solid #d4d2d2; border-radius:15px; background:white;">***게시글/댓글당 신고 누적횟수가 5회이상이면 정지게시글/댓글이되며, 정지게시글/댓글이 5개이상이면 회원 정지가 될 수 있습니다.</div>
</body>
</html>