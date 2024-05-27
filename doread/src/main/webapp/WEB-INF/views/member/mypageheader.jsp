<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="mypage-main">
	<img id="penimage" src="${pageContext.request.contextPath}/images/pencil_1361912.png" width="30" height="30">
	<div class="mypage-div">
		<ul>
			<li id="myphoto">
				<c:if test="${empty mem_photo}">
				<img src="${pageContext.request.contextPath}/images/face.png"
						width="100" height="100" class="my-photo">
				</c:if> <c:if test="${!empty mem_photo}">
					<img src="${pageContext.request.contextPath}/upload/${member.photo}"
						width="100" height="100" class="my-photo">
				</c:if>
			</li>
			<li style="padding-top: 40px; font-size: 19pt; text-align: left;">${user_id}님<br>어서오세요
			</li>
			<li>
				<a>회원정보</a>
				<div class="myul">
					<ul>
						<li><a href="${pageContext.request.contextPath}/member/memberdetail.do">회원 상세 내용</a></li>
						<li><a href="${pageContext.request.contextPath}/member/alarm.do">회원 알림</a></li>
						<li><a href="${pageContext.request.contextPath}/member/likelist.do">회원 찜 목록</a></li>
						<li><a href="${pageContext.request.contextPath}/member/deleteUserForm.do">회원 탈퇴</a></li>
					</ul>
				</div></li>
			<li>
				<a>쇼핑</a>
				<div class="myul">
					<ul>
						<li><a href="${pageContext.request.contextPath}">구매 내역 조회</a></li>
						<li><a href="${pageContext.request.contextPath}">주문/배송 목록</a></li>
					</ul>
				</div></li>

			<li>
				<a>활동내역</a>
				<div class="myul">
						<ul>
							<li><a href="${pageContext.request.contextPath}/member/post.do">게시글 내역</a></li>
							<li><a href="${pageContext.request.contextPath}/member/comment.do">댓글 내역</a></li>
						</ul>
				</div>
			<li>
				<a>QnA</a>
				<div class="myul">
					<ul>
						<li><a href="${pageContext.request.contextPath}/qna/qnaWriteForm.do">1:1문의</a></li>
						<li><a href="${pageContext.request.contextPath}/member/bookrequest.do">도서 신청</a></li>
				
					</ul>
				</div></li>
		</ul>
	</div>
</div>