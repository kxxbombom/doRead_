<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 상세 내용</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/kts.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header_test.css" type="text/css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>
</head>
<body>
	<div class="page-main">
	<div id="mini_nav">
	<ul>
		<c:if test="${empty user_num}">
		<li>
			<a href="${pageContext.request.contextPath}/member/registerUserForm.do" class="top-nav">회원가입</a>
		</li>
		<li>
			<a href="${pageContext.request.contextPath}/member/loginForm.do" class="top-nav" >로그인</a>
		</li>
		<li>
			<a href="${pageContext.request.contextPath}/member/mypage.do" class="top-nav" >마이페이지</a>
		</li>
		</c:if>
		
		<c:if test="${!empty user_num && user_auth != 9}">
		<li class="menu-logout">
			[<span>${user_id}</span>]
			<a href="${pageContext.request.contextPath}/member/logout.do" class="top-nav">로그아웃</a>
		</li>
		<li>
			<a href="${pageContext.request.contextPath}/member/mypage.do" class="top-nav" >마이페이지</a>
		</li>
		</c:if>
		
		<c:if test="${!empty user_num && user_auth == 9}">
		<li class="menu-logout">
			[<span>${user_id}</span>]
			<a href="${pageContext.request.contextPath}/member/logout.do" class="top-nav">로그아웃</a>
		</li>
		<li>
			<a href="${pageContext.request.contextPath}/adminster/adminPage.do" class="top-nav">관리자페이지</a>
		</li>
		</c:if>
		
	</ul>
</div>
<div id="main_logo">
	<ul>
		<li>
			<a href="${pageContext.request.contextPath}/main/main.do" class="top-nav" id="doread"><img src="../images/pngwing.com.png" width="40px">Do Read</a>

		</li>

		<li id="menu-cart">
			<a href="${pageContext.request.contextPath}/cart/list.do">
				<img src="${pageContext.request.contextPath}/images/cart.png" class="click-image" width="30" 
					height="30" onclick="location.href='${pageContext.request.contextPath}/member/cart.do'">
			</a>
		</li>
		<li id="menu-mypage">
			<img src="${pageContext.request.contextPath}/images/mypage.png" class="click-image" width="30" 
					height="30" onclick="location.href='${pageContext.request.contextPath}/member/mypage.do'">
		</li>
		
	</ul>
</div>
		<jsp:include page="/WEB-INF/views/member/mypageheader.jsp" />
		<div class="detail-header">
			<h3>회원 상세 내용</h3>
			<hr size="1" noshade width="100%">
			<div class="detail-content">
				<ul>
					<li>이름: ${member.mem_id}</li>
					<li>아이디: ${member.mem_name}</li>
					<li>이메일: ${member.mem_email}</li>
					<li>전화번호: ${member.mem_phone}</li>
					<li>우편번호: ${member.mem_zipcode}</li>
					<li>주소: ${member.mem_address1} ${member.mem_address2}</li>
				</ul>
			</div>
		</div>
		<hr size="1" noshade width="100%">
			<div class="detail-button">
				<input type="button" value="수정" onclick="location.href='modifyUserForm.do'">
				<input type="button" value="회원탈퇴" onclick="location.href='deleteUserForm.do'" >
			</div>
	</div>
</body>
</html>