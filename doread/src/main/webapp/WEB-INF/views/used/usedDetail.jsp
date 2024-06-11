<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>중고게시판 상세</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/kbm.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header_test.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/usedboard.reply.js"></script>
</head>
<body>
<jsp:include page="/WEB-INF/views/news/test_header.jsp"/>
	<div class="page-main" style="z-index:1; position:relative;">
		<div class="align-center" style="z-index:1;" >
		 	<h2>중고게시판</h2>	
		 	<div class="float-clear">
				<hr width="100%" size="1" noshade="noshade">
			</div>
		</div>
		<div id="profileboard align-left">
			<h2>${used.u_title}</h2>
			<c:if test="${!empty used.user_image }">
				<img src="${pageContext.request.contextPath}/upload/${used.user_image}" class="my-photo" width="46" height="43">
			</c:if>
			<c:if test="${empty used.user_image}">
				<img src="${pageContext.request.contextPath}/images/face.png" class="my-photo" width="46" height="43">
			</c:if>
				${used.id}
				<br>
				<p>
				${used.u_rdate} &nbsp; 조회수:${used.u_hit}
		</div>
		
		<c:if test="${used.mem_num == user_num}">
			
			<ul class="board-btn-container">
				<li class="board-btn">
					<span class="board-cbtn" style="position:absolute; z-index:999; right:0; bottom:10px;"><a href="" onclick="return false;"><img src="${pageContext.request.contextPath}/images/ellipsis-vertical-outline.svg" width="25px"></a></span>
		
					<ul class="btn-hide">
		            	<li><a href="updateUsedForm.do?u_num=${used.u_num}">수정</a></li>
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
			    			location.replace('deleteUsed.do?u_num=${used.u_num}');
			    		}
			    		//기본 이벤트 제거
			    		event.preventDefault();
		        	});
		        	</script>
				</li>
			</ul>	
			
		</c:if>

		<hr width="100%" size="1" noshade="noshade" style="z-index:1;" >
		
		<div id="usedimage" style="z-index:1;">
			<c:if test="${!empty used.u_image }">
				<img src="${pageContext.request.contextPath}/upload/${used.u_image}" width="255" height="293">
			</c:if>
			<c:if test="${empty used.u_image}">
				<img src="${pageContext.request.contextPath}/images/noimage.jpg" width="255" height="293">
			</c:if>
		</div>
		
		<div id="usedcontent" style="z-index:1;">
				<a href="${pageContext.request.contextPath}/book/detail.do?book_num=${used.book_num}"><b>[<c:if test="${used.u_state == 1}">판매중</c:if><c:if test="${used.u_state == 2}">거래중</c:if><c:if test="${used.u_state == 3}">판매완료</c:if>]${used.book_name}</b> <br></a>
				가격(배송비포함명시) <fmt:formatNumber value="${used.u_price}"/>원<br>
				Do READ 중고 품질 판정 가이드:<b>
				<c:if test="${used.u_condition == 1}">미개봉</c:if>
				<c:if test="${used.u_condition == 2}">최상</c:if>
				<c:if test="${used.u_condition == 3}">상</c:if>
				<c:if test="${used.u_condition == 4}">중</c:if>
				<c:if test="${used.u_condition == 5}">하</c:if>
				<img id="gideimage" src="${pageContext.request.contextPath}/images/question_471664.png"  width="15px">
				</b>
				<br>
				<div class="hide gide siteLink" style="font-size:10pxt;  z-index:999; position:absolute; border:1px solid #d4d2d2; border-radius:15px; background:white;">
					&lt;Do READ 중고 품질 판정 가이드&gt;<br>
						미개봉(개봉하지않았음)<br>
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
	<div class="align-right" style="z-index:1;">
		<a class="report" data-id="${used.u_num}" href="#" data-name="u_num" >신고</a>
	</div>
		<hr width="100%" size="1" noshade="noshade">
	<!-- 댓글시작 -->
		<div id="reply_div">
			<span class="uc-title">댓글 달기</span>
			<form id="uc_form">
				<input type="hidden" name="u_num" value="${used.u_num}" id="u_num">
				<textarea rows="3" cols="50" name="uc_content" 
				<c:if test="${empty user_num}">disabled="disabled"</c:if> 
				id="uc_content" class="rep-content"><c:if test="${empty user_num}">로그인해야 작성할 수 있습니다</c:if></textarea>
				<c:if test="${!empty user_num}">
				<div id="re_first">
					<span class="letter-count">300/300</span>
				</div>
				<div id="re_second" class="align-right">
					<input type="submit" value="전송">
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
			<input type="button" value="다음글 보기">
		</div>
		<div id="loading" style="display:none;">
			<img src="${pageContext.request.contextPath}/images/loading.gif" width="50" height="50">
		</div>
		<!-- 댓글 목록 출력 끝 -->
		<!-- 댓글끝 -->
	</div>
	<div class="hide reportdiv" style="width:400px; position:fixed; z-index:999 !important;
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
<br><br><br><br><br><br><br>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>

		