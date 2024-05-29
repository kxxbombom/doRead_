$(function(){
	/*============
	 *	주문수량 변경		book 상세 페이지. 변수 통일
	 *============*/
	$('#c_quantity').on('keyup mouseup',function(){
		if($('#c_quantity').val() == ''){
			$('#book_total_txt').text('총 주문금액 : 0원');
			return;
		}
		if($('#c_quantity').val() <= 0){
			$('#c_quantity').val('');
			return;
		}
		if(Number($('#book_quantity').val()) < $('#c_quantity').val()){
			alert('수량이 부족합니다!');
			$('#c_quantity').val('');
			$('#book_total_txt').text('총 주문금액 : 0원');
			return;
		}
		let total = $('#book_price').val() * $('#c_quantity').val();
		$('#book_total_txt').text('총 주문금액 : ' + total.toLocaleString() + '원');
	});
	/*============
	 *	장바구니 추가
	 *============*/
	$('#book_cart').submit(function(event){
		if($('#c_quantity').val() == ''){
			alert('수량을 입력하세요!');
			$('#c_quantity').focus();
			return false;
		}
		
		let form_data = $(this).serialize(); /*쿼리스트링 형태로 읽어옴*/
		
		//서버와 통신
		$.ajax({
			url:'../cart/write.do',  /*///////////////////////*/
			type:'post',
			data:form_data,
			dataType:'json',
			success:function(param){
				if(param.result == 'logout'){
					alert('로그인 후 사용하세요');
				}else if(param.result == 'success'){
					alert('장바구니에 상품을 담았습니다.');
					location.href='../cart/list.do';  /*나중에*/
				}else if(param.result == 'overquantity'){ /*나중에*/
					alert('기존에 주문한 상품입니다. 개수를 추가하면 재고가 부족합니다.');
				}else{
					alert('장바구니 담기 오류');
				}
			},
			error:function(){
				alert('네트워크 오류 발생');
			}
		});
		
		//기본 이벤트 제거
		event.preventDefault();
	});
});