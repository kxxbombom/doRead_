<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>도서 목록(관리자용)</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header_test.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/kbm.css" type="text/css">
</head>
<body>
<div class="page-main">
	<jsp:include page="${pageContext.request.contextPath}/news/test_header.jsp"/>
	<div class="content-main">
		<form id="list_search_form" action="adminBookList.do" method="get">
			<ul class="list_search">
				<li>
					<select name="keyfield" class="list_search">
						<option value="1" <c:if test="${param.keyfield == 1}">selected</c:if>>도서번호</option>
						<option value="2" <c:if test="${param.keyfield == 2}">selected</c:if>>제목</option>
					</select>
				</li>
				<li>
					<input type="search"  class="list_search" size="16" name="keyword" id="keyword2" value="${param.keyword}">
				</li>
				<li>
					<input type="submit" value="검색" class="list_search">
				</li>
			</ul>
		</form>
		<table>
			<tr>
				<th>도서번호</th>
				<th>분류</th>
				<th>제목</th>
				<th>저자</th>
				<th>출판사</th>
				<th>가격</th>
				<th>재고</th>
				<th>상태</th>
			</tr>
			<c:forEach var="book" items="${list}">
			<tr>
				<td>${book.book_num}</td>
				<td class="text-center">${book.book_category}</td>
				<td><a href="updateBookForm.do?book_num=${book.book_num}">${book.book_name}</a></td>
				<td class="text-center">${book.author}</td>
				<td class="text-center">${book.publisher}</td>
				<td class="text-center">${book.price}</td>
				<td class="text-center">${book.stock}</td>
				<td class="text-center">${book.book_auth}</td>
			</tr>
			</c:forEach>
		</table>

		<script type="text/javascript">
		
			const myForm = document.getElementById('list_search_form');
	
			myForm.onsubmit=function(){
				const keyword = document.getElementById('keyword2');
				if(keyword.value.trim() == ''){
					alert('검색어를 입력하세요');
					keyword.value = '';
					keyword.focus();
					return false;
				}
			};
		
</script>
		<div class="list-space align-right">
			<div class="list-btn">
			<input type="button" value="관리자페이지" onclick="location.href='${pageContext.request.contextPath}/adminster/adminPage.do'"> <%--다른 경로--%>
			</div>
		</div>
		</div>
		
		<div class="align-center">${page}</div>
		
</div>


</body>
</html>