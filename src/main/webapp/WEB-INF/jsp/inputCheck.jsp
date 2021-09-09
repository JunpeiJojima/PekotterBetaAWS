<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>アカウント新規作成</title>
</head>
<body>
<h1>この内容で登録していいぺこ？</h1>
<ul>
<li>ユーザーID：<c:out value="${registUser.getUserId()}"/></li>
<li>パスワード：<c:out value="${registUser.getPass()}"/></li>
<li>メールアドレス：<c:out value="${registUser.getMail()}"/></li>
<li>おなまえ：<c:out value="${registUser.getName()}"/> さん</li>
<li>ねんれい：<c:out value="${registUser.getAge()}"/> 歳</li>
</ul>
<a href="/pekotterBeta1/CreateNewUser">
<input type="submit" value="登録するぺこ">
</a>
<a href="/pekotterBeta1/Registration">
<input type="submit" value="やめるぺこ">
</a>
</body>
</html>