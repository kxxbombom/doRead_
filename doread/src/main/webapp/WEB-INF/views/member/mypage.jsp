<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>mypage</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/kts.css" type="text/css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/ysw.css" type="text/css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>
</head>
<body>
	<div class="page-main">
		<jsp:include page="/WEB-INF/views/common/header.jsp" />
		<jsp:include page="/WEB-INF/views/member/mypageheader.jsp"/>
			<div class="mypage-button">
				<input type="button" value="찜" onclick="location.href='${like}'">
				<input type="button" value="추천" onclick="location.href='${best}'">
			</div>
	</div>
</body>
</html>























