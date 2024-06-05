<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관심도서 등록</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/kts.css" type="text/css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/header_test.css"
	type="text/css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/kbm.css" type="text/css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>
<script type="text/javascript">
	$(function() {
		$('input[type="checkbox"]').on('click', function() {
			let count = $('input:checked[type="checkbox"]').length;

			if (count > 3) {
				this.checked = false;
				alert("관심도서는 3개까지 선택할 수 있습니다.");
			}
		});
	});
</script>
</head>
<body>
	<div class="page-main">
		<jsp:include page="/WEB-INF/views/member/mypageheader.jsp" />
		<div class="content-main register-header">
			<h2>회원정보 수정</h2>
			<form id="modify_form" action="updateCategory.do" method="post">
				<div class="register-main">
					<div class="container">
						<div style="margin-bottom:10px;">
							<h3><label for="preference">관심도서</label></h3>
							<p style="color:rgb(26, 77, 46);">*관심도서는 3개까지 선택할 수 있습니다</p>
						</div>
						<div class="preference-box">
							<div class="preference-group">
								<div class="test">
									<input type="checkbox" name="preference" value="1"
										id="preference1"> <label for="preference1">소설</label>
								</div>
								<div class="test">
									<input type="checkbox" name="preference" value="2"
										id="preference2"> <label for="preference2">시/에세이</label>
								</div>
								<div class="test">
									<input type="checkbox" name="preference" value="3"
										id="preference3"> <label for="preference3">인문</label>
								</div>
								<div class="test">
									<input type="checkbox" name="preference" value="4"
										id="preference4"> <label for="preference4">정치/사회</label>
								</div>
							</div>
							<div class="preference-group">
								<div class="test">
									<input type="checkbox" name="preference" value="5"
										id="preference5"> <label for="preference5">과학/공학</label>
								</div>
								<div class="test">
									<input type="checkbox" name="preference" value="6"
										id="preference6"> <label for="preference6">IT/프로그래밍</label>
								</div>
								<div class="test">
									<input type="checkbox" name="preference" value="7"
										id="preference7"> <label for="preference7">자기계발</label>
								</div>
								<div class="test">
									<input type="checkbox" name="preference" value="8"
										id="preference8"> <label for="preference8">외국어</label>
								</div>
							</div>
							<div class="preference-group">
								<div class="test">
									<input type="checkbox" name="preference" value="9"
										id="preference9"> <label for="preference9">수험서/취업</label>
								</div>
								<div class="test">
									<input type="checkbox" name="preference" value="10"
										id="preference10"> <label for="preference10">만화</label>
								</div>
								<div class="test">
									<input type="checkbox" name="preference" value="11"
										id="preference11"> <label for="preference11">취미/스포츠</label>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="register-button">
					<input type="submit" value="카테고리 정보 수정" style="width: 200px;">
					<input type="button" value="마이페이지"
						onclick="location.href='${pageContext.request.contextPath}/member/mypage.do'">
				</div>
			</form>
		</div>
	</div>
</body>
</html>