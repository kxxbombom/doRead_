$(function(){
	
	//찜목록 등록
	$('#wish').click(function(){
		$.ajax({
			url:'bookfav.do',
			type:'post',
			data:{book_num:$(this).attr('data-num')},
			dataType:'json',
			success:function(param){
				if(param.result == "logout"){
					alert('로그인 후 찜목록에 추가할 수 있습니다');
				}else if(param.result == 'success'){
					displayFav(param);
				}else{
					alert('찜목록 갱신 중 오류가 발생했습니다');
				}
			},
			error:function(){
				alert('네트워크 오류');
			}
		});
	});
	
	//찜 표시
	function displayFav(param){
		let book_fav;
		if(param.status == 'yesFav'){
			book_fav = '../images/bookfav_after.png';
		}else{
			book_fav='..images/bookfav_before.png';
		}
		$('#bookfav').attr('src',book_fav);
		
	}
	
});