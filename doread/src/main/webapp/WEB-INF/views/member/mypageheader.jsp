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
						<li class="page-name">${user_id}��<br>�������
						</li>
					</ul>
					<ul>
						<li class="outer-main"><span class="mypage-title">ȸ������</span>
							<ul>
								<li class="inner-menu">
									<a href="${pageContext.request.contextPath}/member/detail.do">ȸ�� �� ����</a><br>
									<a href="${pageContext.request.contextPath}/member/alarm.do">ȸ�� �˸�</a><br>		
									<a href="${pageContext.request.contextPath}/member/likelist.do">ȸ�� �� ���</a><br>
									<a href="${pageContext.request.contextPath}/member/delete.do">ȸ�� Ż��</a>		
								</li>
							</ul>
						</li>
					</ul>
					<ul>
						<li class="outer-main"><span class="mypage-title">����</span>
							<ul>
								<li class="inner-menu">
									<a href="${pageContext.request.contextPath}/member/buylist.do">���� ���� ��ȸ</a><br>
									<a href="${pageContext.request.contextPath}/member/orderlist.do">�ֹ�/��� ���</a>
								</li>
							</ul>
						</li>
					</ul>
					<ul>
						<li class="outer-main"><span class="mypage-title">Ȱ������</span>
							<ul>
								<li class="inner-menu">
									<a href="${pageContext.request.contextPath}/member/post.do">�Խñ� ����</a><br>
									<a href="${pageContext.request.contextPath}/member/comment.do">��� ����</a>
								</li>
							</ul>
						</li>
					</ul>
					<ul>
						<li class="outer-main"><span class="mypage-title">QnA</span>
							<ul>
								<li class="inner-menu">
									<a href="${pageContext.request.contextPath}/qna/qnaWriteForm.do">1:1����</a><br>
									<a href="${pageContext.request.contextPath}/member/bookrequest.do">���� ��û</a>
								</li>
							</ul>
						</li>
					</ul>
				</div>
			</div>
		</div>
</body>
</html>