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
		 	<hr size="1" width="100%" noshade="noshade">
			장바구니에 담은 상품이 없습니다.
			<hr size="1" width="100%" noshade="noshade">
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
	 							<input type="button" value="변경" class="cart-modify" data-cartnum="${cart.c_num}" data-booknum="${cart.book_num}" data-total="${all_total}">
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
	 		
	 		<div style="border:1px solid black;">
	 			<ul>
	 				<li>상품 금액 <span><fmt:formatNumber value="${all_total}"/>원</span></li>
	 				<li>배송비<img src="${pageContext.request.contextPath}/images/info.png" width="15px"> 
	 					<span id="cart_deliveryFee">
						<c:if test="${all_total >= 15000}">
						0
						</c:if>
						<c:if test="${all_total< 15000}">
						<fmt:formatNumber value="3000"/>
						</c:if>원
						</span>
	 				</li>

	 				<li>결제 예정 금액 <span><fmt:formatNumber value="${all_total_delivery}"/>원</span></li>
	 				<li>적립 예정 포인트 <img src="${pageContext.request.contextPath}/images/info.png" width="15px"> <span id="cart_point"></span></li>
	 			</ul>
	 		</div>
	 		<div class="align-center">
	 			<input type="submit" value="결제하기">
	 		</div>
	 	</form>
		</c:if>
	</div>
</div>

</body>
</html>