<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>도서목록</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header_test.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/kbm.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/kyj.css" type="text/css">
<script type="text/javascript">
window.onload=function(){
	const myForm = document.getElementById('search_form');
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
		<h2>
			<c:if test="${param.book_category == 1}">소설</c:if>
			<c:if test="${param.book_category == 2}">시/에세이</c:if>
			<c:if test="${param.book_category == 3}">인문</c:if>
			<c:if test="${param.book_category == 4}">정치/사회</c:if>
			<c:if test="${param.book_category == 5}">과학/공학</c:if>
			<c:if test="${param.book_category == 6}">IT/프로그래밍</c:if>
			<c:if test="${param.book_category == 7}">자기계발</c:if>
			<c:if test="${param.book_category == 8}">외국어</c:if>
			<c:if test="${param.book_category == 9}">수험서/취업</c:if>
			<c:if test="${param.book_category == 10}">만화</c:if>
			<c:if test="${param.book_category == 11}">취미/스포츠</c:if>
			<c:if test="${param.book_category == 12}">e-book</c:if>
		</h2>
		<form id="book_search" action="listCategory.do" method="get" class="form-center">
			<ul class="list_search">
				<li>
					<select name="keyfield">
						<option value="1" <c:if test="${param.keyfield ==1}">selected</c:if>>도서 제목</option>
						<option value="2" <c:if test="${param.keyfield ==2}">selected</c:if>>저자</option>
						<option value="3" <c:if test="${param.keyfield ==3}">selected</c:if>>출판사</option>
					</select>
				</li>
				<li>
					<input type="hidden" name="book_category" id="book_category" value="${param.book_category}">
					<input type="search" size="30" name="keyword" id="keyword" value="${param.keyword}">
				</li>
				<li>
					<input type="submit" value="검색">
				</li>
			</ul>
		</form>
		<div class="float-clear">
			<hr width="100%" size="1" noshade="noshade">
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
						<div style="postion:absolute; z-index:998;">구매할 수 없는 도서입니다</div>
						<img src="${pageContext.request.contextPath}/upload/${book.book_img}">
						</div>
						</c:if>
						<span style="width:220px;">${book.book_name}</span>
						<br>
						<b><fmt:formatNumber value="${book.price}"/>원</b>
					</a>
				</div>
			</c:forEach>
			<div class="float-clear">
				<hr width="100%" size="1" noshade="noshade" style="margin-top:30px;">
			</div>
		</div>
		<div class="align-center">${page}</div>
	</div>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</div>
</body>
</html>