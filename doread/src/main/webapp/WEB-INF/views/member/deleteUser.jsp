<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<c:if test="${check}">
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원탈퇴</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/kts.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header_test.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/kbm.css" type="text/css">
</head>
<body>
<div class="page-main">
	<jsp:include page="/WEB-INF/views/news/test_header.jsp"/>
	<div class="list-space align-right">
		<h2>회원탈퇴 완료</h2>
		<div class="float-clear">
			<br>
			<hr width="100%" size="1" noshade="noshade">
		</div>
		<div class="result-display">
				<div class="align-center">
					회원탈퇴가 완료되었습니다.
					<p>
						<div class="list-btn"><input type="button" value="홈으로"
							onclick="location.href='${pageContext.request.contextPath}/main/main.do'">
					</div>
				</div>
		</div>
		<div class="float-clear">
			<br>
			<hr width="100%" size="1" noshade="noshade">
		</div>
	</div>
</div>
</body>
</html>
</c:if>
<c:if test="${!check}">
	<script>
		alert('입력한 정보가 정확하지 않습니다.');
		history.go(-1);
	</script>
</c:if>











