<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="logic.User"%>
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
<h2>ぺこった～ログイン</h2>
<% if(loginUser != null) { %>
<p>ログインに成功したぺこ！</p>
<p>こんぺこ！<%= loginUser.getName() %>さん</p>
<a href="/PekotterBetaAWS/Main">つぶやき画面へGOぺこ！</a>
<% } else { %>
<p>ログイン失敗ぺこ…</p>
<a href="/PekotterBetaAWS/">TOPへ戻るぺこ</a>
<% } %>
</body>
</html>