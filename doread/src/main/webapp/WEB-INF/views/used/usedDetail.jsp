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
<script type="text/javascript" src="${pageContext.request.contextPath}/js/usedboard.reply.js"></script>
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

		

</body>
</html>

		