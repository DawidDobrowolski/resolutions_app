<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: dawid
  Date: 7/12/19
  Time: 1:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Tweeter register</title>
</head>
<body>
<h2>Register</h2>

<form:form  modelAttribute="user" method="post">
    <form:hidden path="id" />
    First Name: <form:input type="text" path="firstName"/><br>
    <form:errors path="firstName" element="div"/>
    Last Name: <form:input type="text" path="lastName"/><br>
    <form:errors path="lastName" element="div"/>
    E-mail: <form:input type="email" path="email"/><br>
    <form:errors path="email" element="div"/>
    Password: <form:input type="password" path="password"/><br>
    <form:errors path="password" element="div"/>
    Confirm password: <input type="password" name="confirmPassword"/><br>
    <input type="submit" value="Register"/>
</form:form>
</body>
</html>
