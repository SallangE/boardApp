<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <jsp:include page="/WEB-INF/views/userHeader.jsp" />
<!DOCTYPE html>
<html>
<head>
    <title>게시물 상세보기</title>
    <!-- 부트스트랩 CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body class="bg-light">
    <div class="container mt-5">
        <div class="card shadow">
            <div class="card-header bg-primary text-white">
                <h3>${board.title}</h3>
            </div>
            <div class="card-body">
                <p class="text-muted">작성자: <strong>${board.name}</strong></p>
                <p class="text-muted">작성 시간: ${board.created_at}</p>
                <hr>
                <p>${board.content}</p>
            </div>
            <div class="card-footer text-end">
                <a href="${pageContext.request.contextPath}/board?cmd=list" class="btn btn-secondary">목록으로</a>
            	<!-- 작성자 여부에 따라 수정 및 삭제 버튼 표시 -->
			    <c:if test="${isOwner}">
			        <a href="${pageContext.request.contextPath}/board?cmd=edit&id=${board.board_id}" class="btn btn-primary">수정</a>
			        <a href="${pageContext.request.contextPath}/board?cmd=delete&id=${board.board_id}" class="btn btn-danger" onclick="return confirm('삭제하시겠습니까?');">삭제</a>
			    </c:if>
            </div>
        </div>
    </div>
<%@ include file="userFooter.jsp" %>