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
<script type="text/javascript">
	window.onload = function(){
		const event = document.getElementById('eventUser');
		
		event.onclick = function(){
			window.open('www.naver.com','팝업','width=500,height=500');       
		}
		
	}

</script>
</head>
<body>
<jsp:include page="/WEB-INF/views/event/eventheader.jsp"/>
<div class="page-main">

			<div class="eventvar">
 	<h1>EVENT</h1>
 </div>

<div class="align-left">
<b>${event.e_title}</b><br>
			진행기간 ${event.e_rdate}~${event.e_deadline} 조회수:${event.e_hit}

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

