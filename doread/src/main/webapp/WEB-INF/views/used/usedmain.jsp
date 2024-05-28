<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>중고게시판 메인</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/ssh.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/ysw.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>
</head>
<body>
<jsp:include page="/WEB-INF/views/common/eventheader.jsp"/>
<div class="page-main">

		

<div class="align-center">
 		<h2>중고게시판</h2>	
 			<div class="float-clear">
				<hr width="100%" size="1" noshade="noshade">
			</div>
		
	
 			<form id="usedForm" action="usedList.do" method="get">
				<select id="selectlist" name ="sel" class="input-style">
				<option value="1" <c:if test="${check == 1}">selected</c:if>>판매중</option>
 				<option value="2" <c:if test="${check == 2}">selected</c:if>>거래중</option>
 				<option value="3" <c:if test="${check == 3}">selected</c:if>>판매완.ㄹ</option>
 				</select>
 				<input type="text" name="search" class="inputcheck input-style" id="search" maxlength="30" placeholder="제목 또는 내용으로 검색가능">
 				<input id="subbtn" type="submit" value="검색" class="button2">
			</form>
			<script type="text/javascript">
				$('#selectlist').change(function(){
								$('#subbtn').click();
				});
					
					
				
			
			</script>
			
 			<div class="align-right">
 			<c:if test="${!empty user_num}">
 				<input type="button" value="글쓰기" class="button2" onclick="location.href='${pageContext.request.contextPath}/used/usedWriteForm.do'">
 			</c:if>
			</div>
		
			
			
			
			
			<div class="float-clear">
				<hr width="100%" size="1" noshade="noshade">
			</div>
		

 	
			
			
			

	</div>
</div>
</body>
</html>

