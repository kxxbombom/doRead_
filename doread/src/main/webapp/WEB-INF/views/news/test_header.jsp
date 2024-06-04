<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
		<c:if test="${empty user_num}">
		<li id="menu-cart">
			<img src="${pageContext.request.contextPath}/images/cart.png" class="click-image" width="30" 
					height="30" onclick="location.href='${pageContext.request.contextPath}/member/loginForm.do'">
		</li>
		<li id="menu-mypage">
			<img src="${pageContext.request.contextPath}/images/mypage.png" class="click-image" width="30" 
					height="30" onclick="location.href='${pageContext.request.contextPath}/member/loginForm.do'">
		</li>
		</c:if>
		<c:if test="${user_auth == 2 }">
		<li id="menu-cart">
			<img src="${pageContext.request.contextPath}/images/cart.png" class="click-image" width="30" 
					height="30" onclick="location.href='${pageContext.request.contextPath}/cart/list.do'">
		</li>
		<li id="menu-mypage">
			<img src="${pageContext.request.contextPath}/images/mypage.png" class="click-image" width="30" 
					height="30" onclick="location.href='${pageContext.request.contextPath}/member/mypage.do'">
		</li>
		</c:if>
		<c:if test="${user_auth == 9 }">
		<li id="menu-cart">
			<img src="${pageContext.request.contextPath}/images/cart.png" class="click-image" width="30" 
					height="30">
		</li>
		<li id="menu-mypage">
			<img src="${pageContext.request.contextPath}/images/mypage.png" class="click-image" width="30" 
					height="30">
		</li>
		</c:if>
		
	</ul>
</div>
<div class="main-nav-container">
	<ul id="main_nav">
		<li>
		<div id="container">
        <input type="checkbox" id="toggle" hidden>
        <label for="toggle" id="label">도서</label>
        		<ul id="menu">
           		 	<li class="menu-item"><br><a href="${pageContext.request.contextPath}/book/list.do">도서 전체</a></li>
          			<li class="menu-item"><br><a href="${pageContext.request.contextPath}/book/listCategory.do?book_category=1">소설</a></li>
            		<li class="menu-item"><br><a href="${pageContext.request.contextPath}/book/listCategory.do?book_category=2">시/에세이</a></li>
           			<li class="menu-item"><br><a href="${pageContext.request.contextPath}/book/listCategory.do?book_category=3">인문</a></li>
           			<li class="menu-item"><br><a href="${pageContext.request.contextPath}/book/listCategory.do?book_category=4">정치/사회</a></li>
           			<li class="menu-item"><br><a href="${pageContext.request.contextPath}/book/listCategory.do?book_category=5">과학/공학</a></li>
           			<li class="menu-item"><br><a href="${pageContext.request.contextPath}/book/listCategory.do?book_category=6">IT/프로그래밍</a></li>
           			<li class="menu-item"><br><a href="${pageContext.request.contextPath}/book/listCategory.do?book_category=7">자기계발</a></li>
           			<li class="menu-item"><br><a href="${pageContext.request.contextPath}/book/listCategory.do?book_category=8">외국어</a></li>
           			<li class="menu-item"><br><a href="${pageContext.request.contextPath}/book/listCategory.do?book_category=9">수험서/취업</a></li>
           			<li class="menu-item"><br><a href="${pageContext.request.contextPath}/book/listCategory.do?book_category=10">만화</a></li>
           			<li class="menu-item"><br><a href="${pageContext.request.contextPath}/book/listCategory.do?book_category=11">취미/스포츠</a></li>
       			</ul>
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
			<a href="${pageContext.request.contextPath}/qna/faqLit.do" class="nav">FAQ</a>
		</li>
	</ul>
</div>
<!-- header 끝 -->


