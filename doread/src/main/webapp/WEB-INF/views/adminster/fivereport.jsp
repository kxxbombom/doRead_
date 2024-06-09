<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자페이지</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/ssh.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/ysw.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>
<script type="text/javascript">
	$(function(){
		$('.contentbtn').click(function(){
			if($('span[data-id="'+$(this).attr('data-id')+'"]').hasClass('hide')){
			$('span[data-id="'+$(this).attr('data-id')+'"]').removeClass('hide');
			$(this).val('댓글닫기');
			$.ajax({
				url:'viewcommnet.do',
				type:'post',
				data:{id:$(this).attr('data-id'),name:$(this).attr('data-name')},
				dataType:'json',
				success:function(param){
					if(param.result =='logout'){
						alert('관리자회원으로 로그인해야 볼 수 있습니다.');
						
					}else if(param.result =='success'){
						let data =param.con;
						$('.content').text(data);
						
					}else{
						alert('보기 오류');
					}
					
				},
				error:function(){
					alert('네트워크오류');
				}
				
				
			})		
			
			}else{
				$('span[data-id="'+$(this).attr('data-id')+'"]').addClass('hide');
				$('span[data-id="'+$(this).attr('data-id')+'"]').text('');
				$(this).val('댓글내용확인');
			}
			
				
		
		});
		
		
	})


</script>
</head>
<body>
<jsp:include page="/WEB-INF/views/adminster/adminheader3.jsp"/>
<br><br>
<jsp:include page="/WEB-INF/views/adminster/adminheader.jsp"/>
<div class="page-main">
<h2>누적 5회</h2>
	<c:if test="${empty list}">
	 신고등록된 게시글이 없습니다.
	</c:if>
	<c:if test="${!empty list}">
	 <table>
	 	<tr>
	 	<th>게시판 분류/번호</th>
	 	<th>작성회원번호</th>
	 	<th>신고당한횟수</th>
	 	
	 	</tr>
	 	<c:forEach var="i" items="${list}">
	 		<c:if test="${i.sumcount >=5}">
	 	<tr>
	 			<td><c:if test="${i.type =='u_num'}"><a href="${pageContext.request.contextPath}/used/usedDetail.do?u_num=${i.number}">중고게시판글-${i.number}</a></c:if>
	 				<c:if test="${i.type =='uc_num'}">중고게시판댓글-${i.number}<input type="button" value="댓글내용확인" data-id="${i.number}" data-name="${i.type}" class="contentbtn"><span data-id="${i.number}" class="content hide"></span></c:if>
	 				<c:if test="${i.type =='s_num'}"><a href="${pageContext.request.contextPath}/story/storyDetail.do?s_num=${i.number}">스토리게시판글-${i.number}</a></c:if>
	 				<c:if test="${i.type =='sc_num'}">스토리게시판댓글-${i.number}<input type="button" value="댓글내용확인" data-id="${i.number}" data-name="${i.type}" class="contentbtn"><span  data-id="${i.number}" class="content hide"></span></c:if>
	 			</td>
	 			<td>
	 				<a href="${pageContext.request.contextPath}/adminster/detailUser.do?mem_num=${i.mem_num}">${i.mem_num}</a>
	 			</td>
	 			<td style="color:red;">
	 				${i.sumcount}
	 			</td>
		</tr>
	 		</c:if>
	 	</c:forEach>
	 </table>
	</c:if>
	<div class="align-center">	
		<input type="button" value="메인" class="button2" onclick="location.href='${pageContext.request.contextPath}/main/main.do'">
	</div>
</div>
</body>
</html>