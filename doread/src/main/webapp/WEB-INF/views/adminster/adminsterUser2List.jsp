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
	<h2>정지 회원</h2>
		
	<hr size="1.5" width="80%" noshade="noshade">
	
	<div class="content-main">
		<c:if test="${empty member }">
			정지된 회원이 없습니다.
		</c:if>
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
				<c:if test="${i.mem_auth==1}">
				<tr>
					<td>${i.mem_num}</td>
					<td>${i.mem_name}</td>
					<td>${i.mem_id }</td>
					<td> <a href="detailUser.do?mem_num=${i.mem_num}">${i.mem_auth}</a></td>
					<td>${i.mem_email}</td>
					<td>${i.mem_email}</td>
					<td>${i.mem_rdate}</td>
				</tr>
				</c:if>
			</c:forEach>
			</table>
		</c:if>
	</div>

	<div class="align-center">
		${page}
		<p>
		<br>
		<input type="button" value="메인" class="button2" onclick="location.href='${pageContext.request.contextPath}/main/main.do'">
	</div>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</div>
</body>
</html>
