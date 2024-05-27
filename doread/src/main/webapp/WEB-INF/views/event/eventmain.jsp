<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이벤트 메인</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/ssh.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/ysw.css" type="text/css">
</head>
<body>
<jsp:include page="/WEB-INF/views/event/eventheader.jsp"/>
<div class="page-main">

			<div class="eventvar">
 	<h1>EVENT</h1>
 </div>
<div>
<div class="align-center">
 			<form>
 			<input type="text" name="eventsearch" class="inputcheck input-style" id="eventsearch" maxlength="30" >
 			<input type="submit" value="검색" class="button2">
				<select>
 				<option value="2">종료된이벤트</option>
 				<option value="3">당첨자보기</option>
 				</select>
			</form>
			<div class="align-right">
 			<c:if test="${!empty user_num && user_auth == 9}">
 				<input type="button" value="글쓰기" class="button2" onclick="location.href='${pageContext.request.contextPath}/event/eventWrite.do'">
 			</c:if>
			</div>
		
			
			
			
			<div class="image-space">
			<c:forEach var="event" items="${List}">
			<div class="horizontal-area">
				<a href="${pageContext.request.contextPath}/event/detailEvent.do?e_num=${event.e_num}">
					<img src="${pageContext.request.contextPath}/upload/${event.e_image}">
					<span id ="e_title"><b>${event.e_title}</b></span>
					<br>
					이벤트기간 ${event.e_rdate} ~ ${event.e_deadline}
				</a>
			</div>
			</c:forEach>
			<div class="float-clear">
				<hr width="100%" size="1" noshade="noshade">
			</div>
		</div>
 			
 </div>
 	
			
			
			
</div>
	
</div>
</body>
</html>

