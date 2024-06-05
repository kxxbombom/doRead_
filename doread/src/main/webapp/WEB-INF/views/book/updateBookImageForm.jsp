<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>도서 이미지 변경</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/kbm.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/kyj.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header_test.css" type="text/css">
<script type="text/javascript">
window.onload=function(){
	const imageForm = document.getElementById('image_form');
	imageForm.onsubmit=function(){
		const image = document.getElementById('book_img');
		if(image.value.trim()==''){
			alert('도서 이미지는 필수 등록해야합니다');
			return false;
		}
	};
};
</script>
</head>
<body>
	<br>
	<br>
	<jsp:include page="/WEB-INF/views/adminster/adminheader3.jsp" />
	<br>
	<br>
	<jsp:include page="/WEB-INF/views/adminster/adminheader.jsp" />
	<div class="page-main">
		<h2>도서 정보 수정</h2>
		<hr size="1.5" width="80%" noshade="noshade">
		<form id="image_form" action="updateBookImage.do" method="post" enctype="multipart/form-data">
			<input type="hidden" name="book_num" value="${book.book_num}">
			<div class="content-main">
				<input type="file" class="input-check" name="book_img" id="book_img" accept="image/png,image/gif,image/jpeg">
			</div>
			<div class="align-center">
				<input type="submit" value="등록" class="update-button">
				<input type="button" value="취소" onclick="history.go(-1)">
			</div>
		</form>
		<hr size="1.5" width="80%" noshade="noshade">
	</div>
</body>
</html>