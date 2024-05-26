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
			<form  id="search_form" action="list.do" method="get">
			<div class="group">
  
  				<input  style="margin-left:140px; " class="input" type="search" placeholder="Search" />
  				<svg  style="margin-left:140px;" viewBox="0 0 24 24" aria-hidden="true" class="icon">
    			<g>
      			<path
       				 d="M21.53 20.47l-3.66-3.66C19.195 15.24 20 13.214 20 11c0-4.97-4.03-9-9-9s-9 4.03-9 9 4.03 9 9 9c2.215 0 4.24-.804 5.808-2.13l3.66 3.66c.147.146.34.22.53.22s.385-.073.53-.22c.295-.293.295-.767.002-1.06zM3.5 11c0-4.135 3.365-7.5 7.5-7.5s7.5 3.365 7.5 7.5-3.365 7.5-7.5 7.5-7.5-3.365-7.5-7.5z"
      			></path>
    			</g>
  		</svg>
			</div>
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