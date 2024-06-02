<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주문/결제</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/kbm.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header_test.css" type="text/css">

<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/order.form.js"></script>
</head>
<body>
<div class="page-main">
	<jsp:include page="/WEB-INF/views/news/test_header.jsp"/>
	<div class="content-main">
		<h2>주문/결제</h2>
		<form id="order_form" action="order.do" method="post">
			<div id="receive_info">
				<ul>
					<li>
						<label>배송지 정보</label>
						<div>
							<ul>
								<li>${member.mem_name}</li>
								<li>${member.mem_phone}</li>
								<li>${member.mem_zipcode} ${member.mem_address1} ${member.mem_address2}</li>
							</ul>
						</div>
					</li>
					<li>
						<label>배송요청사항</label>
						<select name="order_message">
							<option value="1">선택해 주세요.</option>
							<option value="2">문 앞에 놓아주세요.</option>
							<option value="3">부재시 경비실에 맡겨주세요.</option>
							<option value="4">부재시 연락주세요.</option>
							<option value="5">배송 전 연락주세요.</option>
							<option value="6">직접 입력</option>
						</select>
						<input type="text" name="order_msg">
					</li>
					<li>
						<label for="enter">공동현관 출입방법</label>
							<input type="radio" name="enter" value="passwd">공동현관 비밀번호
							<input type="radio" name="enter" value="free">자유출입 가능
							<input type="text" name="enter_passwd" placeholder="정확한 공동현관 출입번호를 입력하세요">
					</li>
				</ul>
			</div>
			
			<div>
				주문상품 총 
				<table id="cartTable">
	 			<c:forEach var="cart" items="${list}">
	 				<tr>
	 					<td>
	 						<a href="${pageContext.request.contextPath}/book/detail.do?book_num=${cart.book_num}">
	 							<img src="${pageContext.request.contextPath}/upload/${cart.bookVO.book_img}" width="100">
	 						</a>
	 					</td>
	 					<td>
	 						<a href="${pageContext.request.contextPath}/book/detail.do?book_num=${cart.book_num}">
	 							${cart.bookVO.book_name}
	 						</a>
	 					</td>
	 					<td class="align-center">
	 						<input type="number" name="c_quantity" min="1" max="${cart.bookVO.stock}" value="${cart.c_quantity}" class="quantity-width">
	 					</td>
	 					<td class="align-center">
	 						<fmt:formatNumber value="${cart.bookVO.price}"/>원
	 					</td>
	 				</tr>
	 			</c:forEach>
	 		</table>
			</div>
			
			<div>
			<ul>
				<li>포인트</li>
				<li>보유 원</li>
			</ul>
			통합 포인트 원 <input type="number" min="0" max="">
			<input type="button" name="pointpayment" value="사용">
			</div>
			
			<div class="cartList">
		 			<ul>
		 				<li class="flex-container">상품 금액 <span><b><fmt:formatNumber value="${all_total}"/></b>원</span></li>
		 				<li class="flex-container">
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
		 				<li class="flex-container" style="margin-bottom:20px;">포인트 사용 <span><fmt:formatNumber value=""/>원</span></li>

		 				
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
	 					<input type="submit" value="결제하기">
	 				</div>
		 		</div>
		</form>
	</div>	
</div>
</body>
</html>