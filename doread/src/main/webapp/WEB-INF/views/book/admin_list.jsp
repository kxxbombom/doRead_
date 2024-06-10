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
<br><br>
<jsp:include page="/WEB-INF/views/adminster/adminheader3.jsp"/>
<br><br>
<jsp:include page="/WEB-INF/views/adminster/adminheader.jsp"/>
<div class="page-main">
	<div class="content-main">
		<form id="list_search_form" action="adminBookList.do" method="get">
			<ul class="list_search">
				<li>
					<select name="keyfield" class="list_search">
						<option value="1" <c:if test="${param.keyfield == 1}">selected</c:if>>제목</option>
						<option value="2" <c:if test="${param.keyfield == 2}">selected</c:if>>저자</option>
					</select>
				</li>
				<li>
					<input type="search"  class="list_search" size="16" name="keyword" id="keyword2" value="${param.keyword}">
				</li>
				<li>
					<input type="submit" value="검색" class="list_search" style="margin-left:0px;">
				</li>
			</ul>
		</form>
		<table>
			<tr>
				<th>도서번호</th>
				<th>분류</th>
				<th style="width:300px;">제목</th>
				<th>저자</th>
				<th>재고</th>
				<th>상태</th>
			</tr>
			<c:forEach var="book" items="${list}">
			<tr>
				<td>${book.book_num}</td>
				<td>
					<c:if test="${book.book_category==1}">소설</c:if>
					<c:if test="${book.book_category==2}">시/에세이</c:if>
					<c:if test="${book.book_category==3}">인문</c:if>
					<c:if test="${book.book_category==4}">정치/사회</c:if>
					<c:if test="${book.book_category==5}">과학/공학</c:if>
					<c:if test="${book.book_category==6}">IT/프로그래밍</c:if>
					<c:if test="${book.book_category==7}">자기계발</c:if>
					<c:if test="${book.book_category==8}">외국어</c:if>
					<c:if test="${book.book_category==9}">수험서/취업</c:if>
					<c:if test="${book.book_category==10}">만화</c:if>
					<c:if test="${book.book_category==11}">취미/스포츠</c:if>
				</td>
				<td><a href="updateBookForm.do?book_num=${book.book_num}">${book.book_name}</a></td>
				<td class="text-center">${book.author}</td>
				<td class="text-center">${book.stock}</td>
				<td class="text-center">
					<c:if test="${book.book_auth==0}">
						판매중
					</c:if>
					<c:if test="${book.book_auth==1}">
						판매중단
					</c:if>	
				</td>
					
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
			<input type="button" value="관리자페이지" style="width:110px;" 
			onclick="location.href='${pageContext.request.contextPath}/adminster/adminPage.do'">
			</div>
		</div>
		</div>
		
		<div class="align-center">${page}</div>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</div>
</body>
</html>