<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>뉴스 글수정</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/kbm.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header_test.css" type="text/css">
<script type="text/javascript">
	window.onload=function(){
		const myForm = document.getElementById('newsUpdate_form');
		myForm.onsubmit=function(){
			const title = document.getElementById('news_title');
			if(title.value.trim() == ''){
				alert('제목을 입력하세요');
				title.value = '';
				title.focus();
				return false;
			}
			const content = document.getElementById('news_content');
			if(content.value.trim() == ''){
				alert('내용을 입력하세요');
				content.value = '';
				content.focus();
				return false;
			}
		};
	};
</script>

</head>
<body>
<div class="page-main">
	<jsp:include page="test_header.jsp"/>
	<div class="content-main newsHead">
		<h2><a href="newsList.do">뉴스게시판 수정</a></h2>
		<hr size="1" noshade width="100%">
		<form id="newsUpdate_form" action="newsUpdate.do" method="post" enctype="multipart/form-data">
			<input type="hidden" name="news_num" value="${news.news_num}">
			<ul>
				<li>
					<input type="text" name="news_title" id="news_title" value="${news.news_title}" maxlength="50">
				</li>
				<li>
					<textarea rows="25" cols="120" name="news_content" id="news_content">${news.news_content}</textarea>
				</li>
				<li>
					<input type="file" name="news_image" id="news_image" accept="image/gif, image/png, image/jpeg">
					<c:if test="${!empty news.news_image}">
						<div id="file_detail">
							<img src="${pageContext.request.contextPath}/upload/${news.news_image}" width="140">
							<br>
							<input type="button" value="이미지 삭제" id="image_del">
							<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>
							<script type="text/javascript">
								$(function(){
									$('#image_del').click(function(){
										let choice = confirm('이미지를 삭제하시겠습니까?');
										if(choice){
											$.ajax({
												url:'deleteImage.do',
												type:'post',
												data:{news_num:${news.news_num}},
												dataType:'json',
												success:function(param){
													if(param.result == 'logout'){
														alert('로그인 후 이용하세요');
													}else if(param.result == 'wrongAccess'){
														alert('잘못된 접근입니다');
													}else if(param.result == 'success'){
														$('#file_detail').hide();
													}else{
														alert('파일 삭제 오류 발생');
													}
												},
												error:function(){
													alert('이미지 삭제 오류 발생');
												}
											});
										}
									});
								});
							</script>
						</div>
					
					</c:if>
				</li>
				
				
			</ul>
			<div class="align-center list-btn">
				<input type="submit" value="수정">
				<input type="button" value="목록" onclick="location.href='newsList.do'">
			</div>
		</form>
	</div>
</div>
</body>
</html>