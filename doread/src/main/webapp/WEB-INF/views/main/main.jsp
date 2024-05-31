<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header_test.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/kyj.css" type="text/css">
<script type="text/javascript" src="../js/jquery-3.7.1.min.js"></script>
<script type="text/javascript">
$(".slider-space > div:gt(0)").hide();

setInterval(function() {
  $('.slider-space > div:first')
    .next()
    .end()
    .appendTo('.slider-space');
},  5000);

</script>
</head> 
<body>
<div class="page-main">
	<jsp:include page="/WEB-INF/views/news/test_header.jsp"/>
	<div class="content-main">
		<h4>이벤트</h4>
		<div class="event-space">
			<div class="slider-space">
				<c:forEach var="event" items="${eventList}">
					<div class="image-items" onclick="location.href='${pageContext.request.contextPath}/event/detailEvent.do?e_num=${event.e_num}'">
						<div class="event-item"><img src="${pageContext.request.contextPath}/upload/${event.e_image}" height="300"></div>
						<div class="event-item">
							<ul>
								<li><h2>${event.e_title}</h2></li>
								<li>${event.e_rdate}~${event.e_deadline}</li>
							</ul>		
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
		<div class="float-clear">
				<hr width="100%" size="1" noshade="noshade">
		</div>
		<h4>최신 상품</h4>
		<div class="image-space">
			<c:forEach var="book" items="${bookList}">
			<div class="horizontal-area">
				<a href="${pageContext.request.contextPath}/book/detail.do?book_num=${book.book_num}">
					<img src="${pageContext.request.contextPath}/upload/${book.book_img}">
					<span>${book.book_name}</span>				
					<br>
				</a>
			</div>
			</c:forEach>
			<div class="float-clear">
				<hr width="100%" size="1" noshade="noshade">
			</div>
		</div>
	</div>
</div>
</body>
</html>







