<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>스토리 목록</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header_test.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/kbm.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/ysw.css" type="text/css">
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
		<h2 style="text-align:center;">스토리 목록</h2>
		
		<c:if test="${count==0}">
		<div class="result-display">
			표시할 게시물이 없습니다.
		</div>
		</c:if>
		<c:if test="${count>0}">
		<table>
			<tr style="background-color:rgb(26, 77, 46); color:#FFF;">
				<th>글번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성일</th>
				<th>조회수</th>
			</tr>
			<c:forEach var="story" items="${list}">
	 		<tr>
				<td>${story.s_num}</td>
				<td><a href="storyDetail.do?s_num=${story.s_num}" class="board-title">${story.s_title} <small>(${story.ccount })</small></a></td>
				<td>${story.mem_id}</td>
				<td>${story.s_rdate}</td>
				<td>${story.s_hit}</td>
			</tr>
			</c:forEach>
		</table>
		</c:if>
		<form id="list_search_form" action="storyBoardList.do" method="get">
			<ul class="list_search">
				<li>
					<select name="keyfield" class="list_search">
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
			<input type="button" value="글쓰기" onclick="location.href='storyBoardWriteForm.do'">
			</c:if>
			
			<input type="button" value="목록" onclick="location.href='storyBoardList.do'">
			<input type="button" value="홈으로" onclick="location.href='${pageContext.request.contextPath}/main/main.do'"> <%--다른 경로--%>
			</div>
		</div>
		<div class="align-center">${page}</div>
	</div>
</div>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>