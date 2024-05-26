<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 재설정</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/kbm.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>
<script type="text/javascript">
$(function(){
	var ajaxData = ${ajaxData};
	
	if(ajaxData.result === 'wrongInfo') {
		alert('입력한 정보가 올바르지 않습니다.');
		history.go(-1);
	}else if (ajaxData.result === 'success') {
		alert('비밀번호가 변경되었습니다.');
		location.href='${pageContext.request.contextPath}/member/loginForm.do';
	}
});
</script>
</head>
<body>

</body>
</html>