<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>중고게시판 메인</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/kbm.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header_test.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>
</head>
<body>
<div class="page-main">
	<jsp:include page="/WEB-INF/views/news/test_header.jsp"/>
	<div class="content-main">
 		<h2>중고게시판</h2>	
 			<div class="float-clear">
				<hr width="100%" size="1" noshade="noshade">
			</div>
		
	
 			<form id="usedForm" action="usedList.do" method="get">
				<select id="selectlist" name ="sel" class="input-style">
				<option value="1" <c:if test="${check =='1'}">selected</c:if>>판매중</option>
 				<option value="2" <c:if test="${check =='2'}">selected</c:if>>거래중</option>
 				<option value="3" <c:if test="${check == '3'}">selected</c:if>>판매완료</option>
 				</select>
 				<select id="selectlist2" name ="sel2" class="input-style">
				<option value="1" <c:if test="${check2 == '1'}">selected</c:if>>책제목</option>
 				<option value="2" <c:if test="${check2 == '2'}">selected</c:if>>글제목</option>
 				<option value="3" <c:if test="${check2 == '3'}">selected</c:if>>아이디</option>
 				</select>
 				<input type="text" name="search" class="inputcheck input-style" id="search" maxlength="30" placeholder="검색내용입력">
 				<input id="subbtn" type="submit" value="검색" class="button2">
			</form>
		
			
 			<div class="align-right">
 			<c:if test="${!empty user_num}">
 				<input type="button" value="글쓰기" class="button2" onclick="location.href='${pageContext.request.contextPath}/used/usedWriteForm.do'">
 			</c:if>
 			
 			
 			<c:if test="${empty list}">
 				작성 게시글이 없습니다
 			</c:if>
 			<c:if test="${!empty list }">
 				<table  id="usedtable">
 					<tr>
 						<th>no.</th>
 						<th>책제목</th>
 						<th>가격</th>
 						<th>글 제목 </th>
 						<th>판매상태</th>
 						<th>작성자</th>
 						<th>조회수</th>
 					</tr>
 				<c:forEach var="i" items="${list}">
 					<c:if test="${i.u_auth == 0}">
 					<tr>
 						<td>${i.u_num}</td>
 						<td>${i.book_name}</td>
 						<td><fmt:formatNumber value="${i.u_price}"/>원</td>
 						<td><a href="${pageContext.request.contextPath}/used/usedDetail.do?u_num=${i.u_num}">${i.u_title}</a></td>
 						<td><c:if test="${i.u_state ==1}">판매중</c:if>
 							<c:if test="${i.u_state ==2}">거래중</c:if>
 							<c:if test="${i.u_state ==3}">판매완료</c:if>
 						</td>
 						<td>${i.id}</td>
 						<td>${i.u_hit}</td>
 					</tr>
 					</c:if>
 				</c:forEach>
 				</table>
 			</c:if>
			</div>
		
			
			
			
			
			<div class="float-clear">
				<hr width="100%" size="1" noshade="noshade">
			</div>
		

 	
			
			
			

	</div>
</div>
</body>
</html>

