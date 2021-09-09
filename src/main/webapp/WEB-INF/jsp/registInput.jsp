<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ぺこった～に登録するぺこ</title>
<link
	href="https://fonts.googleapis.com/css2?family=M+PLUS+Rounded+1c:wght@700&display=swap"
	rel="stylesheet">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/welcome.css">
</head>

<body class="registImage">

	<div class="regist-content wrapper">
		<h1 class="login-title">新たにアカウントを作成するぺこ！</h1>

	<div>
		<c:if test="${not empty errorMsg}">
			<p>${errorMsg}</p>
		</c:if>
		</div>

		<form action="/pekotterBeta1/Registration" method="post">

			<!-- ユーザーID：<input type="text" name="userId"><br> -->
			<div>
				<label for="name">ユーザーID</label> <input type="text" id="name"
					name="userId">
			</div>
			
			<!-- パスワード：<input type="password" name="pass"><br> --> 
			<div>
				<label for="passWord">パスワード</label> <input type="password" id="passWord"
					name="pass">
			</div>
			
			<!-- メールアドレス：<input type="text" name="mail"><br> -->
			<div>
				<label for="mailAddres">メールアドレス</label> <input type="text" id="mailAddres"
					name="mail">
			</div>
			
			<!-- おなまえ：<input type="text" name="name"><br> -->
			<div>
				<label for="myName">おなまえ</label> <input type="text" id="myName"
					name="name">
			</div>
			
			<!-- ねんれい：<input type="text" name="age"><br>-->
			<div>
				<label for="myAge">ねんれい</label> <input type="text" id="myAge"
					name="age">
			</div>

			<input type="submit" class="button" value="確認画面へ">

		</form>

	</div>
</body>
</html>