<%--
  Created by IntelliJ IDEA.
  User: jamesche
  Date: 2017/4/27
  Time: 20:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>List</title>
</head>
<body>
<h1>This is the list page.</h1><br>
<h2>
    Welecome: <shiro:principal></shiro:principal>

</h2>
<shiro:hasRole name="admin">
    <a href="admin.jsp">admin</a><br>
</shiro:hasRole>
<shiro:hasRole name="user">
    <a href="user.jsp">user</a><br>
</shiro:hasRole>

<a href="/shiro/testShiroService">Test ShrioService</a> <br>


<a href="/shiro/logout">logout</a>
</body>
</html>
