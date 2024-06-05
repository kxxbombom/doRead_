<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>도서 정보 수정</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/kbm.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/kyj.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header_test.css"
	type="text/css">
<script type="text/javascript"src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>
<script type="text/javascript">
	window.onload = function(){
		const inputcheck = document.getElementsByClassName('inputcheck');
		const form = document.getElementById('bookupdate_form');
		
		form.onsubmit = function(){
			for(let i=0; i<inputcheck.length; i++){
			const label = document.querySelector('label[for="'+inputcheck[i].id+'"]');
			
			if(inputcheck[i].value.trim()==''){
				alert(label.textContent+' 필수 입력');
				inputcheck[i].value='';
				inputcheck[i].focus();
				return false;
				}
			
			if(inputcheck[i].id=='publish_date'){
				if(!/^[2-9][0-9]{3}[-][0-9]{2}[-][0-9]{2}$/.test(inputcheck[i].value)){
					alert('출판일은 0000-00-00 형식으로 입력하세요');
					inputcheck[i].focus();
					return false;
				}	
				
			}
			
		
		}//end of for
		
	});
};

</script>
</head>
<body>
	<br>
	<br>
	<jsp:include page="/WEB-INF/views/adminster/adminheader3.jsp" />
	<br>
	<br>
	<jsp:include page="/WEB-INF/views/adminster/adminheader.jsp" />
	<div class="page-main">

		<h2>도서 정보 수정</h2>

		<hr size="1.5" width="80%" noshade="noshade">
		<form action="updateBook.do" method="post" id="bookupdate_form">
			<input type="hidden" name="book_num" value="${book.book_num}">
			<div class="content-main">
				<ul>
					<li><label for="book_auth">판매여부</label>
					<select name="book_auth" id="book_auth" class="inputcheck input-style" style="margin:4px 0; padding:0;">
							<option value="0">판매</option>
							<option value="1">미판매</option>
					</select></li>
					<li>
					<label for="book_name">책 이름</label>
					<input type="text" name="book_name" class="inputcheck input-style" id="book_name"
						maxlength="30" value="${book.book_name}" style="margin:4px 0;">
						</li>
					<li>
					<label for="author">책 저자</label>
					<input type="text" name="author" class="inputcheck input-style" id="author"
						maxlength="10" value="${book.author}" style="margin:4px 0;">
						</li>
					<li>
					<label for="publisher">출판사</label>
					<input type="text" name="publisher" class="inputcheck input-style" id="publisher"
						maxlength="10" value="${book.publisher}" style="margin:4px 0;">
						</li>
					<li>
					<label for="publish_date">출판일</label>
					<input type="text" name="publish_date" class="inputcheck input-style" id="publish_date"
						maxlength="10" value="${book.publish_date}" style="margin:4px 0;">
					</li>
					<li>
					<label for="price">가격</label>
					<input type="number" name="price" class="inputcheck input-style" id="price"
						maxlength="8" value="${book.price}" style="margin:4px 0;">
						</li>
					<li><label for="stock">재고</label>
					<input type="number" name="stock" class="inputcheck input-style" id="stock"
						maxlength="8" value="${book.stock}" style="margin:4px 0;">
					</li>
					<li>
					<label for="book_category">책카테고리</label>
					<select name="book_category" id="book_category" class="inputcheck input-style" style="margin:4px 0; padding:0;">
							<option value="1">소설</option>
							<option value="2">시/에세이</option>
							<option value="3">인문</option>
							<option value="4">정치/사회</option>
							<option value="5">과학/공학</option>
							<option value="6">IT/프로그래밍</option>
							<option value="7">자기계발</option>
							<option value="8">외국어</option>
							<option value="9">수험서/취업</option>
							<option value="10">만화</option>
							<option value="11">취미/스포츠</option>
					</select>
					</li>
				</ul>
			</div>
			<div class="align-center">
				<input type="button" value="도서 이미지 변경" class="update-button" onclick="location.href='${pageContext.request.contextPath}/book/updateBookImageForm.do?book_num=${book.book_num}'" style="width:140px;">
				<input type="submit" value="수정" class="update-button">
				<input type="button" value="메인" class="update-button" onclick="location.href='${pageContext.request.contextPath}/main/main.do'">
			</div>

		</form>

	</div>
</body>
</html>