<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>문의 상세</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/ysw.css" type="text/css">
</head>
<body>
<div class="page-main">
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<div class="content-main">
		<h2>Q: ${qna.q_title}</h2>
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
				<c:if test="${!empty user_num && user_auth == 9&& empty qna.q_answer}}">
				<input type="button" value="답변하기" onclick="location.href='qnaUpdateAnswerForm.do?q_num=${qna.q_num}'">				
				</c:if> 
				<c:if test="${!empty user_num && user_auth == 9 && !empty qna.q_answer}">
				<input type="button" value="답변수정하기" onclick="location.href='qnaUpdateAnswerForm.do?q_num=${qna.q_num}'">				
				</c:if> 
				<%-- 로그인한 회원번호와 작성자 회원번호가 일치해야 수정, 삭제 가능 --%>
				<c:if test="${user_num == qna.mem_num}">
				<input type="button" value="수정" onclick="location.href='qnaUpdateForm.do?q_num=${qna.q_num}'">
				<input type="button" value="삭제" id="delete_btn">
				<script type="text/javascript">
					const delete_btn=document.getElementById('delete_btn');
					//이벤트 연결
					delete_btn.onclick=function(){
						let choice = confirm('삭제하시겠습니까?');
						if(choice){
							location.replace('qnaDelete.do?q_num=${qna.q_num}');
						}
					};
				</script>
				</c:if>
			</li>
		</ul>
	</div>
</div>
</body>
</html>













