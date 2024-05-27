<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- header 시작 -->

<div id="header-search">
	<ul>
		<li>
			<div id="header_login">
			<a id="header" href="${pageContext.request.contextPath}/main/main.do">
			<img src="${pageContext.request.contextPath}/images/logo.png"></a>
			<h1><a href="${pageContext.request.contextPath}/main/main.do">Do READ</a></h1>
			
			</div>
		<li>
			<form class="form relative">
  			
		  <input style="position:relative; z-index:1;"
		    class="input rounded-full px-8 py-3 border-2 border-transparent focus:outline-none focus:border-blue-500 placeholder-gray-400 transition-all duration-300 shadow-md"
		    placeholder="Search..."
		    required=""
		    type="text"
		  />
		  <svg   style="margin-right:100px; margin-top:14px; position:relative; z-index:999;"  viewBox="0 0 24 24" aria-hidden="true" class="icon">
    			<g >
      			<path
       				 d="M21.53 20.47l-3.66-3.66C19.195 15.24 20 13.214 20 11c0-4.97-4.03-9-9-9s-9 4.03-9 9 4.03 9 9 9c2.215 0 4.24-.804 5.808-2.13l3.66 3.66c.147.146.34.22.53.22s.385-.073.53-.22c.295-.293.295-.767.002-1.06zM3.5 11c0-4.135 3.365-7.5 7.5-7.5s7.5 3.365 7.5 7.5-3.365 7.5-7.5 7.5-7.5-3.365-7.5-7.5z"
      			></path>
    			</g>
  		</svg>
		 
		</form>
			
		</li>
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
		[<span>${user_id}</span>]<a href="${pageContext.request.contextPath}/member/logout.do" class="top-nav">로그아웃</a>
		</li>
		</c:if>
		
	</ul>
</div>
<div class="radio-inputs">
  <label class="radio">
    <input type="radio" name="radio" />
    <span class="name">책카테고리</span>
  </label>
  <label class="radio">
    <input type="radio" name="radio" />
    <span class="name">뉴스</span>
  </label>

  <label class="radio">
    <input type="radio" name="radio" <c:if test="${!empty user_num}">checked=""</c:if> />
    <span class="name">이벤트</span>
  </label>
  <label class="radio">
    <input type="radio" name="radio"  />
        <span class="name">중고</span>
  </label>
  <label class="radio">
    <input type="radio" name="radio" />
    <span class="name">스토리</span>
  </label>

  <label class="radio">
    <input type="radio" name="radio" />
    <span class="name">QnA</span>
  </label>
 	<c:if test="${!empty user_num && user_auth == 9}">
	<label class="radio">
    <input type="radio" name="radio" />
    <span class="name">관리자페이지</span>
  </label>
	</c:if>
</div>

