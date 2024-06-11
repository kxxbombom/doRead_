<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>도서 정보</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header_test.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/kyj.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/book.fav.js"></script>


</head>
<body>
<div class="page-main">
	<jsp:include page="/WEB-INF/views/news/test_header.jsp"/>
	<div class="float-clear">

	</div>
	<div class="content-main">
	<c:if test="${book.book_auth !=0}">
		<div style="opacity:0.4;">
		<div class="book-image">
			<ul>
				<li>
				
				<img src="${pageContext.request.contextPath}/upload/${book.book_img}" width="400">
						
				
				</li>
			</ul>
		</div>
		</div>
	</c:if>
	</div>
	
		<div class="book-detail">
		
				<c:if test="${book.book_auth != 0}">
				<h2   style="readonly:readonly z-index:999; ">현재 구매할 수 없는 도서입니다</h2>
				<div style="opacity:0.4;">
				<input type="hidden" name="book_num" value="${book.book_num}" id="book_num">
				<input type="hidden" name="book_price" value="${book.price}" id="book_price">
				<input type="hidden" name="book_stock" value="${book.stock}" id="book_stock">
				<ul>
					<li id="book-tagtop">
						<div class="category-tag">
							<b>
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
							<c:if test="${book.book_category==12}">e-book</c:if>
							</b>
						</div>
						<br>
							<img id="bookfav" src="${pageContext.request.contextPath}/images/bookfav_before.png" width="20px" data-num="${book.book_num}">
					</li>
					<li><h1>${book.book_name}</h1></li>
					<li>${book.author} / ${book.publisher}</li>
					<li>${book.publish_date}</li>
					<li><h3><b><fmt:formatNumber value="${book.price}"/>원</b> </h3></li>
						<li id="button-margin">
							<input type="submit" value="현재 구매할 수 없는 도서입니다" disabled id="order" class="order-button">
						</li>

					</ul>
					</div>
					
					</c:if>
				
		<c:if test="${book.book_auth == 0}">
		<div class="content-main">
		<div class="book-image">
			<ul>
				<li>
				
				<img src="${pageContext.request.contextPath}/upload/${book.book_img}" width="400">
						
				
				</li>
			</ul>
		</div>
		</div>
			<form id="book_cart" action="${pageContext.request.contextPath}/order/orderOneForm.do" method="post">
				<input type="hidden" name="book_num" value="${book.book_num}" id="book_num">
				<input type="hidden" name="book_price" value="${book.price}" id="book_price">
				<input type="hidden" name="book_stock" value="${book.stock}" id="book_stock">
				<ul>
					<li id="book-tagtop">
						<div class="category-tag">
							<b>
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
							<c:if test="${book.book_category==12}">e-book</c:if>
							</b>
						</div>
						<br>
							<img id="bookfav" src="${pageContext.request.contextPath}/images/bookfav_before.png" width="20px" data-num="${book.book_num}">
					</li>
					<li><h1>${book.book_name}</h1></li>
					<li>${book.author} / ${book.publisher}</li>
					<li>${book.publish_date}</li>
					<li><h3><b><fmt:formatNumber value="${book.price}"/>원</b> / 재고: <span><fmt:formatNumber value="${book.stock}"/>권</span></h3></li>
					<c:if test="${book.stock>0}">
						<li>
							<label for="book_quantity" id="quantity_label">구매수량</label>
							<input type="number" name="book_quantity" id="book_quantity" min="1" max="${book.stock}">
						</li>
						<c:if test="${book.book_category!=12}">
						<li id="button-margin">
							<input type="submit" value="바로구매" id="order" class="order-button">
							<input type="button" value="장바구니" id="insert_cart" class="order-button">
						</li>
						</c:if>
						<c:if test="${book.book_category==12}">
						<li id="button-margin">
							<input type="submit" value="바로구매" id="order" class="order-button">
						</li>
						</c:if>
						<script type="text/javascript">
							$(function(){
								$('#book_quantity').on('keyup',function(){
									if($('#book_quantity').val() == ''){
										alert('수량은 1개 이상 선택');
										$('#book_quantity').focus();
										return;
									}
									if($('#book_quantity').val() <= 0){
										alert('수량은 1개 이상 선택');
										$('#book_quantity').val('');
										return;
									}
									if(Number(${book.stock}) < $('#book_quantity').val()){
										alert('수량이 부족합니다!');
										$('#book_quantity').val('');
										return;
									}
								});
								
								<%--구매버튼--%>
								$('#book_cart').submit(function(){
									if($('#book_quantity').val().trim() == ''){
										alert('수량을 입력하세요');
										$('#book_quantity').val('').focus();
										return false;
									}
								});
								
								<%--장바구니담기--%>
								$('#insert_cart').click(function(){
									if($('#book_quantity').val() == ''){
										alert('수량을 입력하세요');
										$('#book_quantity').focus();
										return;
									}
									$.ajax({
										url:'../cart/insertCart.do',
										type:'post',
										data:{book_num:$('#book_num').val(),book_price:$('#book_price').val(),book_stock:$('#book_stock').val(),book_quantity:$('#book_quantity').val()},
										dataType:'json',
										success:function(param){
											if(param.result == 'logout'){
												alert('로그인 후 사용하세요');
											}else if(param.result == 'success'){
												let choice = confirm('장바구니에 상품을 담았습니다.\n장바구니로 이동하시겠습니까?');
												if(choice){
													location.href='../cart/list.do';
												}

											}else if(param.result == 'overquantity'){
												alert('기존에 주문한 상품입니다. 개수를 추가하면 재고가 부족합니다.');
											}else{
												alert('장바구니 담기 오류');
											}
										},
										error:function(){
											alert('네트워크 오류 발생');
										}
									
									});
								});
								
							});	
						
						</script>
						
					</c:if>
				</ul>
			</form>
		
			</c:if>
		</div>
	</div>
	<div class="float-clear">
			<br>
			<hr width="100%" size="1" noshade="noshade">
	</div>
	<div class="content-main">
		<div class="info-tab">
			<a href="#book_review"><b>리뷰</b></a>
			<a href="#change_info"><b>교환/반품/품절 안내</b></a>
		</div>
	<div class="float-clear">
		<hr width="100%" size="1" noshade="noshade">
	</div>
		<ul class="book-info">
			<li id="book_review">
				<div class="info-title">
				<h3>이 책의 리뷰</h3>
				</div>
				<c:if test="${s_count==0}">
					<div class="align-center">
						<h4>표시할 리뷰가 없습니다.</h4>
					</div>
				</c:if>
				<c:if test="${s_count!=0}">
					<table>
						<tr>
							<th>글 번호</th>
							<th>제목</th>
							<th>작성일</th>
							<th>조회</th>
						</tr>
						<c:forEach var="story" items="${story}">
						<tr>	
							<td>${story.s_num}</td>
							<td><a href="${pageContext.request.contextPath}/story/storyDetail.do?s_num=${story.s_num}">${story.s_title}</a></td>
							<td>${story.s_rdate}</td>
							<td>${story.s_hit}</td>
						</tr>
						</c:forEach>
					</table>
				</c:if>
			</li>
			<li id="change_info">
				<c:if test="${book.book_category==12}">
					<div class="info-title">
						<h3>E-BOOK 안내</h3>
					</div>
					<p>e-book은 구매 후 바로 배송완료단계로 설정되며, 마이페이지 e-book에서 읽으실 수 있습니다.
					<br>
					<p>
					<br>
				</c:if>
				<div class="info-title">
					<h3>교환/반품/품절 안내</h3>
				</div>
				<input type="button" value="교환/반품 신청" onclick="location.href='${pageContext.request.contextPath}/qna/qnaWriteForm.do'">
				<p><b>반품/교환 방법</b></p>
				<p>
				QnA게시판의 [교환 및 반품] 유형으로 반품 및 교환 신청 작성<br>
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
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>