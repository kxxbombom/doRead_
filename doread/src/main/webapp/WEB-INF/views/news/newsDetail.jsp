<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>뉴스글 상세</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/kbm.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/ysw.css" type="text/css">

<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>
</head>
<body>
<div class="page-main">
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<div class="content-main newsHead">
		<h2>뉴스게시판</h2>
		<h3>${news.news_title}</h3>
		<ul class="detail-info">
			<li>
				<c:if test="${user_auth == 9}">
				${news.mem_id}
				</c:if>
				${news.news_rdate}
			</li>
			<li>조회 : ${news.news_hit}</li>
		</ul>
		<c:if test="${user_auth == 9}">
			<div class="board-btn-container">
				<span class="board-btn"><a href="" onclick="return false;"><img src="${pageContext.request.contextPath}/upload/ellipsis-vertical-outline.svg" width="25px"></a></span>
	
				<ul class="btn-hide">
	            	<li><a href="newsUpdateForm.do?news_num=${news.news_num}">수정</a></li>
	            	<li><a href="${pageContext.request.contextPath}/news/newsDelete.do?news_num=${news.news_num}" id ="delete_btn">삭제</a></li>
	        	</ul>
	        	<script type="text/javascript">
					const delete_btn = document.getElementById('delete_btn');
					delete_btn.onclick=function(){
						let choice = confirm('삭제하시겠습니까?');
						if(choice){
							location.replace('newsDelete.do?news_num=${news.news_num}');
						}
					};
				</script>
			</div>
		</c:if>
		<hr size="1" noshade width="100%">
		
		<c:if test="${!empty news.news_image}">
		 <div>
		 	<img src="${pageContext.request.contextPath}/upload/${news.news_image}" class="detail-img">
		 </div>
		</c:if>
		<p>
			${news.news_content}
		</p>
		<hr size="1" noshade width="100%">
		<div>
			<span>
				<c:if test="${!empty news.news_mdate}">
					수정일 : ${news.news_mdate}
				</c:if>
			</span>
		</div>
		<div class="align-center">
			<input type="button" value="목록" onclick="location.href='newsList.do'">
			
		</div>
	</div>
</div>
</body>
</html>