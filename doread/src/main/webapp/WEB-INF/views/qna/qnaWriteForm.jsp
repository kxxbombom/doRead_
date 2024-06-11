<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>문의하기</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header_test.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/kbm.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/ssh2.css" type="text/css">
<script type="text/javascript">
window.onload=function(){
	const myForm = document.getElementById('write_form');
	//이벤트 연결
	myForm.onsubmit = function(){
		const title = document.getElementById('q_title');
		if(title.value.trim()==''){
			alert('제목을 입력하세요');
			title.value='';
			title.focus();
			return false;
		}
		const content= document.getElementById('q_content');
		if(content.value.trim()==''){
			alert('내용을 입력하세요');
			content.value='';
			content.focus();
			return false;
		}
	};
};
</script>
</head>
<body>
<div class="page-main">
	<jsp:include page="/WEB-INF/views/news/test_header.jsp"/>
	<div class="content-main">
		<h2 style="text-align:center;">문의하기</h2>
		<hr size="1.5" width="800px" noshade="noshade">
		<form id="write_form" action="qnaWrite.do" method="post" class="align-center" enctype="multipart/form-data">
			<ul class="align-left">
				<li>
					<label for="q_title">문의 제목</label>
					<input type="text" class="input-style" name="q_title" id="q_title" maxlength="50">
				</li>
				<li>
					<label for="q_content">문의 내용</label>
					<textarea rows="5" cols="20" class="input-style" name="q_content" id="q_content"></textarea>
				</li>
				<li>
					<label for="q_image">이미지</label>
					<input type="file" name="q_image"  class="input-style" id="q_image" accept="image/gif,image/png,image/jpeg">
				</li>
				<li>
					<label for="q_auth">문의 유형</label>
					<input type="radio" name="q_auth" value="0" id="auth0">일반
					<input type="radio" name="q_auth" value="1" id="auth1">신고
					<input type="radio" name="q_auth" value="2" id="auth2">정지
					<input type="radio" name="q_auth" value="3" id="auth3">도서신청
					<input type="radio" name="q_auth" value="4" id="auth4">교환 및 반품
				</li>
			</ul>
			<div class="align-center">
				<input type="submit" class="button2" value="등록">
				<input type="button"  class="button2" value="목록" onclick="location.href='qnaList.do'">
			</div>
		</form>
		<hr size="1.5" width="800px" noshade="noshade">
	</div>
</div>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>