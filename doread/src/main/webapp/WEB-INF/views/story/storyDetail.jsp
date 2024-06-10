<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>스토리</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header_test.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/kbm.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/story.reply.js"></script>
</head>
<body>
<div class="page-main">
	<jsp:include page="/WEB-INF/views/news/test_header.jsp"/>
	<div class="content-main">
		<div class="float-clear">
				<hr width="100%" size="1" noshade="noshade">
		</div>
		<h2>${sb.s_title}</h2>
		<div class="float-clear">
				<hr width="100%" size="1" noshade="noshade">
		</div>
		<c:if test="${!empty sb.mem_photo }">
				<img src="${pageContext.request.contextPath}/upload/${sb.mem_photo}" class="my-photo" width="46" height="43">
		</c:if>
		<c:if test="${empty sb.mem_photo}">
				<img src="${pageContext.request.contextPath}/images/face.png" class="my-photo" width="46" height="43">
		</c:if>
		${sb.mem_id} <br>
		${sb.s_rdate} &nbsp;  조회 : ${sb.s_hit} <br>
		도서명 : ${sb.book_name }
			<ul class="board-btn-container">
				<li class="board-btn">
					<span class="board-cbtn"><a href="" onclick="return false;"><img src="${pageContext.request.contextPath}/upload/ellipsis-vertical-outline.svg" width="25px"></a></span>
					<c:if test="${user_num == sb.mem_num}">
					<ul class="btn-hide">
		            	<li><a href="storyUpdateForm.do?s_num=${sb.s_num}">수정</a></li>
		            	<li><a href="#" id ="delete_btn">삭제</a></li>
		        	</ul>
		        	<script type="text/javascript">
		        	$('.btn-hide').hide();
		        	$('.board-cbtn').click(function() {
		        	    $(this).parent().find('.btn-hide').toggle();
		        	});
		        	$('#delete_btn').click(function(event) {
			        	let choice = confirm('삭제하시겠습니까?');
			    		if(choice){
			    			location.replace('storyDelete.do?s_num=${sb.s_num}');
			    		}
			    		//기본 이벤트 제거
			    		event.preventDefault();
		        	});
		        	</script>
					</c:if>
				</li>
			</ul>	
		<hr size="1" noshade="noshade" width="100%">
		<c:if test="${!empty sb.s_image}">
		<div class="align-center">
		<img src="${pageContext.request.contextPath}/upload/${sb.s_image}" class="detail-img">
		</div>
		</c:if>
		<p id="board_content">
			${sb.s_content}
		</p>
		<hr size="1" noshade="noshade" width="100%">
		<ul class="detail-sub">
			<li>
				<c:if test="${!empty sb.s_mdate}">
				최근 수정일 : ${sb.s_mdate}
				</c:if>
			</li>
		</ul>
		<div class="align-right">
			<a class="report" data-id="${sb.s_num}" href="#" data-name="s_num" >신고</a>
		</div>
		<hr width="100%" size="1" noshade="noshade">
		<!-- 댓글시작 -->
		<div id="reply_div">
			<span class="sc-title">댓글 달기</span>
			<form id="sc_form">
				<input type="hidden" name="s_num" value="${sb.s_num}" id="s_num">
				<textarea rows="3" cols="50" name="sc_content" 
				<c:if test="${empty user_num}">disabled="disabled"</c:if> 
				id="sc_content" class="rep-content"><c:if test="${empty user_num}">로그인해야 작성할 수 있습니다</c:if></textarea>
				<c:if test="${!empty user_num}">
				<div id="re_first">
					<span class="letter-count">300/300</span>
				</div>
				<div id="re_second" class="align-right">
					<input type="submit" value="등록" class="btn5">
				</div>
				</c:if>
			</form>
		</div>
		<div class="float-clear">
			<hr width="100%" size="1" noshade="noshade">
		</div>
		<!-- 댓글 목록 출력 시작 -->
		<div id="output"></div>
		<div class="paging-button" style="display:none;" >
			<input type="button" value="더 보기">
		</div>
		<div id="loading" style="display:none;">
			<img src="${pageContext.request.contextPath}/images/loading.gif" width="50" height="50">
		</div>
		<!-- 댓글 목록 출력 끝 -->
		<!-- 댓글끝 -->
	</div>
	<div class="hide reportdiv" style="width:300px; position:fixed; z-index:999 !important;
	bottom:400px;border:1px solid #999 !important;background:white;">
		<h3>신고하기</h3>
		<span> </span>
		<hr size="1" width="100%" >
		<input type="radio" name="reportcategory" class="inputcheck input-style radio2" value="1" />스팸홍보글/도배글<br>
		<input  type="radio" name="reportcategory" value="2" class="inputcheck input-style radio2"  /> 음란물<br>
		<input  type="radio" name="reportcategory" class="inputcheck input-style radio2" value="3"  />불법정보<br>
		<input type="radio" name="reportcategory" class="inputcheck input-style radio2" value="4" />청소년에게 유해한내용<br>
		<input  type="radio" name="reportcategory" value="5" class="inputcheck input-style radio2"  /> 욕설/생명경시/혐오/차별적표현<br>
		<input  type="radio" name="reportcategory" class="inputcheck input-style radio2" value="6"  />개인정보노출<br>
		<input  type="radio" name="reportcategory" class="inputcheck input-style radio2" value="7"  />불쾌한 표현이 있습니다.<br>
		<textarea class="textareareport"></textarea><br>
		<input type="button" class="rebtn" value="신고하기" ><input class="redelbtn" type="button" id="rebtn" value="취소">
	</div>
</div>
</body>
</html>













