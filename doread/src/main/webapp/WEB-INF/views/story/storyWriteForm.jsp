<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Story 작성하기</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header_test.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/kbm.css" type="text/css">
<script type="text/javascript">
window.onload=function(){
	const myForm = document.getElementById('write_form');
	//이벤트 연결
	myForm.onsubmit = function(){
		const title = document.getElementById('s_title');
		if(title.value.trim()==''){
			alert('제목을 입력하세요');
			title.value='';
			title.focus();
			return false;
		}
		const content= document.getElementById('s_content');
		if(content.value.trim()==''){
			alert('내용을 입력하세요');
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
		<h2>Story 작성하기</h2>
		<form id="write_form" action="storyWrite.do" method="post" enctype="multipart/form-data">
			<ul>
				<li>
					<label for="s_title">제목</label>
					<input type="text" name="s_title" id="s_title" maxlength="50">
				</li>
				<li>
					<label for="s_content">내용</label>
					<textarea rows="10" cols="50"  name="s_content" id="s_content"></textarea>
				</li>
				<li>
					<label for="s_image">이미지</label>
					<input type="file" name="s_image" id="s_image" accept="image/gif,image/png,image/jpeg">
				</li>
				<li>
					<label for="book_num">도서명</label>
					<select name="book_num">
						<c:forEach var="book" items="${list}">
							<option value="${book.book_num}">${book.book_name}</option>
						</c:forEach>
					</select>
				</li>
			</ul>
			<div class="align-center">
				<input type="submit" value="등록" class="button2">
				<input type="button" value="목록" class="button2" onclick="location.href='storyBoardList.do'">
			</div>
		</form>
	</div>
</div>
</body>
</html>