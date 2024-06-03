<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주문 상세 내역</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/kts.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header_test.css" type="text/css">
</head>
<body>
<div class="page-main">
	<div class="content-main">
	<jsp:include page="/WEB-INF/views/news/test_header.jsp"/>
	<jsp:include page="/WEB-INF/views/member/mypageheader.jsp"/>
	
	<h2>주문상세내역</h2>
	<hr size="1" noshade width="100%">
	
	<table>
 			<tr>
 				<th>도서명</th>
 				<th></th>
 				<th>수량</th>
 				<th>도서가격</th>
 				<th>합계</th>
 			</tr>
 			<c:forEach var="detail" items="${detailList}">
				<tr>
					<td>
						<a href="${pageContext.request.contextPath}/book/detail.do?book_num=${detail.book_num}">
							<img src="${pageContext.request.contextPath}/upload/${detail.book_image}" width="100">
						</a>
					</td>
					<td>
						<a href="${pageContext.request.contextPath}/book/detail.do?book_num=${detail.book_num}">${detail.book_name}</a>
					</td>
					<td class="align-center">
						<fmt:formatNumber value="${detail.order_quantity}"/>
					</td>
					<td class="align-center">
						<fmt:formatNumber value="${detail.book_price}"/>원
					</td>
					<td class="align-center">
						<fmt:formatNumber value="${detail.book_total}"/>원
					</td>
				</tr>
 			</c:forEach>
 			<tr>
 				<td colspan="4" class="align-right"><b>총구매금액</b></td>
 				<td class="align-center"><fmt:formatNumber value="${order.order_total}"/>원</td>
 			</tr>
 		</table>
 		
		<div id="receive_info">
			<ul>
				<li>
					<span>배송지정보</span>
					<div>
						<ul>
							<li><span id="" class="">${order.receive_name} ${order.receive_phone}</span></li>
							<li><span id="" class="">${order.receive_zipcode}</span></li>
							<li><span id="" class="">${order.receive_address1}</span> <span id="displayAddress2" class="info-span">${order.receive_address2}</span></li>

						</ul>
					</div>
				</li>
				<li>
					<label>배송요청사항</label> ${order.order_msg}
				</li>
				<li>
					<label>공동현관 출입방법 : </label>
					<c:if test="${order.enter == 1}">
						공동현관 비밀번호 : ${order.enter_passwd}
					</c:if>
					<c:if test="${order.enter == 2}">
						자유출입 가능
					</c:if>
				</li>
				
			</ul>
			<div>
				<input type="button" value="배송지수정">
				<input type="button" value="주문취소">
				<input type="button" value="주문목록">
			</div>
		</div>
				
	</div>
</div>
</body>
</html>