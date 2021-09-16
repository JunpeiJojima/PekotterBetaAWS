<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>アカウント新規作成</title>
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
		<h2>この内容で登録していいぺこ？</h2>
		<div style="align: center">
			<ul style="text-align: left">
				<li>ユーザーID：<c:out value="${registUser.getUserId()}" /></li>
				<li>パスワード：<c:out value="${registUser.getPass()}" /></li>
				<li>メールアドレス：<c:out value="${registUser.getMail()}" /></li>
				<li>おなまえ：<c:out value="${registUser.getName()}" /> さん
				</li>
				<li>ねんれい：<c:out value="${registUser.getAge()}" /> 歳
				</li>
			</ul>
			<a href="/PekotterBetaAWS/CreateNewUser"> <input type="submit"
				class="button" value="登録するぺこ">
			</a>  <a href="/PekotterBetaAWS/Registration"> <input type="submit"
				class="button" value="やめるぺこ">
			</a>
		</div>
	</div>
</body>
</html>