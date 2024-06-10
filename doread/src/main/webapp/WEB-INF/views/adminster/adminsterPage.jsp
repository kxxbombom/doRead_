<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자페이지</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/kbm.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header_test.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/kyj.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>
<script type="text/javascript">
	window.onload = function(){
		const inputcheck = document.getElementsByClassName('inputcheck');
		const form = document.getElementById('bookcreate_form');
		
       
		form.onsubmit = function(){
			
			for(let i=0; i<inputcheck.length; i++){
			const label = document.querySelector('label[for="'+inputcheck[i].id+'"]');
			
			if(inputcheck[i].value.trim()==''){
				alert(label.textContent+' 필수 입력');
				inputcheck[i].value='';
				inputcheck[i].focus();
				return false;
				}
			
			if(inputcheck[i].id=='publish_date'){
				if(!/^[2-9][0-9]{3}[-][0-9]{2}[-][0-9]{2}$/.test(inputcheck[i].value)){
					alert('출판일은 0000-00-00 형식으로 입력하세요');
					inputcheck[i].focus();
					return false;
				}	
				
			}
		}//end of for
		
	};
};
$(function(){
	$('.photo_btn').click(function(event){	
		
		$('#photo_choice').show();
		
	
	});
	
	//이미지 미리 보기
	let photo_path = $('.my-photo').attr('src');//처음 화면에 보여지는 이미지 읽기
	$('#photo').change(function(){
		
		let my_photo = this.files[0];
		if(!my_photo){
			//선택을 취소하면 원래 처음 화면으로 되돌림
			$('.my-photo').attr('src',photo_path);
			return;
		}
		
		if(my_photo.size > 1024*1024){
			alert(Math.round(my_photo.size/1024) 
					 + 'kbytes(1024kbytes까지만 업로드 가능)');
			$('.my-photo').attr('src',photo_path);
			$(this).val('');//선택한 파일 정보 지우기
			return;
		}
		//화면에 이미지 미리 보기
		const reader = new FileReader();
		reader.readAsDataURL(my_photo);
		
		reader.onload=function(){
			$('.my-photo').attr('src',reader.result);
		};		
	});//end of change
	
	//이미지 전송
	$('#photo_submit').click(function(){
		if($('#photo').val()==''){
			alert('파일을 선택하세요');
			$('#photo').focus();
			return;
		}
	
		//파일 전송
		const form_data = new FormData();
		//업로드할 파일은 $('#photo').files[0]를 호출할 수 없음
		//$('#photo')[0].files[0] 또는
		//document.getElementById('photo').files[0]
		//형식으로 호출 가능
		form_data.append('photo',$('#photo')[0].files[0]);
		
		$.ajax({
			url:'${pageContext.request.contextPath}/member/updateMyPhoto.do',
			type:'post',
			data:form_data,
			dataType:'json',
			contentType:false, //데이터 객체를 문자열로 바꿀지 설정.true이면 일반문자
			processData:false, //해당 타입을 true로 하면 일반 text로 구분
			success:function(param){
				if(param.result == 'logout'){
					alert('로그인 후 사용하세요');
				}else if(param.result == 'success'){
					alert('프로필 사진이 수정되었습니다.');
					//수정된 이미지 정보 저장
					photo_path = $('#my').attr('src');
					$('#photo').val('');
					$('#photo_choice').hide();
					$('.photo_btn').show();//수정 버튼 표시
					
				}else{
					alert('파일 전송 오류 발생');
				}
			},
			error:function(){
				alert('네트워크 오류 발생');
			}
		});
		
	});//end of click
	
	//이미지 미리보기 취소
	$('#photo_reset').click(function(){
		//초기 이미지 표시
		$('.my-photo').attr('src',photo_path);//이미지 미리보기 전 이미지로 되돌리기
		$('#photo').val('');
		$('#photo_choice').hide();
		
	});
})
</script>
</head>
<body>
<jsp:include page="/WEB-INF/views/adminster/adminheader3.jsp"/>
<br><br>
<jsp:include page="/WEB-INF/views/adminster/adminheader.jsp"/>
<div class="page-main">

	<h2 class="vertical-margin">책 등록</h2>
	
	<hr size="1.5" width="80%" noshade="noshade">
	<form action="bookcreate.do" method="post" id="bookcreate_form" enctype="multipart/form-data" >
	<div class="content-main">
	<ul>
		<li><label for="book_auth">판매여부</label>
			<select name="book_auth" id="book_auth" class="inputcheck input-style">
				<option value="0">판매</option>
				<option value="1">미판매</option>
			</select>
		</li>
		<li><label for="book_name">책 이름</label>
			<input type="text" name="book_name" class="inputcheck input-style" id="book_name" maxlength="30" >
		</li>
		<li><label for="author">책 저자</label>
			<input type="text" name="author" class="inputcheck input-style "  id="author" maxlength="10">
		</li>
		<li><label for="publisher">출판사</label>
			<input type="text" name="publisher" class="inputcheck input-style " id="publisher" maxlength="10">
		</li>
		<li><label for="publish_date">출판일</label>
			<input type="text" name="publish_date" class="inputcheck input-style " id="publish_date" maxlength="10">
		</li>
		<li><label for="price">가격</label>
			<input type="number" name="price" class="inputcheck input-style " id="price" maxlength="8">
		</li>
		<li><label for="stock">재고</label>
			<input type="number" name="stock" class="inputcheck input-style" id="stock" maxlength="8">
		</li>
		<li><label for="book_category">책카테고리</label>
			<select name="book_category" id="book_category" class="inputcheck input-style">
				<option value="1">소설</option>
				<option value="2">시/에세이</option>
				<option value="3">인문</option>
				<option value="4">정치/사회</option>
				<option value="5">과학/공학</option>
				<option value="6">IT/프로그래밍</option>
				<option value="7">자기계발</option>
				<option value="8">외국어</option>
				<option value="9">수험서/취업</option>
				<option value="10">만화</option>
				<option value="11">취미/스포츠</option>
			</select>
		</li>
		<li>
			<label for="book_img">책 이미지</label>
			<input type="file" id="book_img" name="book_img" class="inputcheck input-style " accept="image/gif,image/png,image/jpeg">
			
		</li>
	</ul>
	</div>
	<div class="align-center">
		<input type="submit" value="등록" class="update-button">
		<input type="button" value="메인" class="update-button" onclick="location.href='${pageContext.request.contextPath}/main/main.do'">
	</div>
	
</form>
</div>
</body>
</html>