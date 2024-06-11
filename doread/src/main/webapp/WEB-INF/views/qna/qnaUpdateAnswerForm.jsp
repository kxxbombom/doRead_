<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>문의 수정</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header_test.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/kbm.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/ssh2.css" type="text/css">
<script type="text/javascript">
window.onload=function(){
	const myForm = document.getElementById('write_form');
	//이벤트 연결
	myForm.onsubmit = function(){
		const content= document.getElementById('q_answer');
		if(content.value.trim()==''){
			alert('문의 답변을 입력하세요');
			content.value='';
			content.focus();
			return false;
		}
	};
};
</script>
</head>
<body>
<div class="page-main">
	<jsp:include page="/WEB-INF/views/news/test_header.jsp"/>
	<div class="content-main">
		<h2>답변 등록</h2>
		<form id="write_form" action="qnaUpdateAnswer.do" method="post" enctype="multipart/form-data">
			<input type="hidden" name="q_num" value="${qna.q_num}">

			<ul>
				<li>
					<p>
					<label for="q_title">문의 제목:</label>${qna.q_title}
					</p>
				</li>
				<li>
					<p>
					<label for="q_content">문의 내용</label>
					${qna.q_content}
					</p>
				</li>
				<li>
					<label for="q_image">이미지</label>
					<img src="${pageContext.request.contextPath}/upload/${qna.q_image}">
				</li>
				<li>
					<label for="q_auth">문의 유형</label>
					<input type="radio" name="q_auth" value="0" id="auth0" <c:if test="${qna.q_auth == 0}">checked</c:if> disabled>일반
					<input type="radio" name="q_auth" value="1" id="auth1" <c:if test="${qna.q_auth == 1}">checked</c:if> disabled>신고
					<input type="radio" name="q_auth" value="2" id="auth2" <c:if test="${qna.q_auth == 2}">checked</c:if> disabled>정지
					<input type="radio" name="q_auth" value="3" id="auth3" <c:if test="${qna.q_auth == 3}">checked</c:if> disabled>도서신청
					<input type="radio" name="q_auth" value="4" id="auth4" <c:if test="${qna.q_auth == 4}">checked</c:if> disabled>반품 및 교환
					
					<hr size="1" noshade="noshade" width="100%">
				</li>
				<li>
					<label for="q_answer">답변</label>
					<textarea rows="5" cols="40"  name="q_answer" class="input-style"  id="q_answer" placeholder="답변을 입력해주세요"></textarea>
				</li>
			</ul>
			<div class="align-center">
				<input type="submit" value="답변 등록"  class="button2">
				<input type="button" value="목록"  class="button2" onclick="location.href='qnaList.do'">
			</div>
		</form>
	</div>
</div>
</body>
</html>