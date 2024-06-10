<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자주묻는 질문</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header_test.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/kbm.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>

<script type="text/javascript">
$(function(){
	$('#pbtn').click(function(){
		$('#adddiv').removeClass('hide');
		$('#addbtn').val("마지막페이지");
	});
	$('#dbtn').click(function(){
		$('#adddiv').removeClass('hide');
		$('#addbtn').val("마지막페이지");
	});
	$('#addbtn').click(function(){
		$('#adddiv').removeClass('hide');
		$('#addbtn').val("마지막페이지");
	});
});
</script>
</head>
<body>
<div class="page-main" id="up">
	<jsp:include page="/WEB-INF/views/news/test_header.jsp"/>
	<div class="content-main">
		<h2 style="text-align:center;" >FAQ 목록</h2>
	</div>
		<hr size="1.5" width="80%" noshade="noshade">
		<div class="align-center">
			<a href="#orderfaq">[주문]</a> <a href="#pointfaq" id="pointbtn">[적립]</a> <a href="#deliveryfaq" id="dbtn">[배송]</a> <a href="#pfaq" id="pbtn">[교환/반품]</a>
		</div> 
		<div class="list-space align-right list-btn2">
			<input type="button" value="내QnA목록" onclick="location.href='qnaList.do'">
			<input type="button" value="문의하기" onclick="location.href='qnaWriteForm.do'">
		</div>
		<table>
			<tr>
			<td id="orderfaq">
			 	<h4>Q[주문]비회원도 주문할 수 있나요?</h4>
			 	<div >
			 	아닙니다. DoRead는 회원제서비스이며 비회원으로는 주문하실 수 없습니다. 주문을 하시려면 회원가입 후 이용해주세요.
			 	</div>
			</td>
			</tr>
			<tr>
			<td>
				<h4> Q[주문]배송지 주소를 변경하고 싶어요.</h4>
			 	<div>
			 	
				주문하신 상품이 배송준비중전 일 경우, 직접 배송지 주소를 변경하실 수 있습니다.<br>
				
				<img src="${pageContext.request.contextPath}/images/faqimage.png" width="80%" ><br>
				마이페이지 > 주문/배송 목록 상세페이지에서 '배송지 수정' 버튼을 클릭하시면 배송지 주소를 변경하실 수 있습니다.<br>
			 	<br>
			 	</div>
			</td>
			</tr>
			<tr>
			<td id="pointfaq">
				<h4> Q[적립]적립금은 얼마 적립되는건가요?</h4>
			 	<div>
			 	
				적립금은 구매금액의 3% 적립되며, 배송비와 적립금은 사용금액은 제외됩니다.<br>
				적립금은상품 주문 시 적립 예정 포인트가 자동 적립됩니다.
			 	<br>
			 	</div>
			
			</td>
			</tr>
			<tr>
			<td>
				<h4> Q[적립]적립금은 어떻게 사용하나요?</h4>
			 	<div>
			 	
				 보유한 적립금액만큼 상품 구매시 사용할 수 있습니다.
			 	<br>
			 	</div>
			</td>
			</tr>
		</table>
		<div class="hide divv" id="adddiv">
			<table>
			<tr>
			<td id="deliveryfaq">
			 	<h4>Q[배송]배송료는 얼마인가요?</h4>
			 	<div>
			 	도서 1만5천원 이상 구매시 무료배송이며,<br>
				도서 1만5천원 미만 구매시 배송비 2,500원 부과됩니다.
			 	</div>
			</td>
			</tr>
			<tr>
			<td>
				<h4> Q[배송]주문 상품이 언제 출고 되는지 알 수 있나요?</h4>
			 	<div>
				각 상품별로 출고예정일이 상이하며, 각상품별 출고예정일은 QNA게시판 문의해주세요. <br>
				출고예정일이란, 주문상품의 결제(무통장입금의 경우 입금확인)가 확인된 날 기준으로 상품을 준비하여 상품 포장 후<br> 물류센터에서 택배사로 전달하게 되는 예상 일자 입니다.<br>
				배송일정은 예상출고일에 택배사의 배송일(약 2일)이 더해진 날이며 연휴 및 토.일,공휴일을 제외한 근무일 기준입니다. <br>
				다양한 상품을 함께 주문하실 경우 가장 늦은 출고일정에 맞춰 함께 배송됩니다.<br>
				*출고예정일이 5일 이상인 상품의 경우, 출판사/유통사 사정으로 품/절판 되어 구입이 어려울 수 있습니다.<br> 이 경우 SMS,메일로 알려드립니다. 고객님께서 급하게 필요하신 상품은 별도로 주문하면 받으시는 시간이 절약 됩니다.<br> 
				*온라인 입금의 경우 입금 확인이 완료된 시점부터 적용되므로 주문일과 관계없이 예상출고일은 결제완료시점을 기준으로 다시 계산됩니다. <br>
				*5~7일 이상이 소요되는 상품(해외개인주문도서 포함)의 경우 거래처 사정에 따라 품절 또는 절판될 수 있습니다.<br> 준비가 되지 않는 경우에는 메일(또는 전화)로 별도 연락 드리겠습니다. <br>
				*외국도서(서양도서, 일본도서)의 경우 한정된 수량이 수입되고, 실재고 DB가 Off-Line과 연결되어 있으므로<br> 상품의 유통상 주문 수량이 재고 수량보다 많은 경우 품절 될 수 있음을 알려드립니다.<br> 
				*외국도서중 국내 출판사를 통해 위탁 판매되고 있는 일부 [수입교재]의 경우<br> 출판사 사정에 의해 출고예정일보다 다소 지연될 수 있음을 사전에 양해 부탁드립니다.<br>
			 	<br>
			 	</div>
			</td>
			</tr>
			<tr>
			<td id="pfaq">
				<h4> Q[교환/반품]주문한 도서의 반품이 가능한가요?</h4>
			 	<div>
			 	
				1.고객님의 변심 또는 주문오류로 인한 반품신청일때, 타 도서로의 교환이 불가하며,<br>
				DoRead에서 주문하신 상품의 경우, 반품 교환이 가능한 종류에 한해 상품을 수령하신 날로부터 7일 이내 반품신청이 가능합니다.<br>	
				반송료는 고객님께서 부담하시게 됩니다. <br>
				단, 반품하실 도서는 사용하지 않고, 상품 자체의 비닐 래핑이 되어있는 책은 비닐 래핑은 벗기지 않은 상태일 때 가능합니다.<br>
				2. 주문하신 상품의 결함 및 계약내용과 다를 경우, 문제점 발견 후 30일 이내 반품신청이 가능합니다. <br>
				3. 반품 보내기 전 다음과 같이 반품 상품을 준비해주십시오. <br>
				도서와 함께 받으신 "거래명세서" 뒷 편의 반품신청서 양식을 모두 작성하여 도서와 함께 택배 밀봉포장을 준비하여 주십시오. <br>
				혹시 반품신청서가 없으시면 메모지에 아래의 내용을 기입하여 주십시오.<br>
			 	</div>
			
			</td>
			</tr>
		</table>
		</div>	
		<div class="align-center list-btn3">
			<input type="button"  class="button2" value=" 더보기" id="addbtn"> &nbsp;<input type="button" class="button2" value="위로가기" onclick="location.href='#up'">
		</div> 
		
		
	</div>
</body>
</html>