<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마일리지	 내역 목록</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/kts.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header_test.css" type="text/css">
<script>
$(function(){
	$('#pointbtn').click(function(){
		const span = document.getElementById('usedPoint');
		const usedpoint = document.getElementById('pointval');
		const total = document.getElementById('total');
		const order_usepoint = document.getElementById('order_usepoint');
		const inpoint = document.getElementById('inpoint');
		const all_total =document.getElementById('all_total');
		const all_total_delivery =document.getElementById('all_total_delivery');
		if(usedpoint.value != 0)
		span.textContent = usedpoint.value;
		else{
		span.textContent = 0;
		}
		if(usedpoint.value != null)
		total.textContent=all_total_delivery.value-usedpoint.value;
		else{
		total.textContent= all_total_delivery.value;
		}
		
		inpoint.textContent = Math.floor((all_total.value-usedpoint.value)*0.03);
		
	});
</script>
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
				<th>지급일</th>
				<th>마일리지</th>
			</tr>
			<c:forEach var="order" items="${list}">
			<c:if test="${order.order_status != 5}">
			<tr class="mileage-list">
				<td>${order.order_num}</td>
				<td><a href="${pageContext.request.contextPath}/order/orderDetail.do?order_num=${order.order_num}">${order.book_name}</a></td>
				<td>${order.order_total}원</td>
				<td>${order.order_date}</td>
				<td>${Math.round(order.order_total*0.03)}P</td>
			</tr>
			</c:if>
			</c:forEach>		
		</table>
		<div style="text-align:center;">${page}</div>
		<ul>
			<li>
				<span id="allpoint"> 총 마일리지 : ${allpoint}P </span>
			</li>
		</ul>
	</div>
</div>
</body>
</html>