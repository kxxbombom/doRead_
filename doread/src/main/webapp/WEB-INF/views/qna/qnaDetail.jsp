<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>문의 상세</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header_test.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/kbm.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>
</head>
<body>
<div class="page-main">
	<jsp:include page="/WEB-INF/views/news/test_header.jsp"/>
	<div class="content-main">
		<h2>Q: ${qna.q_title}</h2>
		<ul class="board-btn-container">
				<li class="board-btn">
					<span class="board-cbtn"><a href="" onclick="return false;"><img src="${pageContext.request.contextPath}/upload/ellipsis-vertical-outline.svg" width="25px"></a></span>
					<c:if test="${user_num == qna.mem_num}">
					<ul class="btn-hide">
		            	<li><a href="qnaUpdateForm.do?q_num=${qna.q_num}">수정</a></li>
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
			    			location.replace('qnaDelete.do?q_num=${qna.q_num}');
			    		}
			    		//기본 이벤트 제거
			    		event.preventDefault();
		        	});
		        	</script>
					</c:if>
				</li>
			</ul>
		<hr size="1" noshade="noshade" width="100%">
		<c:if test="${!empty qna.q_image}">
		<div class="align-center">
		<img src="${pageContext.request.contextPath}/upload/${qna.q_image}" class="detail-img">
		</div>
		</c:if>
		<p>
			${qna.q_content}
		</p>
		<hr size="1" noshade="noshade" width="100%">
		<c:if test="${empty qna.q_answer}">
		<h2>A:</h2>
		<p>
			아직 답변이 완료되지 않았습니다.
		</p>
		<hr size="1" noshade="noshade" width="100%">
		</c:if>
		<c:if test="${!empty qna.q_answer}">
		<h2>A: </h2>
		<p>
			${qna.q_answer}
		</p>
		<hr size="1" noshade="noshade" width="100%">
		</c:if>
		<ul class="detail-sub">
			<li>
				<c:if test="${!empty qna.q_mdate}">
				최근 수정일 : ${qna.q_mdate}
				</c:if>
				작성일 : ${qna.q_rdate}
				<%-- 관리자 등급만 답변 가능--%>
				<%-- 관리자가 작성한 글만 수정 가능?--%>
				<c:if test="${!empty user_num && user_auth == 9 && empty qna.q_answer}">
				<input type="button" value="답변하기" onclick="location.href='qnaUpdateAnswerForm.do?q_num=${qna.q_num}'">				
				</c:if> 
				<c:if test="${!empty user_num && user_auth == 9 && !empty qna.q_answer}">
				<input type="button" value="답변수정하기" onclick="location.href='qnaUpdateAnswerForm.do?q_num=${qna.q_num}'">				
				</c:if> 
				
			</li>
		</ul>
		<div class="list-space align-right">
			<div class="list-btn">
				<input type="button" value="목록" onclick="location.href='${pageContext.request.contextPath}/qna/qnaList.do'">
			</div>
		</div>
	</div>
</div>
</body>
</html>