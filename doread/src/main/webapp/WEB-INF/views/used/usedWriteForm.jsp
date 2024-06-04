<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>중고게시판 글쓰기</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header_test.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>
<script type="text/javascript">
	window.onload = function(){
		const inputcheck = document.getElementsByClassName('inputcheck');
		const form = document.getElementById('event_form');
		
		form.onsubmit = function(){
			for(let i=0; i<inputcheck.length; i++){
			const label = document.querySelector('label[for="'+inputcheck[i].id+'"]');
			
			if(inputcheck[i].value.trim()==''){
				alert(label.textContent+' 필수 입력');
				inputcheck[i].value='';
				inputcheck[i].focus();
				return false;
			}
			}
			}
		
		
		
		
	};

</script>
</head>
<body>
<jsp:include page="/WEB-INF/views/news/test_header.jsp"/>
<div class="page-main">

<div class="align-center">
 	<h2>중고게시판</h2>	
 		<div class="float-clear">
			<hr width="100%" size="1" noshade="noshade">
		</div>
		<div class="formdiv2">
		<form action="usedcreate.do" method="post" id="event_form" enctype="multipart/form-data" >
		<input type="hidden" name="book_num" id="book_num" >
			
			<ul class="align-left">
				<li>
					<label for="u_title">제목</label>
					<input type="text" name="u_title" class="inputcheck input-style" id="u_title" maxlength="30" >
				</li>
				<li><label for="book_name">책 이름</label>
					<input type="text" name="book_name" class="inputcheck input-style "  id="book_name" maxlength="10" readonly="readonly"  >
					<input type="button" id ="book_numbtn" value="책선택" >
				</li>
				
				
				<li><label for="u_price">책 가격</label>
					<input type="number" name="u_price" class="inputcheck input-style " id="u_price" maxlength="10">
				</li>
				<li><label for="selectlist">책 상태</label>
					<select id="selectlist" name ="sel" class="input-style">
					<option value="1" >미개봉</option>
 					<option value="2" >개봉,사용감없음</option>
 					<option value="3">개봉, 2개이하의 파손이나 낙서</option>
 					<option value="4" >개봉, 4개이하의 파손이나 낙서</option>
 					<option value="5" >개봉, 5개이상의 파손이나 낙서</option>
 				</select>
				</li>
				<li><label for="u_content">내용</label>
					<textarea class="inputcheck input-style" style="height:150px"  id="u_content" name="u_content"></textarea>
				</li>
				
				<li>
					<label for="u_image">이미지 첨부</label>
					<input type="file" id="u_image" name="u_image" class="input-style " accept="image/gif,image/png,image/jpeg">
					
				</li>
			</ul>
			
			<div class="align-center">
			<input type="submit" value="등록" class="button2">
			<input type="button" value="목록" class="button2" onclick="location.href='${pageContext.request.contextPath}/used/usedList.do'">
			</div>
			
	
	</form>
</div>
				<div class="modal hide">
					<h4>책 검색</h4>
					<form id="searchform" >
					
					<input type="text" name="search" class="inputcheck input-style" id="search" maxlength="30" placeholder="제목을 입력하세요">
 					<input id="bookbtn" type="submit" value="검색" class="button2"><input id="bookbtn2" type="button" value="취소" class="button2">
					</form>
					
					<div id="add">
					
					</div>
				</div>
				 <script type="text/javascript">
			    	$("#book_numbtn").click(function() {
			    		
			        if($('.modal').hasClass('hide')){
			            $('.modal').removeClass('hide');
			        }else{
			            $('.modal').addClass('hide');
			    	    }
			  	  		});
			    	
			    	$("#bookbtn2").click(function() {
			    		 $('.modal').addClass('hide');
	
			    	});
			    	$("#searchform").submit(function(event){
			    		$.ajax({
			    			url:'searchBook.do',
			    			data:{book_name:$("#search").val()},
			    			type:'post',
			    			dataType:'json',
			    			success:function(param){
			    				if(param.result=='none'){
			    					$('#add').empty();
			    					$('#add').append('찾으시는 제목의 책이 없습니다.');
			    					$('#add').append('<br><input type="button" class="input-style" value="책등록요청하러가기">');
			    					
			    				}else if(param.result=='success'){
			    					$('#add').empty();
			    					let output = '';
			    						
			    					$(param.list).each(function(index,item){
			    					
			    						output += '<input type="text" value="'+item.book_name+'" readonly="readonly" class="booksh" id="'+item.book_num+'">';
			    						output +='<br>';
			    						
			    					})
			    					
			    					$('#add').append(output);
			    				}else{
			    					alert('책찾기오류');
			    				}
			    				
			    			},error:function(){
			    				alert('네트워크오류');
			    			}
			    			
			    			
			    			
			    		});
			    		
			    		event.preventDefault();
			    	});
			    	
			    $(function(){$(document).on('click','.booksh',function(){
			    	
		    		$('#book_name').val(this.value);
		    		$('#book_num').val(this.id);
		    		 $('.modal').addClass('hide');
		    		
	        });});

			    	
			   
			    
			    	
			    </script>	
			
			
			
			
		<div class="float-clear">
			<hr width="100%" size="1" noshade="noshade">
		</div>
		

 	
			
			
			

	</div>
	
</div>

</body>
</html>

