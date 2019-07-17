<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <title>Register</title>
    <script src="<c:url value="/webjars/jquery/3.0.0/jquery.min.js"/>"></script>
    <script src="<c:url value="/webjars/bootstrap/4.3.1/js/bootstrap.min.js"/>"></script>
    <link href="<c:url value="/webjars/bootstrap/4.3.1/css/bootstrap.min.css"/>" rel="stylesheet">
    <link rel="stylesheet" href="<c:url value="/resources/css/main.css"/>">
</head>
<body>
<%@include file="../fragments/header.jspf" %>

<form:form modelAttribute="user" method="post">
    <div class="container">
        <header><h2>Register</h2></header>
        <div class="card">
            <div class="card-body">
                <div class="form-group">
                    <label>First Name:</label>
                    <form:input type="text" path="firstName" class="form-control"/><br>
                    <form:errors path="firstName" element="div"/>
                </div>
                <div class="form-group">
                    <label>Last Name:</label>
                    <form:input type="text" path="lastName" class="form-control"/><br>
                    <form:errors path="lastName" element="div"/>
                </div>
                <div class="form-group">
                    <label>E-mail:</label>
                    <form:input type="email" path="email" class="form-control"/><br>
                    <form:errors path="email" element="div"/>
                </div>
                <div class="form-group">
                    <label>Password:</label>
                    <form:input type="password" path="password" class="form-control"/><br>
                    <form:errors path="password" element="div"/>
                </div>
                <div class="form-group">
                    <label>Confirm password:</label>
                    <input type="password" name="confirmPassword" class="form-control"/><br>
                </div>
                <input type="submit" value="Register" class="btn btn-submit">
            </div>
        </div>
    </div>
</form:form>


<%--    <h2>Register</h2>--%>

<%--    <form:form modelAttribute="user" method="post">--%>
<%--        <form:hidden path="id"/>--%>
<%--        First Name: <form:input type="text" path="firstName"/><br>--%>
<%--        <form:errors path="firstName" element="div"/>--%>
<%--        Last Name: <form:input type="text" path="lastName"/><br>--%>
<%--        <form:errors path="lastName" element="div"/>--%>
<%--        E-mail: <form:input type="email" path="email"/><br>--%>
<%--        <form:errors path="email" element="div"/>--%>
<%--        Password: <form:input type="password" path="password"/><br>--%>
<%--        <form:errors path="password" element="div"/>--%>
<%--        Confirm password: <input type="password" name="confirmPassword"/><br>--%>
<%--        <input type="submit" value="Register"/>--%>
<%--    </form:form>--%>

<%@include file="../fragments/footer.jspf" %>
</body>
</html>
