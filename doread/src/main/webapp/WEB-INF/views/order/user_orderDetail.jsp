<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주문 상세 내역</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/kts.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/kbm.css" type="text/css">

<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header_test.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/order.detail.js"></script>

</head>
<body>
<div class="page-main">
	
	<jsp:include page="/WEB-INF/views/member/mypageheader.jsp"/>
	<div class="content-main orderdetail">
	<h2>주문상세내역</h2>
	<hr size="1" noshade width="100%">
	<table>
 			<tr>
 				<th>도서명</th>
 				<th></th>
 				<th>수량</th>
 				<th>도서가격</th>
 				<th>합계</th>
 			</tr>
 			<c:forEach var="detail" items="${detailList}">
				<tr>
					<td>
						<a href="${pageContext.request.contextPath}/book/detail.do?book_num=${detail.book_num}">
							<img src="${pageContext.request.contextPath}/upload/${detail.book_image}" width="100">
						</a>
					</td>
					<td>
						<a href="${pageContext.request.contextPath}/book/detail.do?book_num=${detail.book_num}">${detail.book_name}</a>
					</td>
					<td class="align-center">
						<fmt:formatNumber value="${detail.order_quantity}"/>
					</td>
					<td class="align-center">
						<fmt:formatNumber value="${detail.book_price}"/>원
					</td>
					<td class="align-center">
						<fmt:formatNumber value="${detail.book_total}"/>원
					</td>
				</tr>
 			</c:forEach>
 			<tr>
 				<td colspan="4" class="align-right"><b>총구매금액</b></td>
 				<td class="align-center"><fmt:formatNumber value="${order.order_total}"/>원</td>
 			</tr>
 		</table>
 		
 		
 		<div id="floattest">
		<div id="receive_info" class="test-left">
			<label>주문번호</label><span>${order.order_num}</span><br>
			<label>배송상태</label>
			<span>
				<c:if test="${order.order_status == 1}">결제완료</c:if>
				<c:if test="${order.order_status == 2}">배송시작</c:if>
				<c:if test="${order.order_status == 3}">배송중</c:if>
				<c:if test="${order.order_status == 4}">배송완료</c:if>
				<c:if test="${order.order_status == 5}">주문취소</c:if>
			</span>
			<ul>
				<li>
					<label>배송지정보</label>
					<div id="receive_div">
						<ul>
							<li><span id="receive_name">${order.receive_name}</span>&nbsp;<span id="receive_phone">${order.receive_phone}</span></li>
							<li><span id="receive_zipcode">${order.receive_zipcode}</span></li>
							<li><span id="receive_address1">${order.receive_address1}</span>&nbsp; <span id="receive_address2">${order.receive_address2}</span></li>

						</ul>
					</div>
				</li>
				<li>
					<label>배송요청사항</label>
					<span id="order_msg">
						<c:if test="${order.select_msg == 2}">문 앞에 놓아주세요</c:if>
						<c:if test="${order.select_msg == 3}">부재시 경비실에 맡겨주세요</c:if>
						<c:if test="${order.select_msg == 4}">부재시 연락주세요</c:if>
						<c:if test="${order.select_msg == 5}">배송 전 연락주세요</c:if>
						<c:if test="${order.select_msg == 6}">${order.order_msg}</c:if>
					</span>
				</li>
				<li>
					<label>공동현관 출입방법</label>
					<span id="enter">
						<c:if test="${order.enter == 1}">
							공동현관 비밀번호
						</c:if>
						<c:if test="${order.enter == 2}">
							자유출입 가능
						</c:if>
					</span>
					<c:if test="${order.enter == 1}">
						<span id="enter_passwd">${order.enter_passwd}</span>
					</c:if>
				</li>
			</ul>
			</div>
			<div id="test-right">
			<ul>
				<li>
					<label>결제정보</label>
					<div id="payment_div">
						<ul>
							<li><span>주문금액</span> <span><fmt:formatNumber value="${order.order_total}"/>원</span></li>
							<li><span>상품금액</span> <span><fmt:formatNumber value="${Obook_total}"/>원</span></li>
							<li><span>배송비</span> <span><fmt:formatNumber value="${delivery}"/>원</span></li>
							<li><span>포인트사용</span> <span><fmt:formatNumber value="${used_point}"/>원</span></li>

							<li><span>결제수단</span>
								<span>
								<c:if test="${order.order_payment == 1}">신용카드</c:if>
								<c:if test="${order.order_payment == 2}">계좌이체</c:if>
								<c:if test="${order.order_payment == 3}">휴대폰결제</c:if>
								</span>
							</li>
						</ul>
					</div>
				</li>
				
			</ul>
			</div>
			
		</div>	
			<div class="align-center orderdetailbtn" style="clear:both;">
				<c:if test="${order.order_status == 1}">
			
				<input type="button"  class="btn2" value="배송지수정" id="openModalBtn">
				<input type="button" class="btn4" id="cancelOrder" value="주문취소">
				
				<script type="text/javascript">
	                $('#cancelOrder').click(function(event){
	            		let check = confirm('주문을 취소하시겠습니까?');
	            		if(check){
	            			location.href="cancelOrder.do?order_num=${order.order_num}";
	            		}
	            		event.preventDefault();
	            	});
                </script>
                
                </c:if>
				
				<input type="button" class="btn4" value="주문목록" onclick="location.href='${pageContext.request.contextPath}/shopping/buylist.do'">
			
			</div>
		
		
		
		<div id="myModal" class="mymodal">
        <div class="modal-content">
            <h2>배송지정보 수정</h2>
            <form id="editForm">
            	<input type="hidden" id="order_num" value="${order.order_num}">
            	<ul>
            		<li>
                		<label for="re_name">이름</label>
                		<input type="text" id="re_name" name="re_name" required value="${order.receive_name}"><br><br>
               		</li>
               		<li>
                		<label for="re_phone">전화번호</label>
                		<input type="text" id="re_phone" name="re_phone" required value="${order.receive_phone}"><br><br>
                	</li>
                	<li>
               		 	<label for="zipcode">우편번호</label>
                		<input type="text" id="zipcode" name="re_zipcode" required value="${order.receive_zipcode}">
                		<input type="button" value="우편번호 찾기" onclick="execDaumPostcode()"><br><br>
                	</li>
                	<li>
                		<label for="address1">주소</label>
                		<input type="text" id="address1" name="re_address1" required value="${order.receive_address1}"><br><br>
                	</li>
                	<li>
                		<label for="address2">상세주소</label>
                		<input type="text" id="address2" name="re_address2" required value="${order.receive_address2}"><br><br>
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
							<textarea name="order_msg" id="order_msg_textarea">${order.order_msg}</textarea>
							</div>
							<br><br>
               		</li>
               		<li>
               			<div class="radio-group">
                		<label for="re_enter">공동현관 출입방법</label>
                		<input type="radio" name="re_enter" required value="1" <c:if test="${order.enter == 1}">checked</c:if> onclick="toggleEnterPasswd(true)">공동현관 비밀번호
						<input type="radio" name="re_enter" required value="2" <c:if test="${order.enter == 2}">checked</c:if> onclick="toggleEnterPasswd(false)">자유출입
						<input style="display: none; width:150px;" type="text" id="re_enter_passwd" name="re_enter_passwd" placeholder="공동현관 비밀번호" <c:if test="${order.enter == 1}">value="${order.enter_passwd}"</c:if>>
               			</div>
               		</li>
                </ul>
                <div class="align-left">
                <button type="submit" class="basicbtn">확인</button>
                <input type="button" id="modify_cancel" value="취소" class="close basicbtn">
                </div>
                
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
		                document.getElementById('zipcode').value = data.zonecode;
		                //(수정) + extraAddr를 추가해서 address1에 참고항목이 보여지도록 수정
		                document.getElementById("address1").value = addr + extraAddr;
		                // 커서를 상세주소 필드로 이동한다.
		                document.getElementById("address2").focus();
		
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
	</div>
</div>
</body>
</html>