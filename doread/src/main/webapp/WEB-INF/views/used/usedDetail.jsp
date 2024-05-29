<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>중고게시판 상세</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/ssh.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/ysw.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>
<script type="text/javascript">
$(function(){
	  let rowCount = 10;
	  let currentPage;
	  let count;
	  
	  function selectList(pageNum){
		   
		  
	  }
	  
	  $('#used_re').submit(function(event){
		  if(('#replyd').val().trim() == ''){
			  alert('내용을 입력하세요');
			  $('#replyd').val('').focus();
			  return false;
			  
		  }
		  
		  let form_data = $(this).serialize();
		  
		  $.ajax({
			  url:'writeUsedReply.do',
			  type:'post',
			  data:form_data,
			  dataType:'json',
			  success:function(param){
				  if(param.result =='logout'){
					  alert('로그인후 작성가능');
				  }else if(param.result=='success'){
					  alert('댓글작성');
					  initForm();
					  selectList(1);
				  }else{
					  alert('댓글쓰기오류');
				  }
			  },error:function(){
				   alert('네트워크오류');
			  }
			  
			  
			  
			  
		  });
		  
		  
		  
		  
		  
		  event.preventDefault();
	  });
	  
	  function initForm(){
		  $('textarea').val('');
		  $('#re_count').text('1000/1000');
		  
	  }
	  
	  
	  
	  
	  selectList(1);
	  
});

</script>
</head>
<body>
<jsp:include page="/WEB-INF/views/common/eventheader.jsp"/>
<div class="page-main">

		

<div class="align-center">
 		<h2>중고게시판</h2>	
 		<div class="float-clear">
				<hr width="100%" size="1" noshade="noshade">
		</div>
</div>
		<div id="profileboard align-left">
		<c:if test="${!empty used.user_image }">
		<img src="${pageContext.request.contextPath}/upload/${used.user_image}" class="my-photo" width="46" height="43">
		</c:if>
		<c:if test="${empty used.user_image}">
		<img src="${pageContext.request.contextPath}/images/face.png" class="my-photo" width="46" height="43">
		</c:if>
		${used.id}<br>
		${used.u_rdate} &nbsp; 조회수:${used.u_hit}
		
		</div>
		<c:if test="${used.mem_num == user_num}">
		<div class="align-right">
		
	
		<span class="global_site"><a href="" onclick="return false;"><img src="${pageContext.request.contextPath}/upload/ellipsis-vertical-outline.svg" width="25px"></a></span>
		<div id="updel" class="hide">
        <ul class="siteLink">
            <li class=""><a href="${pageContext.request.contextPath}/used/updateUsedForm.do?u_num=${used.u_num}" target="_blank">수정</a></li>
            <li class=""><a id ="delbtn"  href="${pageContext.request.contextPath}/used/deleteUsed.do?u_num=${used.u_num}" target="_blank">삭제</a></li>
           	
        </ul>
       </div>
        
		</div>
		</c:if>
		<div class="float-clear">
				<hr width="100%" size="1" noshade="noshade">
		</div>
		
		<div id="usedimage">
		<c:if test="${!empty used.u_image }">
		<img src="${pageContext.request.contextPath}/upload/${used.u_image}" width="255" height="293">
		</c:if>
		<c:if test="${empty used.u_image}">
		<img src="${pageContext.request.contextPath}/images/face.png" width="255" height="293">
		</c:if>
			
			
		</div>
		<div id="usedcontent">
			<div class="float-clear">
			<a href="${pageContext.request.contextPath}/book/detail.do?book_num=${used.book_num}"><b>[<c:if test="${used.u_state == 1}">판매중</c:if><c:if test="${used.u_state == 2}">거래중</c:if><c:if test="${used.u_state == 3}">판매완료</c:if>]${used.book_name}</b> <br></a>
			
			가격(배송비포함명시) <fmt:formatNumber value="${used.u_price}"/>원<br>
			Do READ 중고 품질 판정 가이드:<b>
			<c:if test="${used.u_condition == 1}">미개봉</c:if>
			<c:if test="${used.u_condition == 2}">최상</c:if>
			<c:if test="${used.u_condition == 3}">상</c:if>
			<c:if test="${used.u_condition == 4}">중</c:if>
			<c:if test="${used.u_condition == 5}">하</c:if>
			<img id="gideimage" src="${pageContext.request.contextPath}/images/question_471664.png"  width="15px">
			</b><br>
			<div class="hide gide siteLink">
				&lt;Do READ 중고 품질 판정 가이드&gt;
					
					미개봉<br>
					최상(개봉,사용감없음)<br>
					상(개봉, 2개이하의 파손이나 낙서)<br>
					중(개봉,4개이하의 파손이나 낙서)<br>
					하(개봉,5개이상의 파손,낙서)<br>
	
				
			<br> 
			</div>
			<br>
			<p>
			${used.u_content}
 	
		</div>
		</div>
				<hr width="100%" size="1" noshade="noshade">
		<div class="align-center">
			<input type="button" value="목록" class="button2" onclick="location.href='${pageContext.request.contextPath}/used/usedList.do'">
		</div>
	<div class="rediv">	
	<form id="used_re">
	
			<input  type="hidden" name="u_num" value="${used.u_num}">
			<textarea id="replyd" name="uc_content" ></textarea><input type="submit" value="등록" class="button2">
			<br><span id="re_count">1000/1000</span>
		
	</form>
	</div>
</div>
<script type="text/javascript">
    	$(".global_site a").click(function() {
        if($('#updel').hasClass('hide')){
            $('#updel').removeClass('hide');
        }else{
            $('#updel').addClass('hide');
    	    }
  	  });
    	
    	$('#delbtn').click(function(event){
    		const choose =confirm('정말삭제하시겠습니까?');
    		
    		if(choose){
    			location.href=href="${pageContext.request.contextPath}/used/deleteUsed.do?u_num=${used.u_num}";
    		}
    		event.preventDefault();
    	})
    	
    	
    	$('#gideimage').hover(function(){
    		$('.gide').removeClass('hide');
    	},function(){
    		$('.gide').addClass('hide');
    		
    	})
		</script>
		

</body>
</html>

		