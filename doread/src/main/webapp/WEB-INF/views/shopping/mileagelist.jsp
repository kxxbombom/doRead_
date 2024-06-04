<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마일리지	 내역 목록</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/kts.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header_test.css" type="text/css">
</head>
<body>
<div class="page-main">
	<jsp:include page="/WEB-INF/views/member/mypageheader.jsp"/>
	<div class="content-main">
		<h2>마일리지 내역 목록</h2>
		<hr size="1" noshade width="100%">
		<table>
			<tr>
				<th>주문번호</th>
				<th>도서명</th>
				<th>총구매금액</th>
				<th>마일리지</th>
			</tr>
			<c:forEach var="order" items="${list}">
			<c:if test="${order.order_status == 1}">
			<tr class="mileage-list">
				<td>${order.order_num}</td>
				<td><a href="${pageContext.request.contextPath}/order/orderDetail.do?order_num=${order.order_num}">${order.book_name}</a></td>
				<td>${order.order_total}</td>
				<td>${Math.round(order.order_total*0.03)}</td>
			</tr>
			</c:if>
			</c:forEach>		
		</table>
		<div style="text-align:center;">${page}</div>
		<ul>
			<li>
				총 마일리지 ${point}원
			</li>
		</ul>
		
		
	</div>
</div>
</body>
</html>