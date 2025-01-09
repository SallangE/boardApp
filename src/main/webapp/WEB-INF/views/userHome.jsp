<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="userHeader.jsp" %>

    <div class="container mt-5">
        <div class="card shadow-sm">
            <div class="card-body">
                <h1 class="text-center">${userName}님, 환영합니다!</h1>
                <p class="text-center mt-3">
                    <a href="logout" class="btn btn-danger">로그아웃</a>
                </p>
            </div>
        </div>
    </div>
<%@ include file="userFooter.jsp" %>
