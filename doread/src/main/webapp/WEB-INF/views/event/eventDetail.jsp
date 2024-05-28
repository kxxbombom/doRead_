<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이벤트 상세</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/ssh.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/ysw.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>
</head>
<body>
<jsp:include page="/WEB-INF/views/common/eventheader.jsp"/>
<div class="page-main">

			<div class="eventvar">
 	<h1>EVENT</h1>
 </div>

<div class="align-left">
<b>${event.e_title}</b><br>
			진행기간 ${event.e_rdate}~${event.e_deadline} 조회수:${event.e_hit}

</div>
	<div class="align-right">
	<c:if test="${!empty user_num && user_auth == 9}">
		<span class="global_site"><a href="" onclick="return false;"><img src="${pageContext.request.contextPath}/upload/ellipsis-vertical-outline.svg" width="25px"></a></span>
        
        <ul class="siteLink hide">
            <li class=""><a href="${pageContext.request.contextPath}/event/updateFormEvent.do?e_num=${event.e_num}" target="_blank">수정</a></li>
            <li class=""><a id ="delbtn"  href="${pageContext.request.contextPath}/event/deleteEvent.do?e_num=${event.e_num}" target="_blank">삭제</a></li>
            <li class=""><a id ="eventbtn" href="${pageContext.request.contextPath}/event/eventPresent.do?e_num=${event.e_num}" target="_blank">이벤트발표</a></li>
        </ul>
       
        <script type="text/javascript">
    	$(".global_site a").click(function() {
        if($('.siteLink').hasClass('hide')){
            $('.siteLink').removeClass('hide');
        }else{
            $('.siteLink').addClass('hide');
    	    }
  	  });
    	
    	$('#delbtn').click(function(event){
    		const choose =confirm('정말삭제하시겠습니까?');
    		
    		if(choose){
    			location.href="${pageContext.request.contextPath}/event/deleteEvent.do?e_num=${event.e_num}"
    		}
    		event.preventDefault();
    	})
    	
    	$('#eventbtn').click(function(event){
    		const choose =prompt('당첨 멤버 번호를 입력하세요');
    		
    		if(choose){
    			location.href="${pageContext.request.contextPath}/event/eventPresent.do?e_num=${event.e_num}&e_mem_num="+choose;
    		}
    		event.preventDefault();
    	})
		</script>


        
		</c:if>
		
	</div>		
<div class="align-center">

 			<div class="float-clear">
				<hr width="100%" size="1" noshade="noshade">
			</div>
	<img  src="${pageContext.request.contextPath}/upload/${event.e_image}"	width="600" height="600">
	 <br>이벤트내용 : ${event.e_content}<br>
	 이벤트 유의사항:이벤트 대상 상품 1건 이상 포함,<br>
국내도서·외국도서 5만원 이상 구입 시 또는 전자책 3만원 이상 구입 시 (택1)<br>

사은품의 수량이 한정되어 있으므로, 조기품절 될 수 있습니다.<br>

사은품 선택 시 할인쿠폰 사용에 제한이 있을 수 있습니다.<br>
(할인쿠폰 사용으로 적립예정 포인트가 사은품 선사용 포인트에 미달할 경우)<br>
이벤트발표는 검색>당첨자보기 또는 마이페이지>이벤트내역에서 확인할 수 있습니다 <br>
	
	<input id="eventUser" type="button" value="이벤트 응모하기"	  class="eventbutton" onclick="location.href='${pageContext.request.contextPath}/event/eventUserForm.do?e_num=${event.e_num}'">		
 			
 </div>
 	
	<div class="float-clear">
				<hr width="100%" size="1" noshade="noshade">
			</div>
			<div class="align-center">
			<input type="button" value="목록" class="eventbutton2" onclick="location.href='${pageContext.request.contextPath}/event/eventmain.do'">		
			</div>

	
</div>
</body>
</html>

