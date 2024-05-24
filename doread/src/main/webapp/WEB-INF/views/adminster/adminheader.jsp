<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div id="adminpage">
	<div>
		<img src="../images/face.jpg" width="93">
	</div>
	<div class=".adminnav">
	<ul >
		<li>
		
			<a href="${pageContext.request.contextPath}/adminster/aadminPage.do" >책등록</a>
		</li>
		<li>
			<a href="${pageContext.request.contextPath}/adminster/adminPage.do" >판매내역</a>
		</li>

		<li >
			<a href="${pageContext.request.contextPath}/adminster/adminPage.do" >회원관리</a>
		</li>
		<li>
			<a href="${pageContext.request.contextPath}/adminster/adminPage.do" >신고내역</a>
		</li>
		
	</ul>
	</div>
</div>