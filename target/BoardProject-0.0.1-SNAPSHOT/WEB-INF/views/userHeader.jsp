<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
	<title>게시판</title>
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container-fluid">
            <a class="navbar-brand" href="<c:url value='/userHome' />">홈</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
                    aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav">
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="boardDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                            게시판
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="boardDropdown">
                            <li><a class="dropdown-item" href="<c:url value='/board?cmd=list' />">게시물 목록</a></li>
                            <li><a class="dropdown-item" href="<c:url value='/board?cmd=write' />">게시물 작성</a></li>
                        </ul>
                    </li>
                </ul>
                <ul class="navbar-nav ms-auto">
                    <li class="nav-item">
                        <span class="navbar-text me-3">환영합니다, <c:out value="${userName}" />님</span>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link btn btn-danger text-white" href="<c:url value='/logout' />">로그아웃</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
