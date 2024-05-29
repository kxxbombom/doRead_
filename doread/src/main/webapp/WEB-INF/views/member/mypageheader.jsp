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
						<input type="button" value="����"
						                     id="photo_btn">
					</div>
					<div id="photo_choice" style="display:none;">
						<input type="file" id="photo"
						          accept="image/gif,image/png,image/jpeg">
						<input type="button" value="����" id="photo_submit">
						<input type="button" value="���" id="photo_reset">          
					</div>
			</li>
			<li style="padding-top: 40px; font-size: 19pt; text-align: left;">${user_id}��<br>�������
			</li>
			<li>
				<a>ȸ������</a>
				<div class="myul">
					<ul>
						<li><a href="${pageContext.request.contextPath}/member/memberdetail.do">ȸ�� �� ����</a></li>
						<li><a href="${pageContext.request.contextPath}/member/membernotice.do">ȸ�� �˸�</a></li>
						<li><a href="${pageContext.request.contextPath}/book/bookfav.do">ȸ�� �� ���</a></li>
						<li><a href="${pageContext.request.contextPath}/member/deleteUserForm.do">ȸ�� Ż��</a></li>
					</ul>
				</div></li>
			<li>
				<a>����</a>
				<div class="myul">
					<ul>
						<li><a href="${pageContext.request.contextPath}/shopping/buylist.do">���� ���� ��ȸ</a></li>
						<li><a href="${pageContext.request.contextPath}">�ֹ�/��� ���</a></li>
					</ul>
				</div></li>

			<li>
				<a>Ȱ������</a>
				<div class="myul">
						<ul>
							<li><a href="${pageContext.request.contextPath}/post/postlist.do">�Խñ� ����</a></li>
							<li><a href="${pageContext.request.contextPath}/member/comment.do">��� ����</a></li>
						</ul>
				</div>
			<li>
				<a>QnA</a>
				<div class="myul">
					<ul>
						<li><a href="${pageContext.request.contextPath}/qna/qnaWriteForm.do">1:1����/������û</a></li>
					</ul>
				</div></li>
		</ul>
	</div>
</div>