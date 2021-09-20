<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>pekotter</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/main.css">

<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Sawarabi+Gothic&display=swap"
	rel="stylesheet">
<link href="css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<div class="bg">
		<h1>つぶやき編集</h1>
		<div class="main-content wrapper">

			<aside>

				<p style="text-align: left">
					ログイン中のアカウント : <br> <b><c:out value="${account.getName()}" /></b>
					さん 
					<br> <br>
					 <a href="/PekotterBetaAWS/Logout"> ログアウト</a>
				</p>

				<br>
				<br>
				<p>
					<a href="/PekotterBetaAWS/Main">編集をやめるぺこ</a>
				</p>
			</aside>

			<article>
				<form action="/PekotterBetaAWS/EditTweetServlet" method="post">
					<textarea name="text">${editTweet.getText()}</textarea>
					<input type="hidden" name="id" value="${editTweet.getId()}">
					<input type="submit" class="button" value="ぽえみんぐぺこ">
				</form>
				<c:if test="${not empty errorMsg}">
					<p>${errorMsg}</p>
				</c:if>
			</article>
		</div>
	</div>
</body>
</html>