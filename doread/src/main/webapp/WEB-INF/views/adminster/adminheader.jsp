<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="adminpage">
	<img id="penimage"src="${pageContext.request.contextPath}/images/pencil_1361912.png"  width="30" height="30">
	<div class="adminnav">
	<ul >
		<li id="myphoto">
			<c:if test="${empty mem_photo}">
			<img src="${pageContext.request.contextPath}/images/face.png" 
						                   width="100" height="100" class="my-photo">
			</c:if>
			<c:if test="${!empty mem_photo}">
			<img src="${pageContext.request.contextPath}/upload/${member.photo}" 
					                   width="100" height="100" class="my-photo">
			</c:if>
		
		</li>
		<li style="padding-top:40px; font-size:19pt; text-align:left;">
			<b>관리자<br>페이지입니다.</b>
		</li>
		<li>
			<a href="${pageContext.request.contextPath}/adminster/adminPage.do" >책등록</a>
			<div class="subul">
			<ul >
				<li></li>
			</ul>
			</div>
		</li>
		<li>
			<a href="${pageContext.request.contextPath}/adminster/userList.do" >회원관리</a>
			<div class="subul">
			<ul>
				<li><a href="${pageContext.request.contextPath}/adminster/userList.do">-전체회원</a></li>
				<li><a href="${pageContext.request.contextPath}/adminster/userList2.do">-정지회원</a></li>
			</ul>
			</div>
		</li>
		<li >
			<a href="${pageContext.request.contextPath}/adminster/adminreport.do" >스토리보드신고내역</a>
			<div class="subul">
			<ul  style="font-size:12px;">
				<li><a href="${pageContext.request.contextPath}/adminster/adminreport.do">/신고게시글</a><a href="${pageContext.request.contextPath}/adminster/srclist.do">/신고댓글</a></li>
				<li><a href="${pageContext.request.contextPath}/adminster/limitlist.do">/정지게시글</a><a href="${pageContext.request.contextPath}/adminster/limitcommlist.do">/정지댓글</a></li>
			
			</ul>
			</div>
		</li>
		<li>
			<a href="${pageContext.request.contextPath}/adminster/adminreport.do" >중고보드신고내역</a>
			<div class="subul">
			<ul style="font-size:12px;" >
				<li><a href="#">/신고게시글</a><a href="${pageContext.request.contextPath}/adminster/srclist.do">/신고댓글</a></li>
				<li><a href="${pageContext.request.contextPath}/adminster/limitlist.do">/정지게시글</a><a href="${pageContext.request.contextPath}/adminster/limitcommlist.do">/정지댓글</a></li>
				
			</ul>
			</div>
		</li>
	</ul>
	</div>
</div>