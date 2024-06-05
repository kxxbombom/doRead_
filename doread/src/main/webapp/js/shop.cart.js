$(function(){
	
	/*	장바구니 수량 변경 */
	
	$('.cart-modify').on('click',function(){
		const input_quantity = $(this).parent().find('input[name="c_quantity"]');
		if(input_quantity.val() == ''){
			alert('수량을 입력하세요');
			input_quantity.focus();
			return;
		}
		if(input_quantity.val() < 1){
			alert('상품의 최소 수량은 1입니다.');
			input_quantity.val(input_quantity.attr('value'));
			return;
		}

		$.ajax({
			url:'modifyCart.do',
			type:'post',
			data:{cart_num:$(this).attr('data-cartnum'),book_num:$(this).attr('data-booknum'),c_quantity:input_quantity.val(),total:$(this).attr('data-total')},
			dataType:'json',
			success:function(param){
				if(param.result == 'logout'){
					alert('로그인 후 사용하세요');
					location.href="loginForm.do"
				}else if(param.result == 'overQuantity'){
					alert('상품의 수량이 부족합니다.');
					location.href='list.do';
				}else if(param.result == 'success'){
					location.href="list.do";
				}else{
					alert('장바구니 상품 수량 수정 오류');
				}
			},
			error:function(){
				alert('네트워크 오류');
			}
		});
	});
	
	
	/*	장바구니 상품 삭제*/
	
	$('.cart-del').on('click',function(){
		let check = confirm('선택한 도서를 장바구니에서 삭제하시겠습니까?');
		if(check){

			$.ajax({
				url:'deleteCart.do',
				type:'post',
				data:{cart_num:$(this).attr('data-cartnum')},
				dataType:'json',
				success:function(param){
					if(param.result == 'logout'){
						alert('로그인 후 사용하세요');
					}else if(param.result == 'success'){
						location.href='list.do';
					}else{
						alert('장바구니 상품 삭제 오류');
					}
				},
				error:function(){
					alert('네트워크 오류');
				}
			});
		}
	});
	
});