<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/kbm.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>
<script type="text/javascript">

</script>
</head>
<body>
<div class="page-main">
	<div class="content-main">
		<h2>Do READ</h2>
		<form id="login_form" action="login.do" method="post">
			<ul>
				<li class="floating-label">
					<input type="text" class="form-input" placeholder="아이디" name="id" id="id" maxlength="12" autocomplete="off">
					<label for="id">아이디</label>
				</li>
				<li class="floating-label">
					<input type="password" class="form-input" placeholder="비밀번호" name="passwd" id="passwd" maxlength="12">
					<label for="passwd">비밀번호</label>
				</li>
			</ul>
			<div>
				<input type="submit" value="로그인">
			</div>	
		</form>
		<div>
			<input type="button" value="아이디 찾기" onclick="location.href='${pageContext.request.contextPath}/findId.do'">
			<input type="button" value="비밀번호 찾기" onclick="location.href='${pageContext.request.contextPath}/findPasswd.do'">
		</div>
		<div>
			<input type="button" value="회원가입" onclick="location.href='registerUserForm.do'">
			<input type="button" value="홈으로" onclick="location.href='${pageContext.request.contextPath}/main/main.do'">
		</div>
	</div>
	
</div>
</body>
</html>