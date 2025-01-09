<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <!-- 지시자에 elJstl사용해서 스크립트릿 사용 안하고 표현 -->
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>로그인</title>
    <!-- Bootstrap CSS 링크 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
    <div class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-md-6">
                <div class="card shadow-sm">
                    <div class="card-body">
                        <h1 class="text-center mb-4">로그인</h1>
                        <form action="login" method="post">
                            <div class="mb-3">
                                <label for="studentId" class="form-label">학번</label>
                                <input type="text" class="form-control" id="studentId" name="studentId" placeholder="학번 입력" required>
                            </div>
                            <div class="mb-3">
                                <label for="password" class="form-label">비밀번호</label>
                                <input type="password" class="form-control" id="password" name="password" placeholder="비밀번호 입력" required>
                            </div>
                            <button type="submit" class="btn btn-primary w-100">로그인</button>
                        </form>
                        
                        <!-- 에러 메시지 출력 -->
                        <c:if test="${not empty error}">
                            <div class="alert alert-danger mt-3" role="alert">
                                ${error}
                            </div>
                        </c:if>

                        <!-- 회원가입 버튼 -->
                        <div class="text-center mt-3">
                            <p>계정이 없다면 회원가입을 통해 생성해주세요.</p>
                            <a href="register" class="btn btn-secondary w-100">회원가입</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
<%@ include file="userFooter.jsp" %>