$(document).ready(function(){

    $('#openModalBtn').click(function() {
        $('#myModal').css('display', 'block');
    });
    
    // 모달을 닫는 버튼 클릭 시
    $('.close').click(function() {
        $('#myModal').css('display', 'none');
    });
    
    // 모달 외부 클릭 이벤트 리스너 제거
    $(window).click(function(event) {
        if (event.target == $('#myModal')[0]) {
        }
    });
    
    
    function toggleEnterPasswd(show) {
    var enterPasswdInput = $('#re_enter_passwd');
    if (show) {
        enterPasswdInput.show();
    } else {
        enterPasswdInput.hide();
    }
}

// 페이지 로드 시 초기 상태 설정

    var isEnter = $('input[name="re_enter"]:checked').val();
    toggleEnterPasswd(isEnter == 1);

    // 라디오 버튼 클릭 시 상태 변경
    $('input[name="re_enter"]').on('change', function() {
        toggleEnterPasswd($(this).val() == 1);
    });

    
    // 폼 제출 처리
    $('#editForm').submit(function(event) {
        event.preventDefault();
        
        if(!/^01(0|1|[6-9])[0-9]{3,4}[0-9]{4}$/.test($('#re_phone').val())){
			alert('전화번호를 양식에 맞게 입력하세요(하이픈(_)제외)');
			$('#re_phone').val('').focus();
		}else if(!/^[0-9]{5}$/.test($('#zipcode').val())){
			alert('우편번호를 입력하세요(숫자5자리)');
			$('#zipcode').val('').focus();
		}else if($('#re_name').val().trim() == ""){
			alert('이름을 입력하세요');
			$('#re_name').val('').focus();
		}else if($('#address1').val().trim() == ""){
			alert('주소를 입력하세요');
			$('#address1').val('').focus();
		}else if($('#address2').val().trim() == ""){
			alert('상세주소를 입력하세요');
			$('#address2').val('').focus();
		}else if($('input[name="re_enter"]:checked').val() == '1' && $('input[name="re_enter_passwd"]').val().trim() == '') {
            alert('공동현관 비밀번호를 입력하세요.');
            $('#enter_passwd').focus();
        }else{
        
	        $.ajax({
	            url:'modifyOrderInfo.do',
	            method:'post',
	            data:{order_num:$('#order_num').val(),
		            receive_name:$('#re_name').val(), 
		            receive_phone:$('#re_phone').val(), 
		            receive_zipcode:$('#zipcode').val(), 
		            receive_address1:$('#address1').val(),
		            receive_address2:$('#address2').val(), 
		            enter:$('input[name="re_enter"]:checked').val(), 
		            enter_passwd:$('#re_enter_passwd').val()},
	            dataType:'json',
	            success: function(param) {
	                if (param.result == 'success') {
						
						location.href="orderDetail.do?order_num="+param.order_num;
	                    alert('배송지정보가 수정되었습니다.');
	                    $('#myModal').css('display', 'none');
	                }else if(param.result == 'logout'){
						alert('로그인 후 사용하세요');
						location.href='loginForm.do';
	                }else{
						alert('배송지정보 수정 오류 발생');
					}
	            },
	            error: function() {
	                alert('네트워크 오류');
	            }
	        });
		}
    });
});