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

	$(".slider-space > div:gt(0)").hide();

	setInterval(function() {
	  $('.slider-space > div:first')
	    .next()
	    .end()
	    .appendTo('.slider-space');
	},  5000);
let i =2;
	setInterval(function() {
		    i++;
		    if(i>5){
		    	i=1;
		    }
		    $('#mainb').attr('src','${pageContext.request.contextPath}/mainimage/main'+i+'.JPG');
		  
		},  5000);
$(function(){
	$('#lbtn').click(function(){
		--i;
		if(i<1){
	    	i=5;
	    }
	    $('#mainb').attr('src','${pageContext.request.contextPath}/mainimage/main'+i+'.JPG');
	});
	$('#rbtn').click(function() {
	    ++i;
	    if(i>5){
	    	i=1;
	    }
	    $('#mainb').attr('src','${pageContext.request.contextPath}/mainimage/main'+i+'.JPG');
	  
	});
	
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
		<a href="${pageContext.request.contextPath}/event/eventmain.do"><img id="mainb" width="100%" src="${pageContext.request.contextPath}/mainimage/main1.JPG"  style="z-index:9;"></a>
	
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
			<h4>이벤트</h4>
		<div class="slider-container">
			<div class="slider-space">
				<c:forEach var="event" items="${eventList}">
					<div class="slider-items" onclick="location.href='${pageContext.request.contextPath}/event/detailEvent.do?e_num=${event.e_num}'">
						<div class="slider-image"><img src="${pageContext.request.contextPath}/upload/${event.e_image}" height="300"></div>
						<div class="slider-image">
							<ul>
								<li><h1>${event.e_title}</h1></li>
								<li>${event.e_rdate}~${event.e_deadline}</li>
							</ul>		
						</div>
					</div>
				</c:forEach>
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
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>

</body>
</html>







