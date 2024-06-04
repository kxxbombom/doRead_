<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="mini_nav">
	<ul>
		<c:if test="${empty user_num}">
		<li>
			<a href="${pageContext.request.contextPath}/member/registerUserForm.do" class="top-nav">회원가입</a>
		</li>
		<li>
			<a href="${pageContext.request.contextPath}/member/loginForm.do" class="top-nav" >로그인</a>
		</li>
		<li>
			<a href="${pageContext.request.contextPath}/member/mypage.do" class="top-nav" >마이페이지</a>
		</li>
		</c:if>
		
		<c:if test="${!empty user_num && user_auth != 9}">
		<li class="menu-logout">
			[<span>${user_id}</span>]
			<a href="${pageContext.request.contextPath}/member/logout.do" class="top-nav">로그아웃</a>
		</li>
		<li>
			<a href="${pageContext.request.contextPath}/member/mypage.do" class="top-nav" >마이페이지</a>
		</li>
		</c:if>
		
		<c:if test="${!empty user_num && user_auth == 9}">
		<li class="menu-logout">
			[<span>${user_id}</span>]
			<a href="${pageContext.request.contextPath}/member/logout.do" class="top-nav">로그아웃</a>
		</li>
		<li>
			<a href="${pageContext.request.contextPath}/adminster/adminPage.do" class="top-nav">관리자페이지</a>
		</li>
		</c:if>
		
	</ul>
</div>
<div id="main_logo">
	<ul>
		<li>
			<a href="${pageContext.request.contextPath}/main/main.do" class="top-nav" id="doread"><img src="../images/pngwing.com.png" width="40px">Do Read</a>

		</li>
		<c:if test="${user_auth == 2 }">
		<li id="menu-cart">
			<img src="${pageContext.request.contextPath}/images/cart.png" class="click-image" width="30" 
					height="30" onclick="location.href='${pageContext.request.contextPath}/cart/list.do'">
		</li>
		<li id="menu-mypage">
			<img src="${pageContext.request.contextPath}/images/mypage.png" class="click-image" width="30" 
					height="30" onclick="location.href='${pageContext.request.contextPath}/member/mypage.do'">
		</li>
		</c:if>
		<c:if test="${user_auth == 9 }">
		<li id="menu-cart">
			<img src="${pageContext.request.contextPath}/images/cart.png" class="click-image" width="30" 
					height="30">
		</li>
		<li id="menu-mypage">
			<img src="${pageContext.request.contextPath}/images/mypage.png" class="click-image" width="30" 
					height="30">
		</li>
		</c:if>
		
	</ul>
</div>
<div class="main-nav-container">
	<ul id="main_nav">
		<li>
		<div id="container">
        <input type="checkbox" id="toggle" hidden>
        <label for="toggle" id="label">도서</label>
        		<ul id="menu">
           		 	<li class="menu-item"><a href="${pageContext.request.contextPath}/book/list.do">도서 전체</a></li>
          			<li class="menu-item"><a href="${pageContext.request.contextPath}/book/listCategory.do?book_category=1">소설</a></li>
            		<li class="menu-item"><a href="${pageContext.request.contextPath}/book/listCategory.do?book_category=2">시/에세이</a></li>
           			<li class="menu-item"><a href="${pageContext.request.contextPath}/book/listCategory.do?book_category=3">인문</a></li>
           			<li class="menu-item"><a href="${pageContext.request.contextPath}/book/listCategory.do?book_category=4">정치/사회</a></li>
           			<li class="menu-item"><a href="${pageContext.request.contextPath}/book/listCategory.do?book_category=5">과학/공학</a></li>
           			<li class="menu-item"><a href="${pageContext.request.contextPath}/book/listCategory.do?book_category=6">IT/프로그래밍</a></li>
           			<li class="menu-item"><a href="${pageContext.request.contextPath}/book/listCategory.do?book_category=7">자기계발</a></li>
           			<li class="menu-item"><a href="${pageContext.request.contextPath}/book/listCategory.do?book_category=8">외국어</a></li>
           			<li class="menu-item"><a href="${pageContext.request.contextPath}/book/listCategory.do?book_category=9">수험서/취업</a></li>
           			<li class="menu-item"><a href="${pageContext.request.contextPath}/book/listCategory.do?book_category=10">만화</a></li>
           			<li class="menu-item"><a href="${pageContext.request.contextPath}/book/listCategory.do?book_category=11">취미/스포츠</a></li>
       			</ul>
   		 	</div>
		</li>
		<li>
			<a href="${pageContext.request.contextPath}/news/newsList.do" class="nav">뉴스</a>
		</li>
		<li>
			<a href="${pageContext.request.contextPath}/event/eventmain.do " class="nav">이벤트</a>
		</li>
		<li>
			<a href="${pageContext.request.contextPath}/used/usedList.do" class="nav" >중고</a>
		</li>
		<li>
			<a href="${pageContext.request.contextPath}/story/storyBoardList.do" class="nav">스토리</a>
		</li>
		<li>
			<a href="${pageContext.request.contextPath}/qna/qnaList.do" class="nav">QnA</a>
		</li>
	</ul>
</div>

<div id="mypage-main">
	<div class="mypage-div">
		<ul>
			<li id="myphoto"><c:if test="${empty member.mem_photo}">
					<img src="${pageContext.request.contextPath}/images/face.png"
						width="100" height="100" class="my-photo">
				</c:if> <c:if test="${!empty member.mem_photo}">
					<img
						src="${pageContext.request.contextPath}/upload/${member.mem_photo}"
						width="100" height="100" class="my-photo">
				</c:if>
				
				<button type="button" id="photo_btn">
					<img id="penimage2"
						src="${pageContext.request.contextPath}/images/pencil_1361912.png"
						width="31" height="31">
				</button>
				<div id="photo_choice" style="display: none;">
					<input type="file" id="photo"
						accept="image/gif,image/png,image/jpeg"> <input
						type="button" value="전송" id="photo_submit"> <input
						type="button" value="취소" id="photo_reset">
				</div></li>
			<li
				style="padding-top: 60px; font-size: 19pt; color: rgb(255, 255, 255) !important; text-align: left;">${user_id}님<br>어서오세요
			</li>
			<li><label>회원정보</label>
				<div class="myul">
					<ul>
						<li><a
							href="${pageContext.request.contextPath}/member/memberdetail.do">회원
								상세 내용</a></li>
						<li><a
							href="${pageContext.request.contextPath}/book/bookFavList.do">회원
								찜 목록</a></li>
						<li><a
							href="${pageContext.request.contextPath}/member/deleteUserForm.do">회원
								탈퇴</a></li>
					</ul>
				</div></li>
			<li><label>쇼핑</label>
				<div class="myul">
					<ul>
						<li><a
							href="${pageContext.request.contextPath}/shopping/buylist.do">주문/배송
								목록</a></li>
						<li>
							<a
							href="${pageContext.request.contextPath}/shopping/mileagelist.do">마일리지 내역
								목록</a>
						</li>
					</ul>
				</div></li>

			<li><label>활동내역</label>
				<div class="myul">
					<ul>
						<li><a
							href="${pageContext.request.contextPath}/post/myPostList.do">내가
								쓴 게시글 내역</a></li>
						<li><a
							href="${pageContext.request.contextPath}/post/myCommentList.do">내가
								쓴 댓글 내역</a></li>
						<li><a
							href="${pageContext.request.contextPath}/event/participateEventList.do">이벤트 참여 내역</a></li>		
					</ul>
				</div>
			<li><label>QnA</label>
				<div class="myul">
					<ul>
						<li><a
							href="${pageContext.request.contextPath}/qna/qnaWriteForm.do">1:1문의/도서신청</a></li>
					</ul>
				</div></li>
		</ul>
	</div>
</div>