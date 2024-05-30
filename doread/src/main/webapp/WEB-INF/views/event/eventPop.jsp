<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이벤트 팝업</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/kbm.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header_test.css" type="text/css">
</head>
<body>
<jsp:include page="/WEB-INF/views/news/test_header.jsp"/>
<div class="page-main">
	
	<div class="eventvar">
 	<h1>EVENT 응모</h1>
 	</div>
 	<p>
 	<div class="align-center">
	이벤트에 응모 하시겠습니까?<p>
	<input type="button" value="응모" class="eventbutton2" onclick="location.href='${pageContext.request.contextPath}/event/eventsucess.do?e_num=${e_num}'">		
	<input type="button" value="취소" class="eventbutton2" onclick="location.href='${pageContext.request.contextPath}/event/eventmain.do'">		
	</div>
</div>

</body>
</html>