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

	<div class="content-main ordercom">
		<h2>${result_title}</h2>
		<hr size="1" noshade width="800">
		<div class="result-display">
			<div class="align-center ordercom-main">
				${result_msg}
				<p><p>
				<input type="button" class="homebtn" value="홈으로" onclick="location.href='${result_url}'">

				<span class="list-btn3">
				<input type="button" class="ordercombtn" value="주문/배송 조회" onclick="location.href='${pageContext.request.contextPath}/shopping/buylist.do'">
				</span>


			</div>
		</div>
		<hr size="1" noshade width="800" style="margin-bottom:80px;">
	</div>
</div>
</body>
</html>






