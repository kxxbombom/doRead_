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

</head>
<body>

<br><br>
<jsp:include page="/WEB-INF/views/adminster/adminheader3.jsp"/>
<br><br>
<jsp:include page="/WEB-INF/views/adminster/adminheader.jsp"/>
<div class="page-main">

	<h2>판매내역</h2>
	<div class="align-center">
 			<form id="Form" action="adminList.do" method="get">
				<select id="selectlist" name ="keyf" class="input-style">
					<option value="1" <c:if test="${check == 1}">selected</c:if>>주문이름</option>
 					<option value="2" <c:if test="${check == 2}">selected</c:if> >주문번호</option>
 					
 				</select>
 				<input type="text" name="keyw" class="inputcheck input-style" id="search" maxlength="30" placeholder="">
 				<input id="subbtn" type="submit" value="검색" class="button2">
			</form>
	</div>	
			
	
	<hr size="1.5" width="80%" noshade="noshade">
	
	<c:if test="${empty list}">
		판매 목록이 없습니다.
	</c:if>
 	

	<div class="content-main">
	
		<c:if test="${!empty list}">
		<table>
			<tr>
			<th>주문번호</th>
			<th>주문이름</th>
			<th>회원번호</th>
			<th>주문금액</th> 
			<th>주문일</th>
			
			</tr>
		<c:forEach var="i" items="${list}">
			
			<tr>
			<td>${i.order_num}</td>
			<td><a href="detailOrder.do?order_num=${i.order_num}">${i.book_name}</a></td>
			<td><a href="${pageContext.request.contextPath}/adminster/detailUser.do?mem_num=${i.mem_num}">${i.mem_num}</a></td>
			<td> <fmt:formatNumber value="${i.order_total }"/>원</td>
			<td>${i.order_date}</td>
			</tr>
		</c:forEach>
		</table>
		</c:if>

	</div>
	<div class="align-center">
	${page}
	
	</div>
	<div class="align-center">
	<p>
	<br>
	<input type="button" value="메인" class="button2" onclick="location.href='${pageContext.request.contextPath}/main/main.do'">
	
	</div>
	

</div>
</body>
</html>