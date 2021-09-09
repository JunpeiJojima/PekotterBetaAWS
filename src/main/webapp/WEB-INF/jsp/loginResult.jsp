<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="model.User"%>
<%
//セッションスコープからユーザー情報を取得
User loginUser = (User) session.getAttribute("loginUser");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ぺこった～</title>
</head>
<body>
<h1>ぺこった～ログイン</h1>
<% if(loginUser != null) { %>
<p>ログインに成功したぺこ！</p>
<p>こんぺこ！<%= loginUser.getName() %>さん</p>
<a href="/example14/Main">つぶやき画面へGOぺこ！</a>
<% } else { %>
<p>ログイン失敗ぺこ…</p>
<a href="/example14/">TOPへ戻るぺこ</a>
<% } %>
</body>
</html>