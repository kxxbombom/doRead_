<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이벤트 팝업</title>
</head>
<body>
<div class="popup">
	
	<div class="eventvar">
 	<h1>EVENT 응모</h1>
 	</div>
	이벤트에 응모 하시겠습니까?
	<input type="button" value="응모" class="eventbutton2" onclick="location.href='${pageContext.request.contextPath}/event/eventdetailinsert.do?e_num='">		
	<input type="button" value="취소" class="eventbutton2" >		

</div>

</body>
</html>