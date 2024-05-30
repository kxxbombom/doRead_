<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>장바구니</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/kbm.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header_test.css" type="text/css">

<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/shop.cart.js"></script>
</head>
<body>
<div class="page-main">
	<jsp:include page="/WEB-INF/views/news/test_header.jsp"/>
	<div class="content-main">
		<h2>장바구니</h2>
		
		<c:if test="${empty list}">
		 <div class="result-display">
			장바구니에 담은 상품이 없습니다.
		 </div>
		</c:if>
		
		<c:if test="${!empty list}">
	 	<form id="cart_order" action="${pageContext.request.contextPath}/order/orderForm.do" method="post">
	 		<table>
	 			<tr>
	 				<th>도서명</th>
	 				<th>수량</th>
	 				<th>도서가격</th>
	 				<th>합계</th>
	 			</tr>
	 			<c:forEach var="cart" items="${list}">
	 				<tr>
	 					<td>
	 						<a href="${pageContext.request.contextPath}/book/detail.do?book_num=${cart.book_num}">
	 							<img src="${pageContext.request.contextPath}/upload/${cart.bookVO.book_img}" width="80">
	 							<br>
	 							${cart.bookVO.book_name}
	 						</a>
	 					</td>
	 					<td class="align-center"><%--book status........ --%>
	 						<c:if test="${cart.bookVO.stock < cart.c_quantity}">[재고부족]</c:if>
	 						<c:if test="${cart.bookVO.stock >= cart.c_quantity}">
	 							<input type="number" name="c_quantity" min="1" max="${cart.bookVO.stock}" value="${cart.c_quantity}" class="quantity-width">
	 							<br>												<%--커스텀 데이터--%>
	 							<input type="button" value="변경" class="cart-modify" data-cartnum="${cart.c_num}" data-booknum="${cart.book_num}">
	 						</c:if>
	 					</td>
	 					<td class="align-center">
	 						<fmt:formatNumber value="${cart.bookVO.price}"/>원
	 					</td>
	 					<td class="align-center">
	 						<fmt:formatNumber value="${cart.sub_total}"/>원
	 						<br>
	 						<input type="button" value="삭제" class="cart-del" data-cartnum="${cart.c_num}">
	 					</td>
	 				</tr>
	 			</c:forEach>
	 			<tr>
	 				<td colspan="3" class="align-right"><b>총구매금액</b></td>
	 				<td class="align-center"><fmt:formatNumber value="${all_total}"/>원</td>
	 			</tr>
	 		</table>
	 		<div class="align-center">
	 			<input type="submit" value="구매">
	 		</div>
	 	</form>
		</c:if>
	</div>
</div>

</body>
</html>