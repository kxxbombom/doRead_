<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>뉴스게시판 글쓰기</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/kbm.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/ysw.css" type="text/css">
<script type="text/javascript">
	window.onload=function(){
		const myForm = document.getElementById('newsWrite_form');
		myForm.onsubmit=function(){
			const title = document.getElementById('news_title');
			if(title.value.trim() == ''){
				alert('제목을 입력하세요');
				title.value = '';
				title.focus();
				return false;
			}
			const content = document.getElementById('news_content');
			if(content.value.trim() == ''){
				alert('내용을 입력하세요');
				content.value = '';
				content.focus();
				return false;
			}
		};
	};
</script>

</head>
<body>
<div class="page-main">
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<div class="content-main newsHead">
		<h2>뉴스게시판 작성</h2>
		<hr size="1" noshade width="100%">
		<form id="newsWrite_form" action="newsWrite.do" method="post" enctype="multipart/form-data">
			<ul>
				<li>
					<input type="text" name="news_title" id="news_title" placeholder="제목을 입력하세요" maxlength="50">
				</li>
				<li>
					<textarea rows="25" cols="120" name="news_content" id="news_content" placeholder="내용을 입력하세요"></textarea>
				</li>
				<li>
					<input type="file" name="news_image" id="news_image" accept="image/gif, image/png, image/jpeg">
				</li>
			</ul>
			<div class="align-center list-btn">
				<input type="submit" value="등록">
				<input type="button" value="목록" onclick="location.href='newsList.do'">
			</div>
		</form>
	</div>
</div>
</body>
</html>