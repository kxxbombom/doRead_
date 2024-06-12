<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내가 참여한 이벤트 내역</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/kts.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header_test.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>
</head>
<body>
<div class="page-main">
	<jsp:include page="/WEB-INF/views/member/mypageheader.jsp" />
	<div class="content-main">
		<div class="post-header">
			<h2>내가 참여한 이벤트 내역</h2>
			<hr size="1" noshade width="100%">
		</div>
		<c:if test="${count == 0}">
			<div class="result-display buylist-align-center">
				내역이 없습니다.
			</div>
		</c:if>
		<c:if test="${count> 0}">
		<table class="main-table">
			<tr class="main-tr">
				<th class="main-td">이벤트 번호</th>
				<th class="main-td">이벤트 명</th>
				<th class="main-td">당첨 여부</th>
			</tr>
			<c:forEach var="event" items="${list}">
			<input type="hidden" name="e_num" id="e_num" value="${event.e_num}">
			<tr>
				<td class="main-td">${event.e_num}</td>
				<td class="main-td"><a href="${pageContext.request.contextPath}/event/detailEvent.do?e_num=${event.e_num}">${event.e_title}</a></td>
				<td class="main-td">
					<c:if test="${empty event.e_mem_num and empty event.ed_result }">
						<legend>진행중</legend>
					</c:if>
					<c:if test="${!empty event.e_mem_num and empty event.ed_result }">
						<legend>미당첨</legend>
					</c:if>
					<c:if test="${!empty event.e_mem_num and !empty event.ed_result }">
						<legend style="color:blue;"><b>당첨</b></legend>
					</c:if>
				</td>
			</tr>
			</c:forEach>	
		</table>
		</c:if>
	</div>
</div>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>