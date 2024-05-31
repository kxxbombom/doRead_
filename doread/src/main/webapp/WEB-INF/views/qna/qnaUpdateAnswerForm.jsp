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
		<h2>문의 수정</h2>
		<form id="write_form" action="qnaUpdateAnswer.do" method="post" enctype="multipart/form-data">
			<input type="hidden" name="q_num" value="${qna.q_num}">

			<ul>
				<li>
					<label for="q_title">문의 제목:${qna.q_title} </label>
					
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
					<c:if test="${qna.q_auth == 0}">
					<input type="radio" name="q_auth" value="0" id="auth0" checked>일반
					</c:if>
					<c:if test="${qna.q_auth == 1}">
					<input type="radio" name="q_auth" value="1" id="auth1" checked>신고
					</c:if>
					<c:if test="${qna.q_auth == 2}">
					<input type="radio" name="q_auth" value="2" id="auth2" checked>정지
					</c:if>
					<c:if test="${qna.q_auth == 3}">
					<input type="radio" name="q_auth" value="3" id="auth3" checked>도서신청
					</c:if>
					<hr size="1" noshade="noshade" width="100%">
				</li>
				<li>
					<label for="q_answer">답변</label>
					<textarea rows="5" cols="40"  name="q_answer" id="q_answer" >답변을 입력해주세요</textarea>
				</li>
			</ul>
			<div class="align-center">
				<input type="submit" value="수정">
				<input type="button" value="목록" onclick="location.href='qnaList.do'">
			</div>
		</form>
	</div>
</div>
</body>
</html>