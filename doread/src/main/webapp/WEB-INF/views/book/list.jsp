<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>도서 목록</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
</head>
<body>
<div class="page-main">
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<div class="content-main">
		<form id="search_form" action="bookList.do" method="get">
			<input type="search" size="15" name="bookSearch" id="bookSearch" maxlength="30">
		</form>
	</div>
	</div>
</body>
</html>