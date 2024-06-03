<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>my page</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/kts.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header_test.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/kyj.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>
<script type="text/javascript">
$(function(){
	$('#photo_btn').click(function(){
		$('#photo_choice').show();
		$(this).hide(); //수정 버튼 감추기
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
			url:'updateMyPhoto.do',
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
					photo_path = $('.my-photo').attr('src');
					$('#photo').val('');
					$('#photo_choice').hide();
					$('#photo_btn').show();//수정 버튼 표시
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
		$('#photo_btn').show();//수정 버튼 표시
	});
	
	//추천도서 슬라이더

	setInterval(function() {
	  $('.slider-space > div:first')
	    .next()
	    .end()
	    .appendTo('.slider-space');
	},  5000);

});
</script>
</head>
<body>
<div class="page-main">
		<jsp:include page="/WEB-INF/views/news/test_header.jsp"/>
		<jsp:include page="/WEB-INF/views/member/mypageheader.jsp"/>
		<h2>${user_id}님을 위한 추천도서</h2>
		<hr width="100%" size="1" noshade="noshade">
		<div class="slider-container">
			<c:if test="${count==0}">
				<div class="slider-items">
					<h3>선호 도서 카테고리가 없어 추천도서를 표시할 수 없습니다</h3>
				</div>
			</c:if>
		<div class="slider-space">
			<c:if test="${count!=0}">
				<c:forEach var="recommend" items="${recommend}">
					<div class="slider-items" onclick="location.href='${pageContext.request.contextPath}/book/detail.do?book_num=${recommend.book_num}'">
						<div class="slider-image"><img src="${pageContext.request.contextPath}/upload/${reccomend.book_img}" height="300"></div>
						<div class="slider-image">
							<ul>
								<li><h2>${recommend.book_name}</h2></li>
								<li>${recommend.author}</li>
								<li>${recommend.publisher}</li>
							</ul>		
						</div>
					</div>
				</c:forEach>
			</c:if>
		</div>
			<hr width="100%" size="1" noshade="noshade">
		</div>
				<div class="mypage-button">
					<input type="button" value="찜" onclick="location.href='${like}'">
					<input type="button" value="추천" onclick="location.href='${best}'">
				</div>
</div>
</body>
</html>


