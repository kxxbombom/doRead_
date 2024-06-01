<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/kbm.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header_test.css" type="text/css">

<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>
<script type="text/javascript">
$(function(){
	let idChecked = 0;
	let pwChecked = 0;
	let emailChecked = 0;
	let phoneChecked = 0;

	$('#passwd_checkmessage').hide();
	
	$('#id_check').click(function(){
		if($('#id').val().length == 0){
			alert('아이디를 입력하세요');
			$('#id').val('').focus();
			return;
		}
		if($('#id').val().length < 4){
			alert('아이디는 4~12글자이어야 합니다');
			$('#id').focus();
			return;
		}
		if(!/^[A-Za-z0-9]{4,12}$/.test($('#id').val())){
			alert('영문 또는 숫자만 가능합니다');
			$('#id').val('').focus();
			return;
		}
		
		$.ajax({
			url:'checkDuplicatedId.do',
			type:'post',
			data:{id:$('#id').val()},
			dataType:'json',
			success:function(param){
				if(param.result == 'idNotFound'){
					idChecked = 1;
					$('#id_checkmessage').css('color','black').text('사용 가능 아이디');
				}else if(param.result == 'idDuplicated'){
					idChecked = 0;
					$('#id_checkmessage').css('color','red').text('중복된 아이디');
					$('#id').focus();
				}else{
					idChecked = 0;
					alert('아이디 중복 체크 오류 발생');
				}
			},
			error:function(){
				alert('네트워크 오류 발생');
				idChecked = 0;
			}
		});
	});
	
	
	$('#email_check').click(function(){
		if($('#email').val().length == 0){
			alert('이메일을 입력하세요');
			$('#email').val('').focus();
			return;
		}
		if(!/^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/.test($('#email').val())){
			alert('이메일을 양식에 맞게 입력하세요');
			$('#email').focus();
			return;
		}
		
		$.ajax({
			url:'checkDuplicatedEmail.do',
			type:'post',
			data:{email:$('#email').val()},
			dataType:'json',
			success:function(param){
				if(param.result == 'emailNotFound'){
					idChecked = 1;
					alert('사용 가능한 이메일입니다.');
				}else if(param.result == 'emailDuplicated'){
					emailChecked = 0;
					alert('이미 사용 중인 이메일입니다.');
					$('#email').focus();
				}else{
					emailChecked = 0;
					alert('이메일 중복 체크 오류 발생');
				}
			},
			error:function(){
				alert('네트워크 오류 발생');
				emailChecked = 0;
			}
		});
	});
	
	$('#phone_check').click(function(){
		if($('#phone').val().length == 0){
			alert('전화번호를 입력하세요');
			$('#phone').val('').focus();
			return;
		}
		if(!/^01(0|1|[6-9])[0-9]{3,4}[0-9]{4}$/.test($('#phone').val())){
			alert('전화번호를 양식에 맞게 입력하세요');
			$('#email').focus();
			return;
		}
		
		$.ajax({
			url:'checkDuplicatedPhone.do',
			type:'post',
			data:{phone:$('#phone').val()},
			dataType:'json',
			success:function(param){
				if(param.result == 'phoneNotFound'){
					idChecked = 1;
					alert('사용 가능한 전화번호입니다.');
				}else if(param.result == 'phoneDuplicated'){
					emailChecked = 0;
					alert('이미 사용 중인 전화번호입니다.');
					$('#phone').focus();
				}else{
					emailChecked = 0;
					alert('전화번호 중복 체크 오류 발생');
				}
			},
			error:function(){
				alert('네트워크 오류 발생');
				emailChecked = 0;
			}
		});
	});
	
	$('#register_form #id').keydown(function(){
		idChecked = 0;
		$('#id_checkmessage').text('');
	});
	
	$('#register_form #cpasswd').keydown(function(){
		pwChecked = 0;
		$('#passwd_checkmessage').hide();
	});
	$('#register_form #cpasswd').keyup(function(){
		if($('#passwd').val() == $('#cpasswd').val()){
			pwChecked = 1;
			$('#passwd_checkmessage').show();
		}
	});
	$('#register_form #email').keydown(function(){
		emailChecked = 0;
	});
	$('#register_form #phone').keydown(function(){
		phoneChecked = 0;
	});
	
	$('#register_form').submit(function(){
		const items = document.querySelectorAll('.input-check');
		for(let i=0;i<items.length;i++){
			if(items[i].value.trim() == ''){
				const label = document.querySelector('label[for="' +items[i].id+ '"]');
				alert(label.textContent + ' 필수 입력')
				items[i].value == '';
				items[i].focus();
				return false;
			}
			if(items[i].id == 'id' && !/^[A-Za-z0-9]{4,12}$/.test($('#id').val())){
				alert('영문 또는 숫자 4~12자 사용');
				$('#id').focus();
				return false;
			}
			if(items[i].id == 'id' && idChecked == 0){
				alert('아이디 중복 체크 필수');
				return false;
			}
			if(items[i].id == 'cpasswd' && pwChecked == 0){
				alert('비밀번호를 다시 입력하세요');
				$('#cpasswd').focus();
				return false;
			}
			if(items[i].id == 'phone' && !/^01(0|1|[6-9])[0-9]{3,4}[0-9]{4}$/.test($('#phone').val())){
				alert('전화번호를 양식에 맞게 입력하세요');
				$('#id').focus();
				return false;
			}
			if(items[i].id == 'phone' && phoneChecked == 0){
				alert('전화번호 중복 체크 필수');
				return false;
			}
			if(items[i].id == 'email' && !/^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/.test($('#email').val())){
				alert('이메일을 양식에 맞게 입력하세요');
				$('#id').focus();
				return false;
			}
			if(items[i].id == 'email' && emailChecked == 0){
				alert('이메일 중복 체크 필수');
				return false;
			}
			if(items[i].id == 'zipcode' && !/^[0-9]{5}$/.test($('#zipcode').val())){
				alert('우편번호를 입력하세요(숫자5자리)');
				$('#zipcode').val('').focus();
				return false;
			}
		}
	});
});
</script>
</head>
<body>
<div class="page-main">
	<div class="content-main register-header">
		<jsp:include page="/WEB-INF/views/member/loginheader.jsp"/>
		<h2>회원가입</h2>
		<div class="register-top">
		
   			 <div class="background-box">
        		<span>일반 개인회원</span>
  	 		 </div>
		</div>
	</div>
	<%-- <hr size="1" noshade width="650"> --%>
	<div class="register-main">
		<span>기본 정보 입력</span>
		<form id="register_form" action="registerUser.do" method="post" class="register-member">
			<ul>
				<li>
					<label for="name">이름</label>
					<input type="text" name="name" id="name" maxlength="10" autocomplete="off" class="input-check">
				</li>
				<li>
					<label for="id">아이디</label>
					<input type="text" name="id" id="id" maxlength="12" autocomplete="off" class="input-check">
					<input type="button" value="ID중복체크" id="id_check">
					<span id="id_checkmessage"></span>
					<div class="form-notice1">*영문 또는 숫자(4~12자)</div>
				</li>
				<li>
					<label for="passwd">비밀번호</label>
					<input type="password" name="passwd" id="passwd" maxlength="12" class="input-check">
				</li>
				<li>
					<label for="cpasswd">비밀번호 확인</label>
					<input type="password" name="cpasswd" id="cpasswd" maxlength="12" class="input-check">
					<span id="passwd_checkmessage">비밀번호 일치</span>
				</li>
				<li>
					<label for="email">이메일</label>
					<input type="email" name="email" id="email" maxlength="50" placeholder="example@example.com" class="input-check">
					<input type="button" value="이메일 중복체크" id="email_check">
				</li>
				<li>
					<label for="phone">전화번호</label>
					<input type="text" name="phone" id="phone" maxlength="15" autocomplete="off" class="input-check">
					<input type="button" value="전화번호 중복체크" id="phone_check">
					<div class="form-notice2">*숫자만 입력</div>
					
				</li>
				<li>
					<label for="zipcode">우편번호</label>
					<input type="text" name="zipcode" id="zipcode" maxlength="5" autocomplete="off" class="input-check">
					<input type="button" value="우편번호 찾기" onclick="execDaumPostcode()">
				</li>
				<li>
					<label for="address1">주소</label>
					<input type="text" name="address1" id="address1" maxlength="50" class="input-check">
				</li>
				<li>
					<label for="address2">상세 주소</label>
					<input type="text" name="address2" id="address2" maxlength="50" class="input-check">
				</li>
				<li>
					<div class="container">
					<label for="preference">관심도서</label>
					</div>
					<div class="container">
					<div class="preference-box">
						<div class="preference-group">
							<div class="test">
							<input type="radio" name="preference" value="1" id="preference1">
							<label for="preference1">소설</label>
							</div>
							<div class="test">
							<input type="radio" name="preference" value="2" id="preference2">
							<label for="preference2">시/에세이</label>
							</div>
							<div class="test">
							<input type="radio" name="preference" value="3" id="preference3">
							<label for="preference3">인문</label>
							</div>
							<div class="test">
							<input type="radio" name="preference" value="4" id="preference4">
							<label for="preference4">정치/사회</label>
							</div>
						</div>
						<div class="preference-group">
							<div class="test">
							<input type="radio" name="preference" value="5" id="preference5">
							<label for="preference5">과학/공학</label>
							</div>
							<div class="test">
							<input type="radio" name="preference" value="6" id="preference6">
							<label for="preference6">IT/프로그래밍</label>
							</div>
							<div class="test">
							<input type="radio" name="preference" value="7" id="preference7">
							<label for="preference7">자기계발</label>
							</div>
							<div class="test">
							<input type="radio" name="preference" value="8" id="preference8">
							<label for="preference8">외국어</label>
							</div>
						</div>
						<div class="preference-group">
							<div class="test">
							<input type="radio" name="preference" value="9" id="preference9">
							<label for="preference9">수험서/취업</label>
							</div>
							<div class="test">
							<input type="radio" name="preference" value="10" id="preference10">
							<label for="preference10">만화</label>
							</div>
							<div class="test">
							<input type="radio" name="preference" value="11" id="preference11">
							<label for="preference11">취미/스포츠</label>
							</div>
						</div>
					</div>
					</div>
				</li>
			</ul>
			<hr size="1" noshade width="153%">
			<div class="register-button">
				<input type="submit" value="회원가입">
				<input type="button" value="홈으로" onclick="location.href='${pageContext.request.contextPath}/main/main.do'">
			</div>
			
		<div id="layer" style="display:none;position:fixed;overflow:hidden;z-index:1;-webkit-overflow-scrolling:touch;">
		<img src="//t1.daumcdn.net/postcode/resource/images/close.png" id="btnCloseLayer" style="cursor:pointer;position:absolute;right:-3px;top:-3px;z-index:1" onclick="closeDaumPostcode()" alt="닫기 버튼">
		</div>
		
		<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
		<script>
		    // 우편번호 찾기 화면을 넣을 element
		    var element_layer = document.getElementById('layer');
		
		    function closeDaumPostcode() {
		        // iframe을 넣은 element를 안보이게 한다.
		        element_layer.style.display = 'none';
		    }
		
		    function execDaumPostcode() {
		        new daum.Postcode({
		            oncomplete: function(data) {
		                // 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
		
		                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
		                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
		                var addr = ''; // 주소 변수
		                var extraAddr = ''; // 참고항목 변수
		
		                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
		                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
		                    addr = data.roadAddress;
		                } else { // 사용자가 지번 주소를 선택했을 경우(J)
		                    addr = data.jibunAddress;
		                }
		
		                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
		                if(data.userSelectedType === 'R'){
		                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
		                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
		                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
		                        extraAddr += data.bname;
		                    }
		                    // 건물명이 있고, 공동주택일 경우 추가한다.
		                    if(data.buildingName !== '' && data.apartment === 'Y'){
		                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
		                    }
		                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
		                    if(extraAddr !== ''){
		                        extraAddr = ' (' + extraAddr + ')';
		                    }
		                    //(주의)address1에 참고항목이 보여지도록 수정
		                    // 조합된 참고항목을 해당 필드에 넣는다.
		                    //(수정) document.getElementById("address2").value = extraAddr;
		                
		                } 
		                //(수정) else {
		                //(수정)    document.getElementById("address2").value = '';
		                //(수정) }
		
		                // 우편번호와 주소 정보를 해당 필드에 넣는다.
		                document.getElementById('zipcode').value = data.zonecode;
		                //(수정) + extraAddr를 추가해서 address1에 참고항목이 보여지도록 수정
		                document.getElementById("address1").value = addr + extraAddr;
		                // 커서를 상세주소 필드로 이동한다.
		                document.getElementById("address2").focus();
		
		                // iframe을 넣은 element를 안보이게 한다.
		                // (autoClose:false 기능을 이용한다면, 아래 코드를 제거해야 화면에서 사라지지 않는다.)
		                element_layer.style.display = 'none';
		            },
		            width : '100%',
		            height : '100%',
		            maxSuggestItems : 5
		        }).embed(element_layer);
		
		        // iframe을 넣은 element를 보이게 한다.
		        element_layer.style.display = 'block';
		
		        // iframe을 넣은 element의 위치를 화면의 가운데로 이동시킨다.
		        initLayerPosition();
		    }
		
		    // 브라우저의 크기 변경에 따라 레이어를 가운데로 이동시키고자 하실때에는
		    // resize이벤트나, orientationchange이벤트를 이용하여 값이 변경될때마다 아래 함수를 실행 시켜 주시거나,
		    // 직접 element_layer의 top,left값을 수정해 주시면 됩니다.
		    function initLayerPosition(){
		        var width = 300; //우편번호서비스가 들어갈 element의 width
		        var height = 400; //우편번호서비스가 들어갈 element의 height
		        var borderWidth = 5; //샘플에서 사용하는 border의 두께
		
		        // 위에서 선언한 값들을 실제 element에 넣는다.
		        element_layer.style.width = width + 'px';
		        element_layer.style.height = height + 'px';
		        element_layer.style.border = borderWidth + 'px solid';
		        // 실행되는 순간의 화면 너비와 높이 값을 가져와서 중앙에 뜰 수 있도록 위치를 계산한다.
		        element_layer.style.left = (((window.innerWidth || document.documentElement.clientWidth) - width)/2 - borderWidth) + 'px';
		        element_layer.style.top = (((window.innerHeight || document.documentElement.clientHeight) - height)/2 - borderWidth) + 'px';
		    }
		</script>
		</form>
	</div>
</div>
</body>
</html>