<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>e-book 목록</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/kts.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header_test.css" type="text/css">
</head>
<body>
<div class="page-main">
	<jsp:include page="/WEB-INF/views/member/mypageheader.jsp"/>
	<div class="post-header">
		<h2>e-book 목록</h2>
		<hr size="1" noshade width="100%">
		
		
		<c:if test="${count == 0}">
			<div class="result-display">
				e-book 구매내역이 없습니다.
			</div>
		</c:if>
		
		<c:if test="${count != 0}">
		
		<table class="main-table">
			<tr class="main-tr">
				<th class="main-td">주문번호</th>
				<th class="main-td">도서명</th>
				<th class="main-td">구매금액</th>
				<th class="main-td">책읽기</th>
			</tr>
			<c:forEach var="order" items="${list}">
			<tr>
				<td class="main-td">${order.order_num}</td>
				<td class="main-td"><a href="${pageContext.request.contextPath}/order/orderDetail.do?order_num=${order.order_num}">${order.book_name}</a></td>
				<td class="main-td"><fmt:formatNumber value="${order.book_price}"/>원</td>
				<td class="main-td">
					<input type="button" value="책읽기" class="button2" onclick="window.open('${pageContext.request.contextPath}/shopping/myebook.do?order_num=${order.order_num}','window','location=no, directories=no,resizable=no,status=no,toolbar=no,menubar=no, width=600,height=800,left=0, top=0, scrollbars=yes');return false">
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