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
<jsp:include page="/WEB-INF/views/adminster/adminheader3.jsp"/>
<br><br>
<jsp:include page="/WEB-INF/views/adminster/adminheader.jsp"/>
<div class="page-main">

	<h2>판매내역</h2>
	<div class="align-center">
 			<form id="Form" action="adminList.do" method="get">
				<select id="selectlist" name ="keyf" class="input-style">
					<option value="1" <c:if test="${check == 1}">selected</c:if> >주문번호</option>
 					<option value="2" <c:if test="${check == 2}">selected</c:if> >회원아이디</option>
 					<option value="3" <c:if test="${check == 3}">selected</c:if> >상품명</option>
 				</select>
 				<input type="number" name="keyw" class="inputcheck input-style" id="search" maxlength="30" placeholder="">
 				<input id="subbtn" type="submit" value="검색" class="button2">
			</form>
			<script type="text/javascript">
				$('#search').click(function(){
					if($('#selectlist').val()==1){
						$('#search').attr('type','number');
					}else{
						$('#search').attr('type','text');
					}
				})
			
			</script>
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
				<th>배송상태</th> 
				<th>주문일</th>
			</tr>
		<c:forEach var="i" items="${list}">
			
			<tr>
				<td>${i.order_num}</td>
				<td><a href="detailOrder.do?order_num=${i.order_num}">${i.book_name}</a></td>
				<td><a href="${pageContext.request.contextPath}/adminster/detailUser.do?mem_num=${i.mem_num}">${i.mem_id}</a></td>
				<td> <fmt:formatNumber value="${i.order_total }"/>원</td>
				<td>
				<c:if test="${i.order_status == 1}">결제완료</c:if>
				<c:if test="${i.order_status == 2}">배송시작</c:if>
				<c:if test="${i.order_status == 3}">배송중</c:if>
				<c:if test="${i.order_status == 4}">배송완료</c:if>
				<c:if test="${i.order_status == 5}"><span style="color:red;">주문 취소</span></c:if>
				</td>
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
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</div>
</body>
</html>