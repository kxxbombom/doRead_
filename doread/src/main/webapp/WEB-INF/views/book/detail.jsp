<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>도서 정보</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/ysw.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/kyj.css" type="text/css">
</head>
<body>
<div class="page-main">
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<div class="float-clear">
		<br>
		<hr width="100%" size="1" noshade="noshade">
	</div>
	<div class="content-main">
		<div class="book-image">
			<ul>
				<li><img src="${pageContext.request.contextPath}/upload/${book.book_img}" width="400"></li>
			</ul>
		</div>
		<div class="book-detail">
			<form id="book_cart">
				<input type="hidden" name="book_num" value="${book.book_num}" id="book_num">
				<input type="hidden" name="book_price" value="${book.price}" id="book_price">
				<input type="hidden" name="book_stock" value="${book.stock}" id="book_stock">
				<ul>
					<li>
						<div class="category-tag">
							<c:if test="${book.book_category==1}">소설</c:if>
							<c:if test="${book.book_category==2}">시/에세이</c:if>
							<c:if test="${book.book_category==3}">인문</c:if>
							<c:if test="${book.book_category==4}">정치/사회</c:if>
							<c:if test="${book.book_category==5}">과학/공학</c:if>
							<c:if test="${book.book_category==6}">IT/프로그래밍</c:if>
							<c:if test="${book.book_category==7}">자기계발</c:if>
							<c:if test="${book.book_category==8}">외국어</c:if>
							<c:if test="${book.book_category==9}">수험서/취업</c:if>
							<c:if test="${book.book_category==10}">만화</c:if>
							<c:if test="${book.book_category==11}">취미/스포츠</c:if>
						</div>
					</li>
					<li><h1>${book.book_name}</h1></li>
					<li>${book.author} / ${book.publisher}</li>
					<li>${book.publish_date}</li>
					<li><h3><b><fmt:formatNumber value="${book.price}"/>원</b> / 재고: <span><fmt:formatNumber value="${book.stock}"/>권</span></h3></li>
					<c:if test="${book.stock>0}">
						<li>
							<label for=book_quantity id="quantity_label">구매수량</label>
							<input type="number" name="book_quantity" id="book_quantity" min="1" max="${book.stock}">
						</li>
						<li id="button-margin">
							<input type="button" value="찜목록에 추가" id="wish" class="wish-button">
						</li>
						<li>
							<input type="button" value="바로 구매" id="order" class="order-button">
							<input type="button" value="장바구니" id="insert_cart" class="order-button">
						</li>
					</c:if>
				</ul>
			</form>
		</div>
	</div>
	
	<div class="float-clear">
			<br>
			<hr width="100%" size="1" noshade="noshade">
	</div>
	<div class="content-main">
		<div class="info-tab">
			<a href="#book_review"><b>서평</b></a>
			<a href="#change_info"><b>교환/반품/품절 안내</b></a>
		</div>
	<div class="float-clear">
		<hr width="100%" size="1" noshade="noshade">
	</div>
		<ul>
			<li id="book_review">
				<h3>서평</h3>
				
				<hr width="80%" size="1" noshade="noshade">
			</li>
			<li id="change_info">
				<h3>교환/반품/품절 안내</h3>
				<hr width="80%" size="1" noshade="noshade">
				<input type="button" value="교환/반품 신청" onclick="location.href='${pageContext.request.contextPath}/qna/qnaWriteForm.do'">
				<p><b>반품/교환 방법</b></p>
				<p>
				QnA게시판의 [일반문의] 유형으로 반품 및 교환 신청 작성<br>
				반품 및 교환 신청 시 문의 내용에 신청 사유를 기재해주셔야 처리가 가능합니다.
				</p>
				<p><b>반품/교환 가능기간</b></p>
				<p>변심에 따른 반품 및 교환의 경우 구매 후 7일 이내,<br>상품 하자로 인한 반품 및 교환의 경우 문제점 발견 후 30일 이내로 가능합니다.</p>
				 <p><b>반품/교환 불가 사유</b></p>
				 <ol>
				 	<li>소비자의 책임 있는 사유로 상품이 훼손된 경우</li>
				 	<li>반품/교환 가능 기간이 도과한 경우</li>
				 	<li>전자상거래 등에서의 소비자보호에 관한 법률이 정하는 소비자 청약철회 제한 내용에 해당하는 경우</li>
				 	<li>세트 상품 중 일부만 반품 불가</li>
				 </ol>
				 <p><b>상품 품절 및 도서 신청</b></p>
				 <p>공급사의 재고 사정에 의해 도서가 품절될 수 있으며, 품절 도서 및 희망 도서 관련 문의는 QnA 게시판의 [도서신청]을 이용해주시기 바랍니다.</p>
			</li>
		</ul>
	</div>
</div>
</body>
</html>