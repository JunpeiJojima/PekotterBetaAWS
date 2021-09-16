<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ぺこった～</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/check.css">

<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Sawarabi+Gothic&display=swap"
	rel="stylesheet">
<link href="css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<div class="bg">
		<h2>ぺこった～ログイン</h2>
		<c:choose>
			<c:when test="${registResult}">
				<p>新規アカウント作成に成功したぺこ！</p>
				<a href="/PekotterBetaAWS/LoginServlet">ログイン画面へGOぺこ！</a>
			</c:when>
			<c:otherwise>
				<p>ログイン失敗ぺこ…</p>
				<p>お手数ですが、最初から登録よろぺこ</p>
				<a href="/PekotterBetaAWS/WelcomServlet">TOPへ戻るぺこ</a>
			</c:otherwise>
		</c:choose>
	</div>
</body>
</html>