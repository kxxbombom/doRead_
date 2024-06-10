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
	<div class="content-main list-header" id="main">
		<h2>장바구니</h2>
		
		<c:if test="${empty list}">
		
		<form id="cart_order">
	 	<div class="cart-container">	
	 		<table id="cartTable">
	 			<tr>
	 				<th>도서명</th>
	 				<th></th>
	 				<th>수량</th>
	 				<th>도서가격</th>
	 				<th>합계</th>
	 			</tr>

	 				<tr>
	 					<td rowspan="4" colspan="5" style="height:200px;">
	 						<img src="${pageContext.request.contextPath}/images/noItem.png" width="30px;">
	 						<br>
	 						<span style="color:#666b66">장바구니에 담은 상품이 없습니다.</span>
	 					</td>
	 				</tr>
	 		</table>
	 		
		 		<div class="cartList">
		 			<ul>
		 				<li class="flex-container">상품 금액 <span><b><fmt:formatNumber value="${all_total}"/></b>원</span></li>
		 				<li class="flex-container" style="margin-bottom:20px;">
		 					<span class="tooltip-container">
		 						배송비<img src="${pageContext.request.contextPath}/images/info.png" class="tooltip" width="15px">
		 						<span class="tooltip-text">도서 1만5천원 이상 구매시 무료배송<br>
									도서 1만5천원 미만 구매시 배송비 2,500원 부과</span>
							</span>		
							<span>0원</span>
		 				</li>
		 				
						<hr size="1" width="100%">
		 				<li class="flex-container" style="margin-top:20px;"><b>결제 예정 금액</b> <span><b>0</b>원</span></li>
		 				<li class="flex-container">
		 					<span class="tooltip-container">
		 						적립 예정 포인트 <img src="${pageContext.request.contextPath}/images/info.png" class="tooltip" width="15px">
		 						<span class="tooltip-text">상품 주문 시 적립 예정 포인트가 자동 합산되고 주문하신 상품이 발송완료 된 후에 자동으로 적립됩니다.<br>
									상품구매시 구매액의 3%가 적립됩니다.</span>
								</span>
		 					<span><fmt:formatNumber value="${point}"/>P</span>
		 				</li>
		 			</ul>
		 			
		 			<div class="align-center cart-submit">
	 					<input type="submit" value="주문하기" disabled>
	 				</div>
		 		</div>
	 		</div>
	 		
	 	</form>
		</c:if>
		
		
		
		<c:if test="${!empty list}">
		
	 	<form id="cart_order" action="${pageContext.request.contextPath}/order/orderForm.do" method="post">
	 	<div id="cart_container">	
	 		<table id="cartTable">
	 			<tr>
	 				<th>도서명</th>
	 				<th></th>
	 				<th>수량</th>
	 				<th>도서가격</th>
	 				<th>합계</th>
	 			</tr>
	 			<c:forEach var="cart" items="${list}">
	 				<tr>
	 					<td>
	 						<a href="${pageContext.request.contextPath}/book/detail.do?book_num=${cart.book_num}">
	 							<img src="${pageContext.request.contextPath}/upload/${cart.bookVO.book_img}" width="100" style="margin:20px 0;">
	 						</a>
	 					</td>
	 					<td>
	 						<a href="${pageContext.request.contextPath}/book/detail.do?book_num=${cart.book_num}">
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
	 					<td class="align-center" style="border-left:1px;">
	 						<fmt:formatNumber value="${cart.sub_total}"/>원
	 						<br>
	 						<input type="button" value="삭제" class="cart-del" id="cart-del" data-cartnum="${cart.c_num}">
	 						
	 					</td>
	 				</tr>
	 			</c:forEach>
	 		</table>
	 		
		 		<div class="cartList">
		 			<ul>
		 				<li class="flex-container">상품 금액 <span><b><fmt:formatNumber value="${all_total}"/></b>원</span></li>
		 				<li class="flex-container" style="margin-bottom:20px;">
		 					<span class="tooltip-container">
		 						배송비<img src="${pageContext.request.contextPath}/images/info.png" class="tooltip" width="15px">
		 						<span class="tooltip-text">도서 1만5천원 이상 구매시 무료배송<br>
									도서 1만5천원 미만 구매시 배송비 2,500원 부과</span>
							</span>
			 					<span>
								<c:if test="${all_total >= 15000}">
								0원
								</c:if>
								<c:if test="${all_total < 15000}">
								<fmt:formatNumber value="2500"/>원
								</c:if>
								
							</span>
		 				</li>
		 				
						<hr size="1" width="100%">
		 				<li class="flex-container" style="margin-top:20px;"><b>결제 예정 금액</b> <span><b><fmt:formatNumber value="${all_total_delivery}"/></b>원</span></li>
		 				<li class="flex-container">
		 					<span class="tooltip-container">
		 						적립 예정 포인트 <img src="${pageContext.request.contextPath}/images/info.png" class="tooltip" width="15px">
		 						<span class="tooltip-text">상품 주문 시 적립 예정 포인트가 자동 합산되고 주문하신 상품이 발송완료 된 후에 자동으로 적립됩니다.<br>
									상품구매시 구매액의 3%가 적립됩니다.</span>
								</span>
		 					<span><fmt:formatNumber value="${point}"/>P</span>
		 				</li>
		 			</ul>
		 			
		 			<div class="align-center cart-submit">
	 					<input type="submit" value="주문하기(${cart_count})">
	 					
	 				</div>
		 		</div>
	 		</div>
	 		
	 	</form>
	 	
	 	<div class="cart-notice">
	 			<label>장바구니 유의사항</label>
	 			<ul>
	 				<li>- 상품별 배송일정이 서로 다를시 가장 늦은 일정의 상품 기준으로 모두 함께 배송됩니다.</li>
	 				<li>- 배송지 수정시 배송일정이 변경될 수 있습니다.</li>
	 				<li>- 통합포인트 사용시 적립예정포인트가 변동 될 수 있습니다.</li>
	 			</ul>
	 	</div>
	 	
		</c:if>
	</div>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</div>
</body>
</html>