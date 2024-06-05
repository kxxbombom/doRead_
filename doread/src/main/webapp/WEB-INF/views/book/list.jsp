<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>도서 목록</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header_test.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/kbm.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/kyj.css" type="text/css">

<script type="text/javascript">
window.onload=function(){
	const myForm = document.getElementById('book_search');
	//이벤트 연결
	myForm.onsubmit=function(){
		const keyword = document.getElementById('keyword');
		if(keyword.value.trim()==''){
			alert('검색어를 입력하세요');
			keyword.value = '';
			keyword.focus();
			return false;
		}
	};
};
</script>
</head>
<body>
<div class="page-main">
	<jsp:include page="/WEB-INF/views/news/test_header.jsp"/>
	<div class="content-main">
		<h2 class="content-center">도서 목록</h2>
		<div class="center">
		<form id="book_search" action="list.do" method="get" class="content-center">
			<ul class="list_search">
				<li>
					<select name="keyfield">
						<option value="1" <c:if test="${param.keyfield ==1}">selected</c:if>>도서 제목</option>
						<option value="2" <c:if test="${param.keyfield ==2}">selected</c:if>>저자</option>
						<option value="3" <c:if test="${param.keyfield ==3}">selected</c:if>>출판사</option>
					</select>
				</li>
				<li>
					<input type="search" size="30" name="keyword" id="keyword" value="${param.keyword}" class="list_search">
				</li>
				<li>
					<input type="submit" value="검색" class="list_search">
				</li>
			</ul>
		</form>
		<div class="float-clear">
			<hr width="100%" size="1" noshade="noshade">
		</div>
		</div>
		<div class="image-space">
			<c:forEach var="book" items="${bookList}">
				<div class="horizontal-area" id="book_item">
					<a href="${pageContext.request.contextPath}/book/detail.do?book_num=${book.book_num}">
						<c:if test="${book.book_auth ==0}">
						<img src="${pageContext.request.contextPath}/upload/${book.book_img}">
						</c:if>
						<c:if test="${book.book_auth !=0}">
						<div style="opacity:0.4;">
						<div style="postion:absolute; z-index:998;">품절되었습니다</div>
						<img src="${pageContext.request.contextPath}/upload/${book.book_img}">
						</div>
						</c:if>
					<span>${book.book_name}</span>
					<br>
					<b><fmt:formatNumber value="${book.price}"/>원</b>
					</a>
				</div>
			</c:forEach>
			<div class="float-clear">
				<br>
				<hr width="100%" size="1" noshade="noshade">
			</div>
		</div>
		<div class="align-center">${page}</div>
	</div>
</div>
</body>
</html>