<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ようこそぺこった～へ！</title>

<link
	href="https://fonts.googleapis.com/css2?family=M+PLUS+Rounded+1c:wght@700&display=swap"
	rel="stylesheet">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/welcome.css">
</head>

<!-- <body> -->
<body class="loginOk">
	
	<div class="loginOk-content wrapper">
			<h1 class="loginOk-title">ようこそ、<c:out value="${account.getName()}" />さん</h1>
			<span class="mgr-15"><a class="button2"
				href="/PekotterBetaAWS/Main"> つぶやき画面へGO! </a></span> <a class="button2"
				href="/PekotterBetaAWS/WelcomServlet">トップへ戻るぺこ</a>
		</div>

</body>
</html>