$(function(){
	$('#info_confirm').on('click',function(){
		if(!/^01(0|1|[6-9])[0-9]{3,4}[0-9]{4}$/.test($('#receive_phone').val())){
			alert('전화번호를 양식에 맞게 입력하세요(하이픈(-)제외)');
			$('#receive_phone').val('').focus();
		}else if(!/^[0-9]{5}$/.test($('#receive_zipcode').val())){
			alert('우편번호를 입력하세요(숫자5자리)');
			$('#receive_zipcode').val('').focus();
		}else if($('#receive_name').val().trim() == ""){
			alert('이름을 입력하세요');
			$('#receive_name').val('').focus();
		}else if($('#receive_address1').val().trim() == ""){
			alert('주소를 입력하세요');
			$('#receive_address1').val('').focus();
		}else if($('#receive_address2').val().trim() == ""){
			alert('상세주소를 입력하세요');
			$('#receive_address2').val('').focus();
		}else{
			return saveShippingInfo();
		}
	 });
	 
	$('#order_form').submit(function() {
		
		if ($('#select_msg').val() == '6' && $('#order_msg_textarea').val().trim() == '') {
			alert('배송요청사항을 입력하세요.');
			return false;
		}
		
		if ($('input[name="enter"]:checked').val() == '1' && $('input[name="enter_passwd"]').val().trim() == '') {
			alert('공동현관 비밀번호를 입력하세요.');
			return false;
		}
		
		if ($('#select_msg').val() == '6' && $('#order_msg_textarea').val().trim() == '') {
			alert('배송요청사항을 입력하세요.');
			return false;
		}
		const radio = document.querySelectorAll('input[class="payment"]:checked');
				
		if(radio.length<1){
			alert('결제수단을 선택하세요');
			return false;
		}	
		
		
		
		if ($('input[name="payment"]:checked').val() == '1' && $('input[name="pay_text"]').val().trim() == '') {
			alert('카드번호를 입력하세요.');
			$('input[name="pay_text"]').focus();
			return false;
		}
		
		if ($('input[name="payment"]:checked').val() == '3' && $('input[name="pay_text"]').val().trim() == '') {
			alert('휴대폰번호를 입력하세요.');
			$('input[name="pay_text"]').focus();
			return false;
		}
		
		if ($('input[name="payment"]:checked').val() == '3' && !/^01(0|1|[6-9])[0-9]{3,4}[0-9]{4}$/.test($('input[name="pay_text"]').val())) {
			alert('전화번호를 올바르게 입력하세요(하이픈(-)제외)');
			$('input[name="pay_text"]').focus();
			return false;
		}
		
		
		
		
		
	});
    
    $('#order_msg_textarea').addClass('hidden');
    $('#select_msg').on('change',function(){
		if (this.value == '6') {
             $('#order_msg_textarea').removeClass('hidden');
        } else {
             $('#order_msg_textarea').addClass('hidden');
        }
	});
   /* 
    document.getElementById('order_msg_textarea').disabled = true;
    document.getElementById('select_msg').addEventListener('change', function() {
        var textarea = document.getElementById('order_msg_textarea');
        
    });*/
	 
	 
});