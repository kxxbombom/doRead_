<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>구매 내역 조회</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/kts.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header_test.css" type="text/css">
</head>
<body>
<div class="page-main">
	<jsp:include page="/WEB-INF/views/member/mypageheader.jsp"/>
	<div class="content-main">
		<h2>구매 내역 조회</h2>
		<hr size="1" noshade width="100%">
		
		<form id="list_search_form" action="buylist.do" method="get">
			<ul class="list_search buylist-search" >
				<li>
					<select name="keyfield" class="list_search">
						<option value="1" <c:if test="${param.keyfield == 1}">selected</c:if>>도서명</option>
						<option value="2" <c:if test="${param.keyfield == 2}">selected</c:if>>주문번호</option>
					</select>
				</li>
				<li>
					<input type="search"  class="list_search" size="16" name="keyword" id="keyword" value="${param.keyword}">
				</li>
				<li>
					<input type="submit" value="검색" class="list_search">
				</li>
			</ul>
		</form>
		<script type="text/javascript">
		
			const myForm = document.getElementById('list_search_form');
	
			myForm.onsubmit=function(){
				const keyword = document.getElementById('keyword');
				if(keyword.value.trim() == ''){
					alert('검색어를 입력하세요');
					keyword.value = '';
					keyword.focus();
					return false;
				}
			};
		</script>
		
		<c:if test="${count == 0}">
			<div class="result-display">
				구매내역이 없습니다.
			</div>
		</c:if>
		
		<c:if test="${count != 0}">
		
		<table class="main-table">
			<tr class="main-tr">
				<th class="main-td">주문번호</th>
				<th class="main-td">도서명</th>
				<th class="main-td">총구매금액</th>
				<th class="main-td">주문날짜</th>
				<th class="main-td">주문/배송 상태</th>
			</tr>
			<c:forEach var="order" items="${list}">
			<tr>
				<td class="main-td">${order.order_num}</td>
				<td class="main-td"><a href="${pageContext.request.contextPath}/order/orderDetail.do?order_num=${order.order_num}">${order.book_name}</a></td>
				<td class="main-td">${order.order_total}원</td>
				<td class="main-td">${order.order_date}</td>
				<td class="main-td">
					<c:if test="${order.order_status == 1}">결제완료</c:if>
					<c:if test="${order.order_status == 2}">배송시작</c:if>
					<c:if test="${order.order_status == 3}">배송중</c:if>
					<c:if test="${order.order_status == 4}">배송완료</c:if>
					<c:if test="${order.order_status == 5}">주문취소</c:if>
				</td>
			</tr>
			</c:forEach>		
		</table>
		
		<div style="text-align:center;">${page}</div>
		
		</c:if>
	</div>
</div>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>