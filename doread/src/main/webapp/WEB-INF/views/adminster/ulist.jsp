<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자페이지</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/ssh.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/ysw.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>
</head>
<body>

<jsp:include page="/WEB-INF/views/adminster/adheader2.jsp"/>
<jsp:include page="/WEB-INF/views/adminster/adminheader.jsp"/>
<div class="page-main">
<h2>USED 보드 신고된 게시글 관리</h2>
	<c:if test="${empty list}">
	 신고등록된 게시글이 없습니다.
	</c:if>
	<c:if test="${!empty list}">
	 <table>
	 	<tr>
	 	<th>글번호</th>
	 	<th>신고한회원번호</th>
	 	<th>신고카테고리</th>
	 	<th>신고내용</th>
	 	<th>현재등급</th>
	 	<th>등급변경<th>
	 	</tr>
	 	<c:forEach var="i" items="${list}">
	 		<c:if test="${i.auth == 0}">
	 		<tr>
	 			<td><a href="${pageContext.request.contextPath}/used/usedDetail.do?u_num=${i.u_num}">${i.u_num}</a></td>
	 			<td><a href="${pageContext.request.contextPath}/adminster/detailUser.do?mem_num=${i.mem_num}">${i.mem_num}</a></td>
	 			<td><c:if test="${i.ur_category ==1 }">스팸홍보글/도배글</c:if>
	 				<c:if test="${i.ur_category ==2 }">음란물</c:if>
	 				<c:if test="${i.ur_category ==3 }">불법정보</c:if>
	 				<c:if test="${i.ur_category ==4 }">청소년에게 유해한내용</c:if>
	 				<c:if test="${i.ur_category ==5 }">욕설/생명경시/혐오/차별적표현</c:if>
	 				<c:if test="${i.ur_category ==6 }">개인정보노출</c:if>
	 				<c:if test="${i.ur_category ==7 }">불쾌한 표현이 있습니다.</c:if>
	 				
	 			</td>
	 			<td>
	 				${i.ur_content}
	 			</td>
	 			<td>
	 				<c:if test="${i.auth ==0 }">일반</c:if>
	 				
	 				<c:if test="${i.auth ==2 }">정지</c:if>
	 			</td>
	 			<td>
	 			<input type="button" value="변경" class="upbtn button2" data-id="${i.u_num}" data-name="u_num" data-auth="${i.auth}">
	 			</td>
	 		</tr>
	 		</c:if>
	 	</c:forEach>
	 </table>
	</c:if>
	
	<div class="align-center">
	<input type="submit" value="등록" class="button2">
	<input type="button" value="메인" class="button2" onclick="location.href='${pageContext.request.contextPath}/main/main.do'">
	</div>
	<div class="hide authupdate align-center" 
	style="width:400px; position:fixed;
z-index:999 !important;
bottom:400px;
border:1px solid #999 !important;
border-radius:15px;
padding:8px;
background:white;" >
		<h2>등급변경</h2>
		현재 게시글/댓글 등급:
		<input type="radio" name="radio"  value="0" >일반
	 	<input type="radio"  name="radio"  value="2" >정지
	 		<br> <input type="button" value="변경"  class="button2 rebtn" id="update">	
	 			<input type="button" value="취소"  class="button2 redelbtn" id="update">	
	 	
	
	</div>
<script type="text/javascript">
$(document).on('click', '.upbtn',function(){
	if($('.authupdate').hasClass('hide')){
        $('.authupdate').removeClass('hide');
         $('.rebtn').attr('data-id',$(this).attr('data-id'));
        $('.rebtn').attr('data-name',$(this).attr('data-name'));
        $('input[value="'+$(this).attr('data-auth')+'"]').attr('checked','true');
     
    }else{
        $('.authupdate').addClass('hide');
       $('.rebtn').attr('data-id','');
         $('.rebtn').attr('data-name','');
         
         $('input[type="radio"]:checked').attr('checked','false');
	    }
	
});
$(document).on('click', '.redelbtn',function(){
	
        $('.authupdate').addClass('hide');
        $('.rebtn').attr('data-id','');
        $('.rebtn').attr('data-name','');
       
        $('input[type="radio"]:checked').attr('checked','false');
	    
	
});
$('.rebtn').click(function(){
	if( $('input[type="radio"]:checked').length <1){
		alert('등급을 설정해주세요');
		return;
	}
	
	$.ajax({
		url:'updateAuth2.do',
		data:{num:$(this).attr('data-id'),name:$(this).attr('data-name'),auth:$('input[type="radio"]:checked').val()},
		type:'post',
		dataType:'json',
		success:function(param){
			if(param.result=='logout'){
				alert('관리자 아이디로 로그인 후 사용해주세요');
				
			}else if(param.result=='success'){
				alert('등급이 변경되었습니다.');
				
				$('.authupdate').addClass('hide');
				location.href="ulist.do";
			}else{
				alert('신고 접수 에러');
			}
		},
		error:function(){
			alert('네트워크 오류');
		}
	})
	
	
})


</script>

</div>
</body>
</html>