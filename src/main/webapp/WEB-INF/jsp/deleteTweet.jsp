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
		<h1>つぶやき削除</h1>
		<div class="main-content wrapper">

			<aside>

				<p style="text-align: left">
					ログイン中のアカウント : <br> <b><c:out value="${account.getName()}" /></b>
					さん <br> <br> <a href="/PekotterBetaAWS/Logout"> ログアウト</a>
				</p>
				<br> <br>
				<p>
					<a href="/PekotterBetaAWS/Main">削除やめるぺこ</a>
				</p>
			</aside>

			<article>
				<p>このつぶやきを削除するぺこ？</p>
				<br>
				<table id="maintable" class="td-css">
					<tr>
						<th><c:out value="${deleteTweet.getUserName()}" /> :</th>
						<td><small><c:out value="${deleteTweet.getTime()}" /></small></td>
					</tr>
					<tr>
						<td colspan="2"><c:out
								value="${deleteTweet.getText()}" /></td>
					</tr>
					<tr>
						<td><form action="/PekotterBetaAWS/DeleteTweetServlet"
								method="post">
								<input type="hidden" name="id" value="${deleteTweet.getId()}">
								<input type="submit" class="button" value="削除ぺこ">
							</form></td>
					</tr>
				</table>
				<br> <br>

			</article>
		</div>
	</div>
	<jsp:include page="/WEB-INF/jsp/footer.jsp" />
</body>
</html>