<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>mypage header</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/kts.css" type="text/css">
</head>
<body>
	<div class="content-main">
		<div class="mypage-div">
				<div class="mypage-left">
					<ul>
						<li><c:if test="${empty member.photo}">
								<img src="${pageContext.request.contextPath}/images/face.png"
									width="100" height="100" class="my-photo"><br>
							</c:if>
							<c:if test="${!empty member.photo}">
								<img
									src="${pageContext.request.contextPath}/upload/${member.photo}"
									width="100" height="100" class="my-photo">
							</c:if>
						</li>
						<li>
							<div class="align-center">
								<input type="file" id="photo" accept="image/gif,image/png,image/jpeg">
							</div>
						</li>
					</ul>
				</div>
				<div class="mypage-right">
					<ul>
						<li class="page-name">${user_id}님<br>어서오세요
						</li>
					</ul>
					<ul>
						<li class="outer-main"><span class="mypage-title">회원정보</span>
							<ul>
								<li class="inner-menu">
									<a href="${pageContext.request.contextPath}/member/detail.do">회원 상세 내용</a><br>
									<a href="${pageContext.request.contextPath}/member/alarm.do">회원 알림</a><br>		
									<a href="${pageContext.request.contextPath}/member/likelist.do">회원 찜 목록</a><br>
									<a href="${pageContext.request.contextPath}/member/delete.do">회원 탈퇴</a>		
								</li>
							</ul>
						</li>
					</ul>
					<ul>
						<li class="outer-main"><span class="mypage-title">쇼핑</span>
							<ul>
								<li class="inner-menu">
									<a href="${pageContext.request.contextPath}/member/buylist.do">구매 내역 조회</a><br>
									<a href="${pageContext.request.contextPath}/member/orderlist.do">주문/배송 목록</a>
								</li>
							</ul>
						</li>
					</ul>
					<ul>
						<li class="outer-main"><span class="mypage-title">활동내역</span>
							<ul>
								<li class="inner-menu">
									<a href="${pageContext.request.contextPath}/member/post.do">게시글 내역</a><br>
									<a href="${pageContext.request.contextPath}/member/comment.do">댓글 내역</a>
								</li>
							</ul>
						</li>
					</ul>
					<ul>
						<li class="outer-main"><span class="mypage-title">QnA</span>
							<ul>
								<li class="inner-menu">
									<a href="${pageContext.request.contextPath}/qna/qnaWriteForm.do">1:1문의</a><br>
									<a href="${pageContext.request.contextPath}/member/bookrequest.do">도서 신청</a>
								</li>
							</ul>
						</li>
					</ul>
				</div>
			</div>
		</div>
</body>
</html>