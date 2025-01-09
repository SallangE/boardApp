<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="/WEB-INF/views/userHeader.jsp" />
<!DOCTYPE html>
<html>
<head>
    <title>게시글 작성</title>
    <!-- 부트스트랩 CSS 추가 -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <script>
        document.addEventListener("DOMContentLoaded", function() {
            const submitButton = document.getElementById("submitBtn");

            submitButton.addEventListener("click", function() {
                const title = document.getElementById("title").value.trim();
                const content = document.getElementById("content").value.trim();
				
                if (!title || !content) {
                    alert("제목이나 내용을 비어있습니다!");
                    return;
                }

                document.getElementById("boardForm").submit();
            });
        });
    </script>
</head>
<body class="bg-light">
    <div class="container mt-5">
        <div class="card shadow">
            <div class="card-header bg-primary text-white">
                <h4>게시글 작성</h4>
            </div>
            <div class="card-body">
                <form id="boardForm" action="${pageContext.request.contextPath}/board?cmd=submit" method="post">
                    <div class="mb-3">
                        <label for="title" class="form-label">제목</label>
                        <input type="text" id="title" name="title" class="form-control" placeholder="제목을 입력하세요" required>
                    </div>
                    <div class="mb-3">
                        <label for="content" class="form-label">내용</label>
                        <textarea id="content" name="content" rows="5" class="form-control" placeholder="내용을 입력하세요" required></textarea>
                    </div>
                    <div class="d-grid">
                        <button type="button" id="submitBtn" class="btn btn-primary">작성하기</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
<%@ include file="userFooter.jsp" %>