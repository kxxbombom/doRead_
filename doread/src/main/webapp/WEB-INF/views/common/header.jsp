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
						<li>
							<select name="keyfield">
								<option value="1" <c:if test="${param.keyfield==1}">selected</c:if>>도서명</option>
								<option value="2" <c:if test="${param.keyfield==2}">selected</c:if>>제목</option>
								<option value="3" <c:if test="${param.keyfield==3}">selected</c:if>>내용</option>
							</select>
						</li>
						<li>
							<input type="search" size="16" name="keyword" id="keyword" value="${param.keyword}">				
						</li>
						<li>
							<input type="submit" value="검색">
						</li>
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
			<a href="${pageContext.request.contextPath}" class="nav" id="book_category">책 카테고리</a>
			<div id="category_items">
				<ul>
					<li>도서 전체</li>
					<li><a href="/WEB-INF/views/book/list.jsp">소설</a></li>
					<li><a href="/WEB-INF/views/book/list.jsp">시/에세이</a></li>
					<li><a href="/WEB-INF/views/book/list.jsp">인문</a></li>
					<li><a href="/WEB-INF/views/book/list.jsp">정치/사회</a></li>
					<li><a href="/WEB-INF/views/book/list.jsp">과학/공학</a></li>
					<li><a href="/WEB-INF/views/book/list.jsp">IT/프로그래밍</a></li>
					<li><a href="/WEB-INF/views/book/list.jsp">자기계발</a></li>
					<li><a href="/WEB-INF/views/book/list.jsp">외국어</a></li>
					<li><a href="/WEB-INF/views/book/list.jsp">수험서/취업</a></li>
					<li><a href="/WEB-INF/views/book/list.jsp">만화</a></li>
					<li><a href="/WEB-INF/views/book/list.jsp">취미/스포츠</a></li>
					
				</ul>
			</div>
		</li>
		<li>
			<a href="${pageContext.request.contextPath}/board/newsList.do" class="nav">뉴스</a>
		</li>
		<li>
			<a href="${pageContext.request.contextPath}/event/eventmain.do " class="nav">이벤트</a>
		</li>
		<li>
			<a href="${pageContext.request.contextPath}/board/usedList.do" class="nav" >중고</a>
		</li>
		<li>
			<a href="${pageContext.request.contextPath}/board/list.do" class="nav">스토리</a>
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


