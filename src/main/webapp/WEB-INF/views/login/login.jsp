<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: dawid
  Date: 7/12/19
  Time: 2:55 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Tweeter login</title>
</head>
<body>
<h2>Login</h2>
<c:if test="${success == false}">
    <p>Login failed. Email or password is incorrect.</p>
</c:if>
<form method="post">

    Email: <input type="email" name="email" required/><br>
    Password: <input type="password" name="password" required/><br>
    <input type="submit" value="login">
</form>
</body>
</html>
