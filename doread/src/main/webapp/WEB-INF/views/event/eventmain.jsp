<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이벤트 메인</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/kbm.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header_test.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>
</head>
<body>
<div class="page-main">
	<jsp:include page="/WEB-INF/views/news/test_header.jsp"/>
 			<h2 style="text-align:center;">EVENT</h2>
		<div class="align-center eventmain">
 			<form id="list_search_form" action="eventmain.do" method="get">
				<select id="selectlist" name ="sel" class="list_search">
					<option value="1" <c:if test="${check == 1}">selected</c:if>>진행중이벤트</option>
 					<option value="2" <c:if test="${check == 2}">selected</c:if> >종료된이벤트</option>
 					<option value="3" <c:if test="${check == 3}">selected</c:if>>당첨자보기</option>
 				</select>
 				<input type="text" name="eventsearch" class="inputcheck input-style" id="eventsearch" maxlength="30" placeholder="제목 또는 내용으로 검색가능">
 				<input id="subbtn" type="submit" value="검색" class="button2">
			</form>
			<script type="text/javascript">
				$('#selectlist').change(function(){
								$('#subbtn').click();
				});
			</script>
			<div class="list-space align-right">
				<div class="list-btn">
					<c:if test="${!empty user_num && user_auth == 9}">
						<input type="button" value="글쓰기"
							onclick="location.href='${pageContext.request.contextPath}/event/eventWrite.do'">
					</c:if>
				</div>
			</div>
			<hr width="40%" size="1" noshade="noshade">
			<div  class="eventmain">
				<c:if test="${check == 1}">
				<c:forEach var="event" items="${List}">
				<div class="horizontal-area">
					<p>
					<a href="${pageContext.request.contextPath}/event/detailEvent.do?e_num=${event.e_num}">
						<img src="${pageContext.request.contextPath}/upload/${event.e_image}" width="360px" height="460"><br>
						<span id ="e_title"><b>${event.e_title}</b></span>
						<br>
						이벤트기간 ${event.e_rdate} ~ ${event.e_deadline}
					</a>
					</p>
					<hr width="40%" size="1" noshade="noshade">
					<br><br><br>
				</div>
				</c:forEach>
				</c:if>
				
				<c:if test="${check == 2 }">
				<c:forEach var="event" items="${List}">
				<div class="horizontal-area">
					<a href="${pageContext.request.contextPath}/event/detailEvent.do?e_num=${event.e_num}">
						<img src="${pageContext.request.contextPath}/upload/${event.e_image}" width="360px" height="460"><br>
						<span id ="e_title"><b>${event.e_title}</b></span>
						<br>
						이벤트기간 ${event.e_rdate} ~ ${event.e_deadline}
					</a>
				</div>
				</c:forEach>
				</c:if>
				
				<c:if test="${check == 3}">
					<div class="align-center">
					<c:if test="${empty List}">
								<tr><td><b>당첨자가 없습니다.</b><td></tr>
					</c:if>
					<c:if test="${!empty List}">
						<table>
						<tr>
							<th>이벤트</th>
							<th>당첨자 id</th>
						</tr>
							<c:forEach var="event" items="${List}">
							<tr>
							<td>${event.e_title}</td>
							<td>${event.id}</td>
							</tr>
							</c:forEach>
						
							
						</table>
					</c:if>
					</div>
				</c:if>
				<div class="float-clear">
					<hr width="100%" size="1" noshade="noshade">
				</div>
			
	 			<span>${page}</span>
 			</div>
 	</div>	
</div>

<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>

