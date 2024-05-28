<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>도서 정보</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/ysw.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/kyj.css" type="text/css">
</head>
<body>
<div class="page-main">
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<div class="float-clear">
		<br>
		<hr width="100%" size="1" noshade="noshade">
	</div>
	<div class="content-main">
		<div class="book-image">
			<ul>
				<li><img src="${pageContext.request.contextPath}/upload/${book.book_img}" width="400"></li>
			</ul>
		</div>
		<div class="book-detail">
			<form id="book_cart">
				<input type="hidden" name="book_num" value="${book.book_num}" id="book_num">
				<input type="hidden" name="book_price" value="${book.price}" id="book_price">
				<input type="hidden" name="book_stock" value="${book.stock}" id="book_stock">
				<ul>
					<li>
						<div class="category-tag">
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
						</div>
					</li>
					<li><h1>${book.book_name}</h1></li>
					<li>${book.author} / ${book.publisher}</li>
					<li>${book.publish_date}</li>
					<li><h3><b><fmt:formatNumber value="${book.price}"/>원</b> / 재고: <span><fmt:formatNumber value="${book.stock}"/>권</span></h3></li>
					<c:if test="${book.stock>0}">
						<li>
							<label for=book_quantity id="quantity_label">구매수량</label>
							<input type="number" name="book_quantity" id="book_quantity" min="1" max="${book.stock}">
						</li>
						<li id="button-margin">
							<input type="button" value="바로 구매" id="order" class="order-button">
							<input type="button" value="장바구니" id="insert_cart" class="order-button">
						</li>
					</c:if>
				</ul>
			</form>
		</div>
	</div>
</div>
</body>
</html>