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

<script type="text/javascript">
$(document).ready(function() {
    // btn-hide 클래스를 가진 요소를 숨김
    $('.btn-hide').hide();

    // .board-cbtn 클래스를 가진 요소를 클릭했을 때
    $('.board-cbtn').click(function() {
        // 클릭된 요소의 부모 요소인 li를 찾고 그 하위의 .btn-hide 요소를 토글
        $(this).parent().find('.btn-hide').toggle();
    });

    // 삭제 버튼 클릭 시
    $('#delete_btn').click(function() {
        let choice = confirm('삭제하시겠습니까?');
        if (choice) {
            location.replace('newsDelete.do?news_num=${news.news_num}');
        }
    });
});s
</script>

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
			
			<ul class="board-btn-container">
				<li class="board-btn">
					<span class="board-cbtn"><a href="" onclick="return false;"><img src="${pageContext.request.contextPath}/upload/ellipsis-vertical-outline.svg" width="25px"></a></span>
		
					<ul class="btn-hide">
		            	<li><a href="newsUpdateForm.do?news_num=${news.news_num}">수정</a></li>
		            	<li><a href="newsDelete.do?news_num=${news.news_num}" id ="delete_btn">삭제</a></li>
		        	</ul>
		        	
				</li>
			</ul>	
			
		</c:if>
		<hr size="1" noshade width="100%">
		
		<c:if test="${!empty news.news_image}">
		 <div>
		 	<img src="${pageContext.request.contextPath}/upload/${news.news_image}" class="detail-img">
		 </div>
		</c:if>
		<p id="board_content">
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
		<div class="align-center list-btn">
			<input type="button" value="목록" onclick="location.href='newsList.do'">
			
		</div>
	</div>
</div>
</body>
</html>