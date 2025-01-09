<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="userHeader.jsp" %>

<div class="container">
<br>
  <h2>게시글 목록</h2>            
  <table class="table table-dark table-hover">
    <thead>
      <tr>
        <th>게시물 번호</th>
        <th>제목</th>
        <th>작성자</th>
        <th>내용</th>
        <th>작성 시간</th>
        <th>조회 수</th>
      </tr>
    </thead>
    <tbody>
    <c:forEach var="i" items="${list}" >
      <tr>
         <td>${i.board_id}</td>
         <td><a href="${pageContext.request.contextPath}/board?cmd=view&id=${i.board_id}">${i.title}</a></td>
         <td>${i.name}</td>
         <td>${i.content}</td>
         <td>${i.created_at}</td>
         <td>${i.view}</td>
      </tr>
    
    </c:forEach>
      
    </tbody>
  </table>
</div>
<%@ include file="userFooter.jsp" %>