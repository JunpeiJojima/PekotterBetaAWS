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
		<h1>Home</h1>
		<div class="main-content wrapper">

			<aside>

				<p style="text-align: left">
					ログイン中のアカウント : 
					<br> 
					<b><c:out value="${account.getName()}" /></b>
					さん 
					<br> <br>
					<small><a href="/PekotterBetaAWS/Logout"> ログアウト</a></small>
				</p>

				<br>
				<form action="/PekotterBetaAWS/Main" method="post">
					<textarea name="text" placeholder="いま何してるぺこ？"></textarea>
					<br>
					<input type="submit" class="button" value="ぽえみんぐぺこ">
				</form>
				<c:if test="${not empty errorMsg}">
					<p>${errorMsg}</p>
				</c:if>
				<br>
				<p>
					<a href="/PekotterBetaAWS/Main">更新ぺこ</a>
				</p>
			</aside>


			<article>
				<table id="maintable" >
					<c:forEach var="tweet" items="${tweetList}">
						<tr>
							<th  class="td-css"><c:out value="${tweet.getUserName()}" /> :</th>
							<td  class="td-css"><small><c:out value="${tweet.getTime()}" /></small></td>
						</tr>
						<tr>
							<td colspan="2"><c:out
									value="${tweet.getText()}" /></td>
						</tr>
						<tr>
							<td><a href="/PekotterBetaAWS/GoodNumServlet?id=${tweet.getId()}">いいぺこ♡</a> 
							<c:out value="${tweet.getGoodNum()}" /></td>
						</tr>
						<c:if test="${tweet.getUserName() == account.getName() 
									&& tweet.getUser_id() == account.getUserId()}">
						<tr>
							<td><small><a href="/PekotterBetaAWS/EditTweetServlet?id=${tweet.getId()}">編集</a></small></td>
							<td><small><a href="/PekotterBetaAWS/DeleteTweetServlet?id=${tweet.getId()}">削除</a></small></td>
						</tr>
						</c:if>
					</c:forEach>
				</table>
				<br> <br>

			</article>
		</div>
	</div>
	<jsp:include page="/WEB-INF/jsp/footer.jsp" />
</body>
</html>