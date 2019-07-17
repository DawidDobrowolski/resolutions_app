<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: dawid
  Date: 7/16/19
  Time: 10:59 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@include file="../fragments/header.jspf"%>

<form:form method="post" action="/activity/add" modelAttribute="activity">
    <form:hidden path="id" />
    Resolution: <br><form:select path="userResolution" items="${userResolutions}" itemLabel="name" itemValue="id"/><br>
    Date: <form:input type="date" path="date" />
    <form:errors path="date"/><br>
    Units of activity: <form:input type="number" path="unitsOfActivity"/>
    <form:errors path="unitsOfActivity"/><br>
    Note: <br><form:textarea path="note" rows="3" cols="20"/>
    <form:errors path="note"/><br>
    <input type="submit" value="Save"/>
</form:form>
<%@include file="../fragments/footer.jspf"%>


</body>
</html>
