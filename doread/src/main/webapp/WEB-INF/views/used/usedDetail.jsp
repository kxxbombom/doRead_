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
		<img src="${pageContext.request.contextPath}/upload/${used.mem_photo}" class="my-photo" width="46" height="43">
		</c:if>
		<c:if test="${empty used.user_image}">
		<img src="${pageContext.request.contextPath}/images/face.png" class="my-photo" width="46" height="43">
		</c:if>
		${used.id}<br>
		${used.u_rdate} &nbsp; 조회수:${used.u_hit}
		
		</div>
		<div class="align-right">
		
	
		<span class="global_site"><a href="" onclick="return false;"><img src="${pageContext.request.contextPath}/upload/ellipsis-vertical-outline.svg" width="25px"></a></span>
<div id="updel" class="hide">
        <ul class="siteLink">
            <li class=""><a href="${pageContext.request.contextPath}/event/updateFormEvent.do?e_num=${event.e_num}" target="_blank">수정</a></li>
            <li class=""><a id ="delbtn"  href="${pageContext.request.contextPath}/event/deleteEvent.do?e_num=${event.e_num}" target="_blank">삭제</a></li>
            <li class=""><a id ="eventbtn" href="${pageContext.request.contextPath}/event/eventPresent.do?e_num=${event.e_num}" target="_blank">이벤트발표</a></li>
        </ul>
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
		
		</div>
		<div class="float-clear">
				<hr width="100%" size="1" noshade="noshade">
		</div>
		
		<div id="usedimage">
		<c:if test="${!empty used.user_image }">
		<img src="${pageContext.request.contextPath}/upload/${used.u_image}" width="255" height="293">
		</c:if>
		<c:if test="${empty used.user_image}">
		<img src="${pageContext.request.contextPath}/images/face.png" width="255" height="293">
		</c:if>
			
			
		</div>
		<div id="usedcontent">
			<div class="float-clear">
			<a href="${pageContext.request.contextPath}/book/detail.do?book_num=${used.book_num}"><b>[중고]${used.book_name}</b> <br></a>
			
			가격(배송비포함명시) <fmt:formatNumber value="${used.u_price}"/>원<br>
			Do READ 중고 품질 판정 가이드:<b>
			<c:if test="${used.u_condition == 1}">미개봉</c:if>
			<c:if test="${used.u_condition == 2}">최상</c:if>
			<c:if test="${used.u_condition == 3}">상</c:if>
			<c:if test="${used.u_condition == 4}">중</c:if>
			<c:if test="${used.u_condition == 5}">하</c:if>
			<img src="${pageContext.request.contextPath}/images/question_471664.png"  width="15px">
			</b><br>
			<br>
			<p>
			${used.u_content}
 	
		</div>
		</div>
				<hr width="100%" size="1" noshade="noshade">
		
			<input type="button" value="목록" class="button2" onclick="location.href='${pageContext.request.contextPath}/used/usedList.do'">

	
</div>


</body>
</html>

		