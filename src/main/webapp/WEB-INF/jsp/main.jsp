<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>pekotter</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<h1>ぺこった～メイン</h1>
<p>
<c:out value="${account.getName()}" />さん、ログイン中ぺこ
<a href="/pekotterBeta1/Logout">さよならぺこ・・・</a>
</p>
<p><a href="/pekotterBeta1/Main">更新ぺこ</a></p>
<form action="/pekotterBeta1/Main" method="post">
<input type="text" name="text">
<input type="submit" value="ぽえみんぐぺこ">
</form>
<c:if test="${not empty errorMsg}">
<p>${errorMsg}</p>
</c:if>
<c:forEach var="tweet" items="${tweetList}">
<p><c:out value="${tweet.getUserName()}"/>:
<c:out value="${tweet.getText()}"/>
<c:out value="${tweet.getTime()}"/>
</p>
</c:forEach>
<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
</body>
</html>