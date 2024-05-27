<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${result_title}</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/kbm.css" type="text/css">
</head>
<body>
<div class="page-main">
	<jsp:include page="/WEB-INF/views/member/loginheader.jsp"/>
	<div class="content-main login-info">
		<h2>${result_title}</h2>
		<hr size="1" noshade width="800">
		<div class="result-display">
			<div class="align-center">
				${result_msg}
				<p>
				<input type="button" value="확인" onclick="location.href='${result_url}'">
			</div>
		</div>
		<hr size="1" noshade width="800">
	</div>
</div>
</body>
</html>



