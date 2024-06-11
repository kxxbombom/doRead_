<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header_test.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/kyj.css" type="text/css">
<script type="text/javascript" src="../js/jquery-3.7.1.min.js"></script>
<script type="text/javascript">
$(function(){	
	let i =2;
	setInterval(function() {
		    i++;
		    if(i>8){
		    	i=1;
		    }
		    $('#mainb').attr('src','${pageContext.request.contextPath}/mainimage/main'+i+'.JPG');
			
		    if(i==2){
		    	$('#main_link').attr('href','${pageContext.request.contextPath}/book/detail.do?book_num=246');
		    }else if(i==5){
		    	$('#main_link').attr('href','${pageContext.request.contextPath}/book/detail.do?book_num=214');
		    }else if(i==6){
		    	$('#main_link').attr('href','${pageContext.request.contextPath}/book/detail.do?book_num=245');
		    }else if(i==7){
		    	$('#main_link').attr('href','${pageContext.request.contextPath}/book/detail.do?book_num=72');
		    }else if(i==8){
		    	$('#main_link').attr('href','${pageContext.request.contextPath}/book/detail.do?book_num=247');
		    }else{
		    	$('#main_link').attr('href','${pageContext.request.contextPath}/event/eventmain.do');
		    }
		    
		},  5000);
	
	

	$('#lbtn').click(function(){
		--i;
		if(i<1){
	    	i=8;
	    }
	   
	    $('#mainb').attr('src','${pageContext.request.contextPath}/mainimage/main'+i+'.JPG');
	    
	    if(i==2){
	    	$('#main_link').attr('href','${pageContext.request.contextPath}/book/detail.do?book_num=246');
	    }else if(i==5){
	    	$('#main_link').attr('href','${pageContext.request.contextPath}/event/detailEvent.do?e_num=182');
		}else if(i==6){
	    	$('#main_link').attr('href','${pageContext.request.contextPath}/book/detail.do?book_num=245');
	    }else if(i==7){
	    	$('#main_link').attr('href','${pageContext.request.contextPath}/book/detail.do?book_num=72');
	    }else if(i==8){
	    	$('#main_link').attr('href','${pageContext.request.contextPath}/book/detail.do?book_num=247');
	    }else{
	    	$('#main_link').attr('href','${pageContext.request.contextPath}/event/eventmain.do');
	    }
 
	});
	$('#rbtn').click(function() {
	    ++i;
	    if(i>8){
	    	i=1;
	    }
	    $('#mainb').attr('src','${pageContext.request.contextPath}/mainimage/main'+i+'.JPG');
	  	
	    
	    if(i==2){
	    	$('#main_link').attr('href','${pageContext.request.contextPath}/book/detail.do?book_num=246');
	    }else if(i==5){
	    	$('#main_link').attr('href','${pageContext.request.contextPath}/event/detailEvent.do?e_num=182');
		}else if(i==6){
	    	$('#main_link').attr('href','${pageContext.request.contextPath}/book/detail.do?book_num=245');
	    }else if(i==7){
	    	$('#main_link').attr('href','${pageContext.request.contextPath}/book/detail.do?book_num=72');
	    }else if(i==8){
	    	$('#main_link').attr('href','${pageContext.request.contextPath}/book/detail.do?book_num=247');
	    }else{
	    	$('#main_link').attr('href','${pageContext.request.contextPath}/event/eventmain.do');
	    }
	});
	
	let j = 1;
	setInterval(function() {
	    j++;
	    if(j>4){
	    	j=1;
	    }
	    $('#eventb').attr('src','${pageContext.request.contextPath}/eventimage/eventimage'+j+'.jpg');
		
	    if(j==2){
	    	$('#event_link').attr('href','${pageContext.request.contextPath}/event/detailEvent.do?e_num=203');
	    }else if(j==3){
	    	$('#event_link').attr('href','${pageContext.request.contextPath}/event/detailEvent.do?e_num=204');
	    }else if(j==4){
	    	$('#event_link').attr('href','${pageContext.request.contextPath}/event/detailEvent.do?e_num=182');
	    }
	},  5000);
	
});
	

</script>
</head> 
<body>
<div class="page-main">
	<jsp:include page="/WEB-INF/views/news/test_header.jsp"/>
	<div class="content-main" >
	<label class="container" style="z-index:999; left:340px; top:120px;" id="lbtn">
  	<input checked="checked" type="checkbox">
  	<svg id="rightside" viewBox="0 0 320 512" height="1em" xmlns="http://www.w3.org/2000/svg" class="chevron-right"><path d="M310.6 233.4c12.5 12.5 12.5 32.8 0 45.3l-192 192c-12.5 12.5-32.8 12.5-45.3 0s-12.5-32.8 0-45.3L242.7 256 73.4 86.6c-12.5-12.5-12.5-32.8 0-45.3s32.8-12.5 45.3 0l192 192z"></path></svg>
	</label>
	<label class="container" style="z-index:999;left:370px; top:120px;" id="rbtn">
 	<input checked="checked" type="checkbox" >
  	<svg   viewBox="0 0 320 512" height="1em" xmlns="http://www.w3.org/2000/svg" class="chevron-right"><path d="M310.6 233.4c12.5 12.5 12.5 32.8 0 45.3l-192 192c-12.5 12.5-32.8 12.5-45.3 0s-12.5-32.8 0-45.3L242.7 256 73.4 86.6c-12.5-12.5-12.5-32.8 0-45.3s32.8-12.5 45.3 0l192 192z"></path></svg>
	</label>
	<br>
		<a href="${pageContext.request.contextPath}/event/eventmain.do" id="main_link"><img id="mainb" width="100%" src="${pageContext.request.contextPath}/mainimage/main1.JPG"  style="z-index:9;"></a>
	
		<div class="float-clear">
				<hr width="100%" size="1" noshade="noshade">
		</div>
		<h4>최신 상품</h4>
		<div class="image-space">
			<c:forEach var="book" items="${bookList}">
			<div class="horizontal-area">
				<a href="${pageContext.request.contextPath}/book/detail.do?book_num=${book.book_num}">
					<img src="${pageContext.request.contextPath}/upload/${book.book_img}">
					<span>${book.book_name}</span>				
					<br>
				</a>
			</div>
			</c:forEach>
			<div class="float-clear">
				<hr width="100%" size="1" noshade="noshade">
			</div>
		<div class="slider-container">
			<div class="main-slider">
				<div>
					<a href="${pageContext.request.contextPath}/event/detailEvent.do?e_num=202" id="event_link"><img id="eventb" width="100%" src="${pageContext.request.contextPath}/eventimage/eventimage1.jpg"  style="z-index:9;"></a></div>
				</div>
			</div>
		</div>
		</div>
	</div>
</div>
<div class="wrapper1">
  <input type="checkbox" id="toogle" class="hidden-trigger">
  <label for="toogle" class="circle">
<svg class="svg" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" width="48" height="48" xml:space="preserve" version="1.1" viewBox="0 0 48 48">
    <image width="48" height="48" xlink:href="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAADAAAAAwCAQAAAD9CzEMAAAAbElEQVR4Ae3XwQnFQAiE4eVVsGAP1mkPFjwvQvYSWCQYCYGZv4Dv5MGB5ghcIiDQI+kCftCzNsAR8y5gYu2rwCBAgMBTgEC3rek2yQEtAZoDjso8AyaKexmIDJUZD40AAQIE0gwx449GgMC9/t0b7GTsa7J+AAAAAElFTkSuQmCC"></image>
  </svg>
  </label>
  <div class="subs">
    <button class="sub-circle">
    <button class="sub-circle">
      <input value="1" name="sub-circle" type="radio" id="sub6" class="hidden-sub-trigger">
      <label for="sub6"></label>
    </button>
    <button class="sub-circle">
      <input value="1" name="sub-circle" type="radio" id="sub7" class="hidden-sub-trigger">
      <label for="sub7"></label>
    </button>
    <button class="sub-circle">
      <input value="1" name="sub-circle" type="radio" id="sub8" class="hidden-sub-trigger">
      <label for="sub8"></label>
    </button>
   </div>
</div>
<div class="floBanPc1 hidden-md hidden-sm"><a href="#" target="_blank"><img src="${pageContext.request.contextPath}/upload/btn_side_kakaotalk.png" href="#" width="100" height="100"></a></div>
<div class="floBanPc2 hidden-md hidden-sm	"><a href="#" target="_blank"><img src="${pageContext.request.contextPath}/upload/btn_side_phone.png" href="#" width="100" height="100"></a></div>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>

</body>
</html>







