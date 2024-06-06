<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주문/결제</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/kbm.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header_test.css" type="text/css">

<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/order.form.js"></script>
<script type="text/javascript">



function editShippingInfo() {
	
    const inputs = document.querySelectorAll('input.r_info');
    const spans = document.querySelectorAll('span.info-span');
    const completeButton = document.querySelector('input[type="button"][value="완료"]');
    const editButton = document.querySelector('button[onclick="editShippingInfo()"]');
	
    inputs.forEach(input => {
        input.classList.remove('hidden');
    });
    spans.forEach(span => {
        span.classList.add('hidden');
    });
    completeButton.classList.remove('hidden');
    editButton.classList.add('hidden');
}

function saveShippingInfo() {

    document.getElementById('displayName').textContent = document.getElementById('receive_name').value;
    document.getElementById('displayPhone').textContent = document.getElementById('receive_phone').value;
    document.getElementById('displayZipcode').textContent = document.getElementById('receive_zipcode').value;
    document.getElementById('displayAddress1').textContent = document.getElementById('receive_address1').value;
    document.getElementById('displayAddress2').textContent = document.getElementById('receive_address2').value;

    const inputs = document.querySelectorAll('input.r_info');
    const spans = document.querySelectorAll('span.info-span');
    const completeButton = document.querySelector('input[type="button"][value="완료"]');
    const editButton = document.querySelector('button[onclick="editShippingInfo()"]');

    inputs.forEach(input => {
        input.classList.add('hidden');
    });
    spans.forEach(span => {
        span.classList.remove('hidden');
    });
    completeButton.classList.add('hidden');
    editButton.classList.remove('hidden');
    
    return false;
}

$(function(){
	$('#pointbtn').click(function(){
		const span = document.getElementById('usedPoint');
		const usedpoint = document.getElementById('pointval');
		const total = document.getElementById('total');
		const order_usepoint = document.getElementById('order_usepoint');
		const inpoint = document.getElementById('inpoint');
		const all_total =document.getElementById('all_total');
		const all_total_delivery =document.getElementById('all_total_delivery');
		if(usedpoint.value != 0)
		span.textContent = parseInt(usedpoint.value).toLocaleString();
		else{
		span.textContent = 0;
		}
		if(usedpoint.value != null)
		total.textContent=all_total_delivery.value-usedpoint.value;
		else{
		total.textContent= all_total_delivery.value;
		}
		
		inpoint.textContent = Math.floor((all_total.value-usedpoint.value)*0.03);
		
	});
	$('#pointval').keyup(function(){
		if($(this).val()<0 || $(this).val()>Number($('#userpoint').val())){
			alert('소유 포인트만 사용가능');
			$(this).val('').focus();
			return ;
		}
		
		$('#pointbtn').click();
		
	})
	$('#order_form').submit(function(){
		const radio = document.querySelectorAll('input[class="payment"]:checked');
		
		if(radio.length<1){
			alert('결제수단을 선택하세요');
			return false;
		}	
		const inputcheck = document.getElementsByClassName('input-check');
		for(let i=0; i<inputcheck.length; i++){
			if(inputcheck[i].value.trim()==''){
				alert("배송지 정보를 입력해주세요");
				return false;
			}
		}
	})
	
	
	
	function toggleEnterPasswd(show) {
	    var enterPasswdInput = $('#enter_passwd');
	    if (show) {
	        enterPasswdInput.show();
	    } else {
	        enterPasswdInput.hide();
	    }
	}

	var isEnter = $('input[name="enter"]:checked').val();
    toggleEnterPasswd(isEnter == 1);

    $('input[name="enter"]').on('change', function() {
        toggleEnterPasswd($(this).val() == 1);
    });
    
    
    
    
    function togglePayment(show) {
	    var paymentInput = $('#pay_card');
	    if (show) {
	        paymentInput.show();
	    } else {
	        paymentInput.hide();
	    }
	}

	var isPay = $('input[name="payment"]:checked').val();
    togglePayment(isPay == 1);

    $('input[name="payment"]').on('change', function() {
        togglePayment($(this).val() == 1);
    });
    
    
    
    
});
</script>
</head>
<body>
<div class="page-main">
	<jsp:include page="/WEB-INF/views/news/test_header.jsp"/>
	<div class="content-main order-form slide-in">
		<h2>주문/결제</h2>
		<form id="order_form" action="order.do" method="post">
			<div class="cart-container">
			<div class="order-info">
				<div id="receive_info">
					<ul>
						<li>
							<label>배송지정보</label>
							<div id="receive_div">
								<ul>
									<li><input type="text" id="receive_name" name="receive_name" value="${member.mem_name}" class="hidden r_info input-check" placeholder="이름을 입력해주세요"><span id="displayName" class="info-span">${member.mem_name}</span></li>
									<li><input type="text" id="receive_phone" name="receive_phone" value="${member.mem_phone}" class="hidden r_info input-check" placeholder="휴대폰번호를 (-)없이 입력해주세요"><span id="displayPhone" class="info-span">${member.mem_phone}</span></li>
									<li><input type="text" id="receive_zipcode" name="receive_zipcode" value="${member.mem_zipcode}" class="hidden r_info input-check"> <input type="button" style="margin-left:5px;" class="hidden r_info" value="우편번호 찾기" onclick="execDaumPostcode()"><span id="displayZipcode" class="info-span">${member.mem_zipcode}</span></li>
									<li><input type="text" id="receive_address1" name="receive_address1" value="${member.mem_address1}" class="hidden r_info input-check"><input type="text" style="margin-left:5px;" id="receive_address2" name="receive_address2" value="${member.mem_address2}" class="hidden r_info"> <span id="displayAddress1" class="info-span">${member.mem_address1}</span>&nbsp;<span id="displayAddress2" class="info-span">${member.mem_address2}</span></li>

								</ul>
								<button type="button" onclick="editShippingInfo()" class="cartorder-btn">변경</button>
								<input type="button" value="완료" class="hidden cartorder-btn" id="info_confirm">
							</div>
						</li>
						<li>
							<label>배송요청사항</label>
							<div class="flex-column">
							<select name="order_message" id="select_msg">
								<option value="1">선택해 주세요.</option>
								<option value="2">문 앞에 놓아주세요.</option>
								<option value="3">부재시 경비실에 맡겨주세요.</option>
								<option value="4">부재시 연락주세요.</option>
								<option value="5">배송 전 연락주세요.</option>
								<option value="6">직접 입력</option>
							</select>
							<textarea name="order_msg" id="order_msg_textarea"></textarea>
							</div>
						</li>
						<li>
							<div class="radio-group">
								<label>공동현관 출입방법</label>			
								<input type="radio" name="enter" value="1">공동현관 비밀번호
								<input type="radio" name="enter" value="2" checked>자유출입 가능
								<input type="text" style="width:220px;" name="enter_passwd" id="enter_passwd" placeholder="정확한 공동현관 출입번호를 입력하세요">
							</div>
						</li>
					</ul>
				</div>
				
				<div id="order_book">
					<div>
					<label>주문도서</label>
					<table id="orderFormTable">
		 			<c:forEach var="cart" items="${list}">
		 				<tr>
		 					<td>
		 						<a href="${pageContext.request.contextPath}/book/detail.do?book_num=${cart.book_num}">
		 							<img src="${pageContext.request.contextPath}/upload/${cart.bookVO.book_img}" width="100" style="margin:10px 0;">
		 						</a>
		 					</td>
		 					<td style="padding-right:50px;">
		 						<a href="${pageContext.request.contextPath}/book/detail.do?book_num=${cart.book_num}">
		 							${cart.bookVO.book_name}
		 						</a>
		 					</td>
		 					<td class="align-center" style="padding-right:30px;">
		 						<span>${cart.c_quantity}개</span>
		 					</td>
		 					<td class="align-center">
		 						<fmt:formatNumber value="${cart.bookVO.price}"/>원
		 					</td>
		 				</tr>
		 			</c:forEach>
		 			</table>
					</div>
				</div>
				
				<div>
					<ul>
						<li><label>포인트</label>보유 <span style="color:#1c782b; font-weight:bold;">${point}</span>원</li>
					</ul>
					<div id="point_use">
						<input type="hidden" value="${point}" id="userpoint">
						<span>통합 포인트</span> <span>${point}원</span>
						<input type="number" name ="usedpoint" id="pointval" min="0" max="${point}" maxlength ="${point}" value="0">
						<input type="button" name="pointpayment" id="pointbtn" value="사용" class="cartorder-btn">
					</div>
				</div>
				
				<div style="display:flex;">
					<label style="display:inline-block; width:125px; margin-top:16px;" for="payment">결제수단</label>

						<div class="radio-group">
							<input type="radio" class="payment" name="payment" value="1">신용카드
							<input type="radio" class="payment" name="payment" value="2">계좌이체
							<input type="radio" class="payment" name="payment" value="3">휴대폰결제	
							<input type="text" name="enter_passwd" placeholder="카드번호" id="pay_card">
						</div>
						

			 	</div>
			 	
			</div>
			
			<div class="cartList2">
		 			<ul style="display:inline-block; width:200px;">
		 				<li class="flex-container">상품 금액 <span><b><fmt:formatNumber value="${all_total}"/></b>원</span></li>
		 				<li class="flex-container">
		 					<span class="tooltip-container">
		 						배송비<img src="${pageContext.request.contextPath}/images/info.png" class="tooltip" width="15px">
		 						<span class="tooltip-text">도서 1만5천원 이상 구매시 무료배송<br>
									도서 1만5천원 미만 구매시 배송비 2,500원 부과</span>
							</span>
			 					<span>
								<c:if test="${all_total >= 15000}">
								0원
								</c:if>
								<c:if test="${all_total < 15000}">
								<fmt:formatNumber value="2500"/>원
								</c:if>
								
							</span>
		 				</li>
		 				<li class="flex-container" style="margin-bottom:20px;">포인트 사용 <span><span id="usedPoint" style="text-align:right;">0</span>원</span>
		 				
		 				</li>

		 				
						<hr size="1" width="100%">
		 				<li class="flex-container" style="margin-top:20px;"><b>결제 예정 금액</b> <span><b><c:set var="point" value="" scope="request"/><span id="total"><fmt:formatNumber value="${all_total_delivery}"/></span></b>원</span>
		 				<input type="hidden" value="${all_total}" id="all_total">
		 				<input type="hidden" value="${all_total_delivery}" id="all_total_delivery">
		 				</li>
		 				<li class="flex-container">
		 					<span class="tooltip-container">
		 						적립 예정 포인트 <img src="${pageContext.request.contextPath}/images/info.png" class="tooltip" width="15px">
		 						<span class="tooltip-text">상품 주문 시 적립 예정 포인트가 자동 합산되고 주문하신 상품이 발송완료 된 후에 자동으로 적립됩니다.<br>
									상품구매시 구매액의 3%가 적립됩니다.</span>
								</span>
		 					<span><span id="inpoint"><fmt:formatNumber value="${Math.floor(all_total*0.03)}"/></span>P</span>
		 				</li>
		 			</ul>
		 			<div class="align-center cart-submit">
		 			<input type="submit" value="결제하기"  style="margin-bottom:5px;">
	 				</div>
		 		</div>
		 	</div>
		 	
		 	
		 	<%--다음 우편번호 api 시작 --%>
		 	<div id="layer" style="display:none;position:fixed;overflow:hidden;z-index:1;-webkit-overflow-scrolling:touch;">
		<img src="//t1.daumcdn.net/postcode/resource/images/close.png" id="btnCloseLayer" style="cursor:pointer;position:absolute;right:-3px;top:-3px;z-index:1" onclick="closeDaumPostcode()" alt="닫기 버튼">
		</div>
		
		<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
		<script>
		    // 우편번호 찾기 화면을 넣을 element
		    var element_layer = document.getElementById('layer');
		
		    function closeDaumPostcode() {
		        // iframe을 넣은 element를 안보이게 한다.
		        element_layer.style.display = 'none';
		    }
		
		    function execDaumPostcode() {
		        new daum.Postcode({
		            oncomplete: function(data) {
		                // 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
		
		                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
		                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
		                var addr = ''; // 주소 변수
		                var extraAddr = ''; // 참고항목 변수
		
		                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
		                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
		                    addr = data.roadAddress;
		                } else { // 사용자가 지번 주소를 선택했을 경우(J)
		                    addr = data.jibunAddress;
		                }
		
		                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
		                if(data.userSelectedType === 'R'){
		                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
		                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
		                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
		                        extraAddr += data.bname;
		                    }
		                    // 건물명이 있고, 공동주택일 경우 추가한다.
		                    if(data.buildingName !== '' && data.apartment === 'Y'){
		                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
		                    }
		                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
		                    if(extraAddr !== ''){
		                        extraAddr = ' (' + extraAddr + ')';
		                    }
		                    //(주의)address1에 참고항목이 보여지도록 수정
		                    // 조합된 참고항목을 해당 필드에 넣는다.
		                    //(수정) document.getElementById("address2").value = extraAddr;
		                
		                } 
		                //(수정) else {
		                //(수정)    document.getElementById("address2").value = '';
		                //(수정) }
		
		                // 우편번호와 주소 정보를 해당 필드에 넣는다.
		                document.getElementById('receive_zipcode').value = data.zonecode;
		                //(수정) + extraAddr를 추가해서 address1에 참고항목이 보여지도록 수정
		                document.getElementById("receive_address1").value = addr + extraAddr;
		                // 커서를 상세주소 필드로 이동한다.
		                document.getElementById("receive_address2").focus();
		
		                // iframe을 넣은 element를 안보이게 한다.
		                // (autoClose:false 기능을 이용한다면, 아래 코드를 제거해야 화면에서 사라지지 않는다.)
		                element_layer.style.display = 'none';
		            },
		            width : '100%',
		            height : '100%',
		            maxSuggestItems : 5
		        }).embed(element_layer);
		
		        // iframe을 넣은 element를 보이게 한다.
		        element_layer.style.display = 'block';
		
		        // iframe을 넣은 element의 위치를 화면의 가운데로 이동시킨다.
		        initLayerPosition();
		    }
		
		    // 브라우저의 크기 변경에 따라 레이어를 가운데로 이동시키고자 하실때에는
		    // resize이벤트나, orientationchange이벤트를 이용하여 값이 변경될때마다 아래 함수를 실행 시켜 주시거나,
		    // 직접 element_layer의 top,left값을 수정해 주시면 됩니다.
		    function initLayerPosition(){
		        var width = 300; //우편번호서비스가 들어갈 element의 width
		        var height = 400; //우편번호서비스가 들어갈 element의 height
		        var borderWidth = 5; //샘플에서 사용하는 border의 두께
		
		        // 위에서 선언한 값들을 실제 element에 넣는다.
		        element_layer.style.width = width + 'px';
		        element_layer.style.height = height + 'px';
		        element_layer.style.border = borderWidth + 'px solid';
		        // 실행되는 순간의 화면 너비와 높이 값을 가져와서 중앙에 뜰 수 있도록 위치를 계산한다.
		        element_layer.style.left = (((window.innerWidth || document.documentElement.clientWidth) - width)/2 - borderWidth) + 'px';
		        element_layer.style.top = (((window.innerHeight || document.documentElement.clientHeight) - height)/2 - borderWidth) + 'px';
		    }
		</script>
		</form>
	</div>	
</div>
</body>
</html>