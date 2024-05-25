<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원관리상세페이지</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/ssh.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/ysw.css" type="text/css">
</head>
<body>

<jsp:include page="/WEB-INF/views/adminster/adheader2.jsp"/>
<jsp:include page="/WEB-INF/views/adminster/adminheader.jsp"/>
<div class="page-main">

	<h2>회원 관리 상세페이지</h2>
	
	<hr size="1.5" width="80%" noshade="noshade">
	
	<div class="page-center">
	<ul >
		<li><label for="mem_num">회원 번호</label>
			${member.mem_num }
		</li>
		<li><label for="name">회원 이름</label>
			${member.mem_name }
		</li>
		<li><label for="mem_auth">회원 등급 <img id ="modifyauth" src="${pageContext.request.contextPath}/images/pencil_1361912.png"  width="18" height="18"></label>
			${member.mem_auth } 
		</li>
		<li><label for="mem_phone">전화번호</label>
			${member.mem_phone }
		</li>
		<li><label for="mem_email">이메일</label>
			${member.mem_email }
		</li>
		<li><label for="mem_zipcode">우편번호</label>
			${member.mem_zipcode }
		</li>
		<li><label for="mem_address">주소</label>
			${member.mem_address1 }  ${member.mem_address2 }
		</li>
		<li><label for="mem_rdate">가입일</label>
			${member.mem_rdate}
		</li>
	<c:if test="${!empty member.mem_mdate}">
		<li><label for="mem_mdate">수정일</label>
			${member.mem_mdate}
		</li>
	</c:if>
	</ul>
	</div>
	
	<br>
	<div class="align-center">
	<input type="button" value="메인" class="button2" onclick="location.href='${pageContext.request.contextPath}/main/main.do'">
	</div>
	

</div>
</body>
</html>