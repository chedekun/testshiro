<%--
  Created by IntelliJ IDEA.
  User: jamesche
  Date: 2017/4/26
  Time: 20:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>LOGIN</title>
</head>
<body>
<h1>This is the login page.</h1>

<form method="post" action="shiro/login">
    username: <input type="text" name="username"/>
    <br><br>
    password; <input type="password" name="password"/>
    <br><br>
    <input type="submit" value="submit"/>
    <input type="reset" value="reset"/>
</form>
</body>
</html>