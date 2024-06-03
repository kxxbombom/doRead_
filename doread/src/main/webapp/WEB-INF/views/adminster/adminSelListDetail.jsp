<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자페이지</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/ssh.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/ysw.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>
</head>
<body>

<br><br>
<jsp:include page="/WEB-INF/views/adminster/adminheader3.jsp"/>
<br><br>
<jsp:include page="/WEB-INF/views/adminster/adminheader.jsp"/>
<div class="page-main">

	<h2>판매상세내역</h2>
		
			
	
	<hr size="1.5" width="80%" noshade="noshade">

	<div class="content-main">
	
		<c:if test="${!empty list}">
		<table>
			<tr>
			<th>상세번호</th>
			<th>책이름</th>
			<th>책가격</th>
			<th>수량</th> 
			<th>합계</th>
			
			</tr>
		<c:forEach var="i" items="${list}">
			
			<tr>
			<td>${i.order_num}</td>
			<td><a href="${pageContext.request.contextPath}/book/detail.do?book_num=${i.book_num}"><img width="200" height="270" src="${pageContext.request.contextPath}/upload/${i.book_image}"></a><br>${i.book_name}</td>
			<td>${i.book_price}</td>
			<td>${i.order_quantity}</td>
			<td> <fmt:formatNumber value="${i.book_total }"/>원</td>
			</tr>
			<tr>
				<td colspan="5"><hr size="1.5" width="80%" noshade="noshade"></td>
			</tr>
		</c:forEach>
		</table>
		</c:if>

	</div>
	
	<div class="align-center" style="margin:0 auto;">
	
	<ul class="align-left">
		<li>받는 사람 : ${order.receive_name }</li>
		<li>전화번호 : ${order.receive_phone }</li>
		<li>우편번호 : ${order.receive_zipcode }</li>
		<li>주소 : ${order.receive_address1 }  &nbsp;  ${order.receive_address2 }</li>
		<li>
		<span>배송상태 : </span>
		<c:if test="${order.order_status == 1}">배송대기</c:if>
		<c:if test="${order.order_status == 2}">배송준비중</c:if>
		<c:if test="${order.order_status == 3}">배송중</c:if>
		<c:if test="${order.order_status == 4}">배송완료</c:if>
		<c:if test="${order.order_status == 5}"><span style="color:red;">주문 취소</span></c:if>
		<input type="button" class="button2"  value="배송상태변경" id="delivery">
		<form id="deliform" class="hide" method="post">
		<select name="order_status" id="order_status" class="input-style"  >
				<option value="1">배송대기</option>
				<option value="2">배송준비중</option>
				<option value="3">배송중</option>
				<option value="4">배송완료</option>
		</select>
		<input type="submit" class="button2"  value="변경" id="upbtn">
		</form>
		</li>
	</ul>
	<p>
	<br>
	<input type="button" value="메인" class="button2" onclick="location.href='${pageContext.request.contextPath}/main/main.do'">
	
	</div>
	<script type="text/javascript">
		$('#deliform').submit(function(event){
			
			
			
			
			event.preventDefault();
			
		});
	
	
	</script>

</div>
</body>
</html>