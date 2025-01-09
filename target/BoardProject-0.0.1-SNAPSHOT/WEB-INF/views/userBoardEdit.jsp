<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <jsp:include page="/WEB-INF/views/userHeader.jsp" />
<!DOCTYPE html>
<html>
<head>
    <title>게시물 수정</title>
    <!-- 부트스트랩 CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-5">
        <h1>게시물 수정</h1>
        <br>
        <form action="${pageContext.request.contextPath}/board?cmd=update" method="post">
            <input type="hidden" name="id" value="${board.board_id}">
            <div class="mb-3">
                <label for="title" class="form-label">제목</label>
                <input type="text" class="form-control" id="title" name="title" value="${board.title}" required>
            </div>
            <div class="mb-3">
                <label for="content" class="form-label">내용</label>
                <textarea class="form-control" id="content" name="content" rows="5" required>${board.content}</textarea>
            </div>
            <br>
            <button type="submit" class="btn btn-primary">저장</button>
            <a href="${pageContext.request.contextPath}/board?cmd=list" class="btn btn-secondary">취소</a>
        </form>
    </div>
<%@ include file="userFooter.jsp" %>