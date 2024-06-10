<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>뉴스게시판 목록</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header_test.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/kbm.css" type="text/css">
</head>
<body>
<div class="page-main">
	<jsp:include page="test_header.jsp"/>
	<div class="content-main newsHead">
		<h2><a href="newsList.do">뉴스게시판</a></h2>
		
		<c:if test="${count == 0}">
			<div class="result-display">
				표시할 게시물이 없습니다.
			</div>
		</c:if>
		
		<c:if test="${count > 0}">
		<table>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<c:if test="${user_auth == 9}">
				<th>작성자</th>
				</c:if>
				<th>작성일</th>
				<th>조회</th>
			</tr>
			<c:forEach var="news" items="${list}">
			<tr>
				<td>${news.news_num}</td>
				<td><a href="newsDetail.do?news_num=${news.news_num}" class="board-title">${news.news_title}</a></td>
				<c:if test="${user_auth == 9}">
				<td class="text-center">${news.mem_id}</td>
				</c:if>
				<td class="text-center">${news.news_rdate}</td>
				<td>${news.news_hit}</td>
			</tr>
			</c:forEach>
		</table>
		<%-- <hr size="1" noshade width="100%"> --%>

		<form id="list_search_form" action="newsList.do" method="get">
			<ul class="list_search">
				<li>
					<select name="keyfield" class="list_search">
						<option value="1" <c:if test="${param.keyfield == 1}">selected</c:if>>제목</option>
						<option value="2" <c:if test="${param.keyfield == 2}">selected</c:if>>내용</option>
					</select>
				</li>
				<li>
					<input type="search"  class="list_search" size="16" name="keyword" id="keyword2" value="${param.keyword}">
				</li>
				<li>
					<input type="submit" value="검색" class="list_search">
				</li>
			</ul>
		</form>
		<script type="text/javascript">
		
			const myForm = document.getElementById('list_search_form');
	
			myForm.onsubmit=function(){
				const keyword = document.getElementById('keyword2');
				if(keyword.value.trim() == ''){
					alert('검색어를 입력하세요');
					keyword.value = '';
					keyword.focus();
					return false;
				}
			};
		
</script>
		</c:if>
		<div class="list-space align-right">
			<div class="list-btn">
			<c:if test="${!empty user_num && user_auth == 9}">
			<input type="button" value="글쓰기" onclick="location.href='newsWriteForm.do'">
			</c:if>
			
			<input type="button" value="목록" onclick="location.href='newsList.do'">
			<input type="button" value="홈으로" onclick="location.href='${pageContext.request.contextPath}/main/main.do'"> <%--다른 경로--%>
			</div>
		</div>
		</div>
		
		<div class="align-center">${page}</div>
		
</div>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>