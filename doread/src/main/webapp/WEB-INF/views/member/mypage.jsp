<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/kts.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/ysw.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>
</head>
<body>
<div class="page-main">
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<div class="contents-main">
		<div class="mypage-div">
			<ul>
				<li>
					<img src="${pageContext.request.contextPath}/upload/${member.photo}" 
					                   width="50" height="50" class="my-photo">
				</li>	            
				<li>
					<label for="page-name">${member.id}님<br>어서오세요</label>
				</li>
			</ul>
			
			<ul>
				<li class="content-title">회원정보</li>
				<li>
					<a href="${pageContext.request.contextPath}/member/detail.do">회원 상세 내용</a>
					<a href="${pageContext.request.contextPath}/member/alarm.do">회원 알림</a>
					<a href="${pageContext.request.contextPath}/member/likelist.do">회원 찜 목록</a>
					<a href="${pageContext.request.contextPath}/member/delete.do">회원 탈퇴</a>
				</li>
			</ul>
			<ul>
				<li class="content-title">쇼핑</li>
				<li>
					<a href="${pageContext.request.contextPath}/member/buylist.do">구매 내역 조회</a>
					<a href="${pageContext.request.contextPath}/member/orderlist.do">주문/배송 목록</a>
				</li>
			</ul>
			<ul>
				<li class="content-title">활동내역</li>
				<li>
					<a href="${pageContext.request.contextPath}/member/post.do">게시글 내역</a>
					<a href="${pageContext.request.contextPath}/member/comment.do">댓글 내역</a>
				</li>
			</ul>
			<ul>
				<li class="content-title">QnA</li>
				<li>
					<a href="${pageContext.request.contextPath}/member/inquery.do">1:1문의</a>
					<a href="${pageContext.request.contextPath}/member/bookrequest.do">도서 신청</a>
					
				</li>
			</ul>
		</div>
	</div>
</div>

</body>
</html>























