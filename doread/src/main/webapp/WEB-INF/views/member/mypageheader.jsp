<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="mypage-main">
	<img id="penimage" src="${pageContext.request.contextPath}/images/pencil_1361912.png" width="30" height="30">
	<div class="mypage-div">
		<ul>
			<li id="myphoto">
				<c:if test="${empty member.mem_photo}">
				<img src="${pageContext.request.contextPath}/images/face.png"
						width="100" height="100" class="my-photo">
				</c:if>
				<c:if test="${!empty member.mem_photo}">
				<img src="${pageContext.request.contextPath}/upload/${member.mem_photo}"
						width="100" height="100" class="my-photo">
				</c:if>
				<div class="updatephoto">
						<input type="button" value="수정"
						                     id="photo_btn">
					</div>
					<div id="photo_choice" style="display:none;">
						<input type="file" id="photo"
						          accept="image/gif,image/png,image/jpeg">
						<input type="button" value="전송" id="photo_submit">
						<input type="button" value="취소" id="photo_reset">          
					</div>
			</li>
			<li style="padding-top: 40px; font-size: 19pt; text-align: left;">${user_id}님<br>어서오세요
			</li>
			<li>
				<a>회원정보</a>
				<div class="myul">
					<ul>
						<li><a href="${pageContext.request.contextPath}/member/memberdetail.do">회원 상세 내용</a></li>
						<li><a href="${pageContext.request.contextPath}/member/membernotice.do">회원 알림</a></li>
						<li><a href="${pageContext.request.contextPath}/book/bookfav.do">회원 찜 목록</a></li>
						<li><a href="${pageContext.request.contextPath}/member/deleteUserForm.do">회원 탈퇴</a></li>
					</ul>
				</div></li>
			<li>
				<a>쇼핑</a>
				<div class="myul">
					<ul>
						<li><a href="${pageContext.request.contextPath}/shopping/buylist.do">구매 내역 조회</a></li>
						<li><a href="${pageContext.request.contextPath}">주문/배송 목록</a></li>
					</ul>
				</div></li>

			<li>
				<a>활동내역</a>
				<div class="myul">
						<ul>
							<li><a href="${pageContext.request.contextPath}/post/postlist.do">게시글 내역</a></li>
							<li><a href="${pageContext.request.contextPath}/member/comment.do">댓글 내역</a></li>
						</ul>
				</div>
			<li>
				<a>QnA</a>
				<div class="myul">
					<ul>
						<li><a href="${pageContext.request.contextPath}/qna/qnaWriteForm.do">1:1문의/도서신청</a></li>
					</ul>
				</div></li>
		</ul>
	</div>
</div>