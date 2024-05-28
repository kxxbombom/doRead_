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
		<h2>스토리 제목 :  ${sb.s_title}</h2>
		<h3>리뷰 도서 : ${sb.book_name }</h3>
		<hr size="1" noshade="noshade" width="100%">
		<c:if test="${!empty sb.s_image}">
		<div class="align-center">
		<img src="${pageContext.request.contextPath}/upload/${sb.s_image}" class="detail-img">
		</div>
		</c:if>
		<p>
			${sb.s_content}
		</p>
		<hr size="1" noshade="noshade" width="100%">
		<ul class="detail-sub">
			<li>
				<c:if test="${!empty sb.s_mdate}">
				최근 수정일 : ${sb.s_mdate}
				</c:if>
				작성일 : ${sb.s_rdate}
				<%-- 로그인한 회원번호와 작성자 회원번호가 일치해야 수정, 삭제 가능 --%>
				<c:if test="${user_num == sb.mem_num}">
				<input type="button" value="수정" onclick="location.href='storyUpdateForm.do?s_num=${sb.s_num}'">
				<input type="button" value="삭제" id="delete_btn">
				<script type="text/javascript">
					const delete_btn=document.getElementById('delete_btn');
					//이벤트 연결
					delete_btn.onclick=function(){
						let choice = confirm('삭제하시겠습니까?');
						if(choice){
							location.replace('storyDelete.do?s_num=${sb.s_num}');
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













