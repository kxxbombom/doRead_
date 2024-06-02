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
					</li>
					<li>
						<label>공동현관 출입방법</label>
							<input type="radio" name="">
					</li>
				</ul>
			</div>
		
		</form>
	</div>	
</div>
</body>
</html>