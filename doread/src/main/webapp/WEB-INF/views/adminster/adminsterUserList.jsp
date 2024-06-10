<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자페이지</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/ssh.css" type="text/css">

</head>
<body>
<jsp:include page="/WEB-INF/views/adminster/adminheader3.jsp"/>
<br><br>
<jsp:include page="/WEB-INF/views/adminster/adminheader.jsp"/>
<div class="page-main">
<br>
	<h2>회원 관리</h2>
	
		<form id="Form" action="userList.do" method="get">
				<select id="selectlist" name ="keyf" class="input-style">
					<option value="1" <c:if test="${check == 1}">selected</c:if> >회원아이디</option>
 					<option value="2" <c:if test="${check == 2}">selected</c:if> >회원이름</option>
 					<option value="3" <c:if test="${check == 3}">selected</c:if> >이메일</option>
 				</select>
 				<input type="text" name="keyw" class="inputcheck input-style" id="search" maxlength="30" placeholder="">
 				<input id="subbtn" type="submit" value="검색" class="button2">
		</form>
	<hr size="1.5" width="80%" noshade="noshade">
			
	<c:if test="${empty member}">
		가입된 회원이 없습니다.
	</c:if>
 	

	<div class="content-main">
		<c:if test="${!empty member}">
		<table>
			<tr>
				<th>회원번호</th>
				<th>회원이름</th>
				<th>회원아이디</th>
				<th>회원등급</th> 
				<th>회원전화번호</th>
				<th>회원이메일</th>
				<th>회원가입일</th>
			</tr>
		<c:forEach var="i" items="${member}">
			
			<tr>
				<td>${i.mem_num}</td>
				<td>${i.mem_name}</td>
				<td>${i.mem_id }</td>
				<td> <a href="detailUser.do?mem_num=${i.mem_num}">${i.mem_auth}</a></td>
				<td>${i.mem_email}</td>
				<td>${i.mem_email}</td>
				<td>${i.mem_rdate}</td>
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