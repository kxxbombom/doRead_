<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/kyj.css" type="text/css">
<!-- header 시작 -->
<div id="mini_nav">
	<ul>
		<c:if test="${empty user_num}">
		<li>
			<a href="${pageContext.request.contextPath}/member/registerUserForm.do" class="top-nav">회원가입</a>
		</li>
		<li>
			<a href="${pageContext.request.contextPath}/member/loginForm.do" class="top-nav" >로그인</a>
		</li>
		</c:if>
		<c:if test="${!empty user_num}">
		<li class="menu-logout">
		[<span>${user_id}</span>]
		
			<a href="${pageContext.request.contextPath}/member/logout.do" class="top-nav">로그아웃</a>
		</li>
		</c:if>
		<c:if test="${!empty user_num && user_auth == 9}">
		<li>
			<a href="${pageContext.request.contextPath}/member/adminList.do" class="top-nav">회원관리</a>
		</li>
		</c:if>
	</ul>
</div>
<div id="header-search">
	<ul>
		<li>
			<h1 class="home">
				<a href="${pageContext.request.contextPath}/main/main.do" class="top-nav">Do Read</a>
			</h1>
		</li>
		<li>
			<form id="search_form" action="list.do" method="get">
					<ul class="search">
						
						<li id="menu-cart">
							<img src="${pageContext.request.contextPath}/images/cart.png" width="25" 
							height="25" onclick="location.href='${pageContext.request.contextPath}/member/cart.do'" class="click-image">
						</li>
						<li id="menu-mypage">
							<img src="${pageContext.request.contextPath}/images/mypage.png" width="25" 
							height="25" onclick="location.href='${pageContext.request.contextPath}/member/mypage.do'" class="click-image">
						</li>
					</ul>
			</form>
		</li>
	</ul>
</div>
<div id="main_nav">
	<ul>
		<li>
			<a href="${pageContext.request.contextPath}/book/list.do" class="nav" id="book_category">책 카테고리</a>
			<div id="category_items">
					<div class="category_item"><a href="${pageContext.request.contextPath}/book/list.do">도서 전체</a></div>
					<div class="category_item"><a href="${pageContext.request.contextPath}/book/listCategory.do?book_category=1">소설</a></div>
					<div class="category_item"><a href="${pageContext.request.contextPath}/book/listCategory.do?book_category=2">시/에세이</a></div>
					<div class="category_item"><a href="${pageContext.request.contextPath}/book/listCategory.do?book_category=3">인문</a></div>
					<div class="category_item"><a href="${pageContext.request.contextPath}/book/listCategory.do?book_category=4">정치/사회</a></div>
					<div class="category_item"><a href="${pageContext.request.contextPath}/book/listCategory.do?book_category=5">과학/공학</a></div>
					<div class="category_item"><a href="${pageContext.request.contextPath}/book/listCategory.do?book_category=6">IT/프로그래밍</a></div>
					<div class="category_item"><a href="${pageContext.request.contextPath}/book/listCategory.do?book_category=7">자기계발</a></div>
					<div class="category_item"><a href="${pageContext.request.contextPath}/book/listCategory.do?book_category=8">외국어</a></div>
					<div class="category_item"><a href="${pageContext.request.contextPath}/book/listCategory.do?book_category=9">수험서/취업</a></div>
					<div class="category_item"><a href="${pageContext.request.contextPath}/book/listCategory.do?book_category=10">만화</a></div>
					<div class="category_item"><a href="${pageContext.request.contextPath}/book/listCategory.do?book_category=11">취미/스포츠</a></div>
			</div>
		</li>
		<li>
			<a href="${pageContext.request.contextPath}/news/newsList.do" class="nav">뉴스</a>
		</li>
		<li>
			<a href="${pageContext.request.contextPath}/event/eventmain.do " class="nav">이벤트</a>
		</li>
		<li>
			<a href="${pageContext.request.contextPath}/used/usedList.do" class="nav" >중고</a>
		</li>
		<li>
			<a href="${pageContext.request.contextPath}/story/storyBoardList.do" class="nav">스토리</a>
		</li>
		<li>
			<a href="${pageContext.request.contextPath}/qna/qnaList.do" class="nav">QnA</a>
		</li>
		<c:if test="${!empty user_num && user_auth == 9}">
		<li>
			<a href="${pageContext.request.contextPath}/adminster/adminPage.do" class="nav">관리자페이지</a>
		</li>
		</c:if>
	</ul>
</div>
<!-- header 끝 -->


