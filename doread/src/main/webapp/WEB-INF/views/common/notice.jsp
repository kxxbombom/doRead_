<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>안내</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/ssh2.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/kbm.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header_test.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>
</head>
<body>
<div class="page-main">
	<jsp:include page="/WEB-INF/views/news/test_header.jsp"/>
	<div class="content-main">
		<h2>안내</h2>
		<div class="result-display">
			<div class="align-center">
				<c:if test="${!empty accessMsg}">
				${accessMsg}
				</c:if>
				<c:if test="${empty accessMsg}">
				<h4>잘못된 접속입니다.</h4>
				</c:if>
				<p>
				<c:if test="${!empty accessUrl}">
				<input type="button" value="이동"
				   onclick="location.href='${accessUrl}'">
				</c:if>
				<c:if test="${empty accessUrl}">
				<input type="button" class="button2" value="홈으로"
				  onclick="location.href='${pageContext.request.contextPath}/main/main.do'">
				</c:if>
			</div>
		</div>
	</div>
</div>
</body>
</html>






