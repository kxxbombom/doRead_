<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원관리상세페이지</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/ssh.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/ysw.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>
<script type="text/javascript">
	$(function(){
		$('#modifyauth').click(function(){
			if(${member.mem_auth }==9)
				alert('관리자 회원 등급은 수정 불가능합니다.');
				
			if(${member.mem_auth }!=9)
				$('#auth_update').show();
				
		})
		$('#delbtn').click(function(){
			$('#auth_update').hide();
			
		})
		$('#authbtn').click(function(){
			if($('#auth').val().trim() =='' || !/^[0129]$/.test($('#auth').val())) {
				alert('등급 숫자만 입력가능합니다.');
				return;
			}
			$.ajax({
				url:'updateAuth.do',
				data:{auth:$('#auth').val(),mem_num:$('#mem_num').val()},
				type:'post',
				dataType:'json',
				success:function(param){
					if(param.result=='success'){
						$('#auth_update').hide();
						alert('등급 변경 되었습니다.');
						$('#authspan').empty();
						$('#authspan').text(param.auth);
					}else if(param.result=='logout'){
						alert('로그인 후 수정가능');
						
					}else if(param.result=='notadmin'){
						alert('관리자만 수정가능합니다.');
						
					}else{
						alert('등급 수정 실패');
						
					}
				},error:function(){
					alert('네트워크오류발생');
				}
				
				
			})
			
		})
	});
</script>
</head>
<body>
<jsp:include page="/WEB-INF/views/adminster/adminheader3.jsp"/>
<br><br>
<jsp:include page="/WEB-INF/views/adminster/adminheader.jsp"/>
<div class="page-main">

	<h2>회원 관리 상세페이지</h2>
	
	<hr size="1.5" width="80%" noshade="noshade">
	
	<div class="page-center">
	<input type="hidden" id="mem_num" value="${member.mem_num}">
	<ul >
		<li><label >회원 번호</label>
			${member.mem_num }
		</li>
		<li><label>회원 이름</label>
			${member.mem_name }
		</li>
		<li><label >회원 등급 <img id ="modifyauth" src="${pageContext.request.contextPath}/images/pencil_1361912.png"  width="18" height="18"></label>
			<span id="authspan">${member.mem_auth }</span> <div id="auth_update" style="display:none;"><input type="number" name="auth" class="inputcheck input-style" id="auth" maxlength="1"><input type="button" value="수정" id="authbtn" class="button2" ><input type="button" value="취소" id="delbtn" class="button2" ></div>
		</li>
		<li><label >회원 아이디</label>
			${member.mem_id }
		</li>
		<li><label >전화번호</label>
			${member.mem_phone }
		</li>
		<li><label >이메일</label>
			${member.mem_email }
		</li>
		<li><label >우편번호</label>
			${member.mem_zipcode }
		</li>
		<li><label >주소</label>
			${member.mem_address1 }  ${member.mem_address2 }
		</li>
		<li><label >가입일</label>
			${member.mem_rdate}
		</li>
	<c:if test="${!empty member.mem_mdate}">
		<li><label>수정일</label>
			${member.mem_mdate}
		</li>
	</c:if>
	</ul>
	</div>
	
	<br>
	<div class="align-center">
	<input type="button" value="메인" class="button2" onclick="location.href='${pageContext.request.contextPath}/main/main.do'">
	</div>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</div>
</body>
</html>