<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		<hr size="1" noshade width="100%">
			 
		
		<h4>중고 게시판</h4>
		<table>
			<tr>
				<th>글번호</th>
				<th>제목</th>
				<th>작성일</th>
				<th>글등급<img class="gideimage" src="${pageContext.request.contextPath}/images/question_471664.png"  width="15px">
				</th>
			</tr>
			<c:forEach var="list" items="${list}">
			<tr>
				<td>${list.u_num}</td>
				<td><a href="${pageContext.request.contextPath}/used/usedDetail.do?u_num=${list.u_num}">${lsit.u_title}</a></td>
				<td>${list.u_rdate}</td>
				<td style="color:red;">
					<c:if test="${list.u_auth == 2}">
					정지
					</c:if>
				</td>
			</tr>
			</c:forEach>		
		</table>
