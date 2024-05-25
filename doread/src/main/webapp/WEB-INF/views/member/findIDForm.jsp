<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 찾기</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/kbm.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>
<script type="text/javascript">
$(function(){
	$('#findID-result').hide();
	$('#findID_btn_pw').hide();
	
	$('#findID_form').submit(function(event){
		const name = document.getElementById('name');
		if(name.value.trim() == ''){
			alert('이름을 입력하세요');
			name.value == '';
			name.focus();
			return false;
		}
		const email = document.getElementById('email');
		if(email.value.trim() == ''){
			alert('이메일을 입력하세요');
			email.value == '';
			email.focus();
			return false;
		}
		if(!/^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/.test($('#email').val())){
			alert('이메일 양식에 맞게 입력하세요');
			email.focus();
			return false;
		}

		$.ajax({
			url:'findMemberID.do',
			type:'post',
			data:{name:$('#name').val(),email:$('#email').val()},
			dataType:'json',
			success:function(param){
				if(param.result == 'memberNotFound'){
					$('#result_msg1').css('color','red').text('입력하신 정보와 일치하는 회원이 없습니다.');
					$('#findID-result').show();
					$('#result_msg2').hide();
					$('#findID_btn_pw').hide();
				}else if(param.result == 'memberFound'){
					$('#result_msg1').css('color','black').text('고객님의 정보와 일치하는 아이디입니다');
					$('#result_msg2').css('color','rgb(31, 181, 68)').text(param.id);
					$('#result_msg2').show();
					$('#findID-result').show();
					$('#findID_btn_pw').show();
				}else{
					alert('아이디 찾기 오류 발생');
				}
			},
			error:function(){
				alert('네트워크 오류 발생');
			}
		});
		
		event.preventDefault();
		document.getElementById('name').value = name.value;
		document.getElementById('email').value = email.value;
		
	});
});
</script>
</head>
<body>
<div class="page-main">
	<jsp:include page="/WEB-INF/views/member/loginheader.jsp"/>
	<div class="findID-menu">
		<div class="findID-h">
			<h2><a href="location.href='findIDForm.do'">아이디 찾기</a></h2>
			<h3><a href="location.href='findPWForm.do'">비밀번호 찾기</a></h3>
		</div>
	</div>
	<hr size="1" noshade width="1000">
	<div class="findID-main">
		<form id="findID_form" action="findIDForm.do" method="post" class="register-member">
			<ul>
				<li>
					<label for="name">이름</label>
					<input type="text" name="name" id="name" autocomplete="off" maxlength="10" class="input-check">
				</li>
				<li>
					<label for="email">이메일</label>
					<input type="email" name="email" id="email" autocomplete="off" maxlength="50" class="input-check">
				</li>
			</ul>
			<div class="findID-button">
				<input type="submit" value="아이디 찾기">
			</div>
		</form>
		<hr size="1" noshade width="1000">
		<div id="findID-result">
			<span id="result_msg1"></span>
			<p>
			<span id="result_msg2"></span>
		</div>
		<div class="findID-btn">
			<input type="button" value="로그인" onclick="location.href='loginForm.do'">
			<input type="button" value="홈으로" onclick="${pageContext.request.contextPath}/main/main.do">
			<input type="button" value="비밀번호 찾기" id="findID_btn_pw" onclick="location.href='findPWForm.do'">
		</div>
	</div>
</div>
</body>
</html>