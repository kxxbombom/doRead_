<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 찾기</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/kbm.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>
<script type="text/javascript">
$(function(){
	$('#findPWNoID_form').submit(function(){
		const id = document.getElementById('id');
		if(id.value.trim() == ''){
			alert('아이디를 입력하세요')
			id.value == '';
			id.focus();
			return false;
		}
	});
});
</script>
</head>
<body>
<div class="page-main">
	<div class="content-main">
	<jsp:include page="/WEB-INF/views/member/loginheader.jsp"/>
	<div class="find-menu">
		<div class="find-h">
			<h4><a href="findIDForm.do">아이디 찾기</a></h4>
			<h2><a href="findPWNoIDForm.do">비밀번호 찾기</a></h2>
		</div>
	</div>
	<hr size="1" noshade width="800">
	<div class="find-menu2">
		<span>비밀번호를 찾고자하는 아이디를 입력해주세요.</span>
	</div>
	<div class="findPW-main">
		<form id="findPWNoID_form" action="checkID.do" method="post" class="register-member">
			<ul>
				<li>
					<label for="id">아이디</label>
					<input type="text" name="id" id="id" maxlength="12" autocomplete="off">
				</li>
				
			</ul>
			<div class="findPWNoID-button">
				<input type="submit" value="다음">
			</div>
		</form>
		<hr size="1" noshade width="800">
	</div>
	</div>
</div>
</body>
</html>