<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ぺこった～</title>
</head>
<body>
<h1>ぺこった～ログイン</h1>
<c:choose>
<c:when test="${registResult}">
<p>新規アカウント作成に成功したぺこ！</p>
<a href="/pekotterBeta1/LoginServlet">ログイン画面へGOぺこ！</a>
</c:when>
<c:otherwise>
<p>ログイン失敗ぺこ…</p>
<p>お手数ですが、最初から登録よろぺこ</p>
<a href="/pekotterBeta1/WelcomServlet">TOPへ戻るぺこ</a>
</c:otherwise>
</c:choose>
</body>
</html>