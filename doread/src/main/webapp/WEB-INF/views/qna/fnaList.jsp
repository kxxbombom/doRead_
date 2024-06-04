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
<script type="text/javascript">
window.onload= function(){
	const myForm = document.getElementById('search_form');
	//이벤트 연결
	myForm.onsubmit=function(){
		const keyword = document.getElementById('keyword');
		if(keyword.value.trim()==''){
			alert('검색어를 입력하세요');
			keyword.value='';
			keyword.focus();
			return false;
		}
	};
};
</script>
</head>
<body>
<div class="page-main">
	<jsp:include page="/WEB-INF/views/news/test_header.jsp"/>
	<div class="content-main">
		<h2 style="text-align:center;">FAQ 목록</h2>
	</div>
		<hr size="1.5" width="80%" noshade="noshade">
		<table>
			<tr>
			<td>
			 	<h4>Q[주문]비회원도 주문할 수 있나요?</h4>
			 	<div>
			 	아닙니다. DoRead는 비회원으로도 주문하실 수 없습니다. 주문을 하시려면 회원가입 후 이용해주세요.
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
			<td>
				<h4> Q[적립]적립금은 얼마 적립되는건가요?</h4>
			 	<div>
			 	
				적립금은 구매금액의 0.3%적립되며, 배송비와 적립금은 사용금액은 제외됩니다.<br>
				적립금은상품 주문 시 적립 예정 포인트가 자동 합산되고 주문하신 상품이 발송완료 된 후에 자동으로 적립됩니다.
			 	<br>
			 	</div>
			
			</td>
			</tr>
			<tr>
			<td>
				<h4> Q[적립]적립금은 얼마 적립되는건가요?</h4>
			 	<div>
			 	
				적립금은 구매금액의 0.3%적립되며, 배송비와 적립금은 사용금액은 제외됩니다.<br>
				적립금은상품 주문 시 적립 예정 포인트가 자동 합산되고 주문하신 상품이 발송완료 된 후에 자동으로 적립됩니다.
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
		
			
		
	</div>
</body>
</html>