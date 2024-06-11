<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>e-book읽기</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>
<script type="text/javascript">
	$(function(){
		$('body').mouseout(function(){
			$('.e_book').addClass('disabled');
		});
		$('body').mouseover(function(){
			$('.e_book').removeClass('disabled');
		});
	})

</script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/ssh2.css" type="text/css">
</head>
<body>
<div class="e_book" draggable="false">
<h2>${book.book_name}</h2>
<h1>
</h1>
${book.book_content}
</div>
</body>
</html>