<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header_test.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/kts.css" type="text/css">
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
});
</script>
</head>
<body>

	<div id="mini_nav">
	<ul>
		<c:if test="${empty user_num}">
		<li>
			<a href="${pageContext.request.contextPath}/member/registerUserForm.do" class="top-nav">회원가입</a>
		</li>
		<li>
			<a href="${pageContext.request.contextPath}/member/loginForm.do" class="top-nav" >로그인</a>
		</li>
		<li>
			<a href="${pageContext.request.contextPath}/member/mypage.do" class="top-nav" >마이페이지</a>
		</li>
		</c:if>
		
		<c:if test="${!empty user_num && user_auth != 9}">
		<li class="menu-logout">
			[<span>${user_id}</span>]
			<a href="${pageContext.request.contextPath}/member/logout.do" class="top-nav">로그아웃</a>
		</li>
		<li>
			<a href="${pageContext.request.contextPath}/member/mypage.do" class="top-nav" >마이페이지</a>
		</li>
		</c:if>
		
		<c:if test="${!empty user_num && user_auth == 9}">
		<li class="menu-logout">
			[<span>${user_id}</span>]
			<a href="${pageContext.request.contextPath}/member/logout.do" class="top-nav">로그아웃</a>
		</li>
		<li>
			<a href="${pageContext.request.contextPath}/adminster/adminPage.do" class="top-nav">관리자페이지</a>
		</li>
		</c:if>
		
	</ul>
	</div>
	<div id="main_logo">
	<ul>
		<li>
			<a href="${pageContext.request.contextPath}/main/main.do" class="top-nav" id="doread"><img src="../images/pngwing.com.png" width="40px">Do Read</a>

		</li>

		<li id="menu-cart">
			<img src="${pageContext.request.contextPath}/images/cart.png" class="click-image" width="30" height="30">
		</li>
		<li id="menu-mypage">
			<img src="${pageContext.request.contextPath}/images/mypage.png" class="click-image" width="30" height="30">
		</li>
		
	</ul>
	</div>
	

