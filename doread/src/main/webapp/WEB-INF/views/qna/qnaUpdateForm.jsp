<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>문의 수정</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/ysw.css" type="text/css">
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
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<div class="content-main">
		<h2>문의 수정</h2>
		<form id="write_form" action="qnaUpdate.do" method="post" enctype="multipart/form-data">
		<input type="hidden" name="q_num" value="${qna.q_num}">
			<ul>
				<li>
					<label for="q_title">제목</label>
					<input type="text" name="q_title" id="q_title" maxlength="50" value="${qna.q_title }">
				</li>
				<li>
					<label for="q_content">내용</label>
					<textarea rows="5" cols="40"  name="q_content" id="q_content" >${qna.q_content}</textarea>
				</li>
				<li>
					<label for="q_image">이미지</label>
					<input type="file" name="q_image" id="q_image" accept="image/gif,image/png,image/jpeg">
				</li>
				<li>
					<label for="q_auth">문의 유형</label>
					<input type="radio" name="q_auth" value="0" id="auth0">일반
					<input type="radio" name="q_auth" value="1" id="auth1">신고
					<input type="radio" name="q_auth" value="2" id="auth2">정지
				</li>
			</ul>
			<div class="align-center">
				<input type="submit" value="수정">
				<input type="button" value="목록" onclick="location.href='qnaList.do'">
			</div>
		</form>
	</div>
</div>
</body>
</html>