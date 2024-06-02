<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${result_title}</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/kbm.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header_test.css" type="text/css">

</head>
<body>
<div class="page-main">
	<jsp:include page="/WEB-INF/views/news/test_header.jsp"/>

	<div class="content-main">
		<h2>${result_title}</h2>
		<div class="result-display">
			<div class="align-center">
				${result_msg}
				<p>
				<input type="button" value="홈으로" onclick="location.href='${result_url}'">
				<input type="button" value="주문/배송 조회" onclick="location.href='${pageContext.request.contextPath}/shopping/buylist.do'">
			</div>
		</div>
	</div>
</div>
</body>
</html>






