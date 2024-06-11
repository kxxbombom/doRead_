<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 상세 내용</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/kts.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header_test.css" type="text/css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>
</head>
<body>
	<div class="page-main">
		<jsp:include page="/WEB-INF/views/member/mypageheader.jsp" />
		<div class="post-header">
			<h2>회원 상세 내용</h2>
			<hr size="1" noshade width="100%">
			<div class="detail-content">
				<ul>
					<li>이름: ${member.mem_id}</li>
					<li>아이디: ${member.mem_name}</li>
					<li>이메일: ${member.mem_email}</li>
					<li>전화번호: ${member.mem_phone}</li>
					<li>우편번호: ${member.mem_zipcode}</li>
					<li>주소: ${member.mem_address1} ${member.mem_address2}</li>
				</ul>
			</div>
		</div>
			<div class="detail-button">
				<input type="button" value="수정" onclick="location.href='modifyUserForm.do'">
				<input type="button" value="회원탈퇴" onclick="location.href='deleteUserForm.do'" >
			</div>
	</div>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>