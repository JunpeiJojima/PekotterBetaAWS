<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ぺこった～にログインするぺこ</title>
<link
	href="https://fonts.googleapis.com/css2?family=M+PLUS+Rounded+1c:wght@700&display=swap"
	rel="stylesheet">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/welcome.css">
</head>

<body class="loginImage">

	<div class="login-content wrapper">
		<h1 class="login-title">ぺこった～にログインするぺこ</h1>

		<div>
			<c:if test="${not empty errorMsg}">
				<p>${errorMsg}</p>
			</c:if>
		</div>

		<form action="/pekotterBeta1/LoginServlet" method="post">

			<!-- ユーザーID：<input type="text" name="userId"><br> -->
			<div>
				<label for="name">ユーザーID</label> <input type="text" id="name"
					name="userName">
			</div>

			<!-- パスワード：<input type="password" name="pass"><br> -->
			<div>
				<label for="pass">パスワード</label> <input type="password" id="pass"
					name="userPass">
			</div>

			<input type="submit" class="button" value="ログイン">

		</form>
	</div>
	<!-- /.wrapper -->
</body>
</html>