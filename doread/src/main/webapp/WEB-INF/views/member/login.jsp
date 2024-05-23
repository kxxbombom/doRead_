<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:choose>
<c:when test="${auth == 1}">

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인정보</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/kbm.css" type="text/css">
</head>
<body>
<div class="page-main">
	<%-- 로그인용 header 만들기 --%>
	<div class="content-main">
		<h2>회원정보</h2>
		<div class="result-display">
			정지된 회원입니다
			<p>
			<input type="button" value="홈으로" onclick="location.href='${pageContext.request.contextPath}/main/main.do'">
		</div>
	</div>
</div>
</body>
</html>

</c:when>
<c:otherwise>
	<script type="text/javascript">
		alert('회원정보 불일치');
		history.go(-1);
	</script>
</c:otherwise>
</c:choose>