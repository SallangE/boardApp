<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>회원가입</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>
        $(document).ready(function () {
            $("#registerForm").submit(function (e) {
                // 정규식을 통한 유효성 검사
                const idRegex = /^[a-zA-Z0-9]{10}$/;
                const passwordRegex = /^.{4,}$/;

                if (!idRegex.test($("#studentId").val())) {
                    alert("ID는 10자의 영문 또는 숫자로 입력해주세요.");
                    e.preventDefault();
                    return;
                }

                if (!passwordRegex.test($("#password").val())) {
                    alert("비밀번호는 최소 4자 이상이어야 합니다.");
                    e.preventDefault();
                    return;
                }
            });
        });
    </script>
<body>
<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <h1 class="text-center">회원가입</h1>
            
            <c:if test="${not empty error}">
                <div class="alert alert-danger">${error}</div>
            </c:if>
        	
            <form id="registerForm" action="register" method="post">
                <div class="mb-3">
                    <label for="studentId" class="form-label">학번</label>
                    <input type="text" class="form-control" id="studentId" name="studentId" required>
                </div>
                <div class="mb-3">
                    <label for="password" class="form-label">비밀번호</label>
                    <input type="password" class="form-control" id="password" name="password" required>
                </div>
                <div class="mb-3">
                    <label for="name" class="form-label">이름</label>
                    <input type="text" class="form-control" id="name" name="name" required>
                </div>
                <div class="mb-3">
                    <label for="university" class="form-label">대학교</label>
                    <input type="text" class="form-control" id="university" name="university" required>
                </div>
                <div class="mb-3">
                    <label for="department" class="form-label">학과</label>
                    <input type="text" class="form-control" id="department" name="department" required>
                </div>
                <button type="submit" class="btn btn-primary w-100 mb-3">회원가입</button>
            </form>

            <!-- 로그인 페이지로 이동하는 버튼 -->
            <div class="text-center">
                <a href="${pageContext.request.contextPath}/login" class="btn btn-link">로그인 페이지로 돌아가기</a>
            </div>
        </div>
    </div>
</div>
<%@ include file="userFooter.jsp" %>