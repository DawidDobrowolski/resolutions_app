<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: dawid
  Date: 7/15/19
  Time: 10:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form:form method="post" action="/resolution/add" modelAttribute="userResolution">
    <form:hidden path="id" />
    Resolution: <br><form:select path="resolution" items="${resolutions}" itemLabel="name" itemValue="id"/>
    <form:errors path="resolution"/><br>
    Name: <form:input type="text" path="name"/>
    <form:errors path="name"/><br>
    Start date: <form:input type="date" path="startDate"/>
    <form:errors path="startDate"/><br>
    Description: <br><form:textarea path="description" rows="3" cols="20"/>
    <form:errors path="description"/><br>
    Weekly plan: <form:input type="number" path="weeklyPlan"/>
    <form:errors path="weeklyPlan"/><br>
    E-mail remainder: <form:checkbox path="emailReminder"/>
    <form:errors path="emailReminder"/><br>
    Is active: <form:checkbox path="active"/>
    <form:errors path="active"/><br>
    <input type="submit" value="Save"/>
</form:form>
</body>
</html>
