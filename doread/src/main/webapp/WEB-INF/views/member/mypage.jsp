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
	<div class="content-main">
		<div class="mypage-div">
			<ul>
				<li id="profile-image">
					<img src="${pageContext.request.contextPath}/upload/${member.photo}" 
					                   width="50" height="50" class="my-photo">
				</li>	            
				<li class="page-name">${member.id}님<br>어서오세요</li>
			</ul>
			<ul>
				<li class="outer-main">
					<span class="mypage-title">회원정보</span>
						<ul>
							<li class="inner-menu"><a href="${pageContext.request.contextPath}/member/detail.do">회원 상세 내용</a></li>		
							<li class="inner-menu"><a href="${pageContext.request.contextPath}/member/alarm.do">회원 알림</a></li>
							<li class="inner-menu"><a href="${pageContext.request.contextPath}/member/likelist.do">회원 찜 목록</a></li>
							<li class="inner-menu"><a href="${pageContext.request.contextPath}/member/delete.do">회원 탈퇴</a></li>
						</ul>
				</li>
			</ul>
			<ul>
				<li class="outer-main">
					<span class="mypage-title">쇼핑</span>
						<ul>
							<li class="inner-menu"><a href="${pageContext.request.contextPath}/member/buylist.do">구매 내역 조회</a></li>
							<li class="inner-menu"><a href="${pageContext.request.contextPath}/member/orderlist.do">주문/배송 목록</a></li>
						</ul>
				</li>
			</ul>
			<ul>
				<li class="outer-main">
					<span class="mypage-title">활동내역</span>
						<ul>
							<li class="inner-menu"><a href="${pageContext.request.contextPath}/member/post.do">게시글 내역</a></li>
							<li class="inner-menu"><a href="${pageContext.request.contextPath}/member/comment.do">댓글 내역</a></li>
						</ul>
				</li>
			</ul>
			<ul>
				<li class="outer-main">
					<span class="mypage-title">QnA</span>
						<ul>
							<li class="inner-menu"><a href="${pageContext.request.contextPath}/member/inquery.do">1:1문의</a></li>
							<li class="inner-menu"><a href="${pageContext.request.contextPath}/member/bookrequest.do">도서 신청</a></li>
						</ul>
				</li>
			</ul>
		</div>
		<div class="mypage-button">
			<ul>
				<li class="mypage-mid">
					<input type="button" value="찜" onclick="location.href='${like}'">
					<input type="button" value="추천" onclick="location.href='${best}'">
				</li>
			</ul>
		</div>
	</div>
		
</div>
</body>
</html>























