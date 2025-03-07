<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>문의 목록</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header_test.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/kbm.css" type="text/css">
<script type="text/javascript">
window.onload= function(){
	const myForm = document.getElementById('search_form');
	//이벤트 연결
	myForm.onsubmit=function(){
		const keyword = document.getElementById('keyword');
		if(keyword.value.trim()==''){
			alert('검색어를 입력하세요');
			keyword.value='';
			keyword.focus();
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
		<h2 style="text-align:center;">QnA 목록</h2>
		</div>
		<c:if test="${user_auth!=9 }">
		<c:if test="${count==0}">
		<div class="result-display">
		<hr size="1" width="80%" noshade="noshade">
			<p>표시할 게시물이 없습니다.</p>
		<hr size="1" width="80%" noshade="noshade">
		</div>
		</c:if>
		<c:if test="${count>0}">
		<table>
			<tr>
				<th>글번호</th>
				<th>제목</th>
				<th>답변여부</th>
				<th>작성일</th>
			</tr>
			<c:forEach var="qna" items="${list}">
			<c:if test="${qna.mem_num == user_num}">
			<tr>
				<td>${qna.q_num}</td>
				<td><a href="qnaDetail.do?q_num=${qna.q_num}">${qna.q_title}</a></td>
				<td>
					<c:if test="${empty qna.q_answer}"><b>N</b></c:if>
					<c:if test="${!empty qna.q_answer}"><b>Y</b></c:if>
				</td>
				<td>${qna.q_rdate}</td>
			</tr>
			</c:if>
			</c:forEach>
		</table>
		</c:if>
		</c:if>
		
		<c:if test="${user_auth == 9}">
		<c:if test="${count2==0}">
		<div class="result-display">
			표시할 게시물이 없습니다.
		</div>
		</c:if>
		<c:if test="${count2>0}">
		<table>
			<tr>
				<th>글번호</th>
				<th>제목</th>
				<th>답변여부</th>
				<th>작성일</th>
			</tr>
			<c:forEach var="qna2" items="${list2}">
			<tr>
				<td>${qna2.q_num}</td>
				<td><a href="qnaDetail.do?q_num=${qna2.q_num}">${qna2.q_title}</a></td>
				<td>
					<c:if test="${empty qna2.q_answer}"><b>N</b></c:if>
					<c:if test="${!empty qna2.q_answer}"><b>Y</b></c:if>
				</td>
				<td>${qna2.q_rdate}</td>
			</tr>
			</c:forEach>
		</table>
		</c:if>
		</c:if>
		<form id="list_search_form" action="qnaList.do" method="get">
			<ul class="list_search">
				<li>
					<select name="keyfield" class="input-style">
							<option value="1" <c:if test="${param.keyfield==1}">selected</c:if>>제목</option>
							<option value="2" <c:if test="${param.keyfield==2}">selected</c:if>>내용</option>
					</select>
				</li>
				<li>
					<input type="search" size="16" class="list_search" name="keyword" id="keyword" value="${param.keyword}" placeholder="검색내용입력">				
				</li>
				<li>
					<input type="submit" value="검색" class="list_search">
				</li>
			</ul>
		</form>
		<div class="list-space align-right">
			<div class="list-btn">
			<c:if test="${!empty user_num && user_auth == 2}">
			<input type="button" value="글쓰기" onclick="location.href='qnaWriteForm.do'">
			</c:if>
			<c:if test="${!empty user_num && user_auth == 9}">
			<input type="button" id="answerList-btn" value="답변" onclick="location.href='qnaAnswerList.do'">
			</c:if>
			<input type="button" value="목록" onclick="location.href='qnaList.do'">
			<input type="button" value="홈으로" onclick="location.href='${pageContext.request.contextPath}/main/main.do'"> <%--다른 경로--%>
			</div>
		<div class="align-center">${page}</div>
		</div>
</div>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>