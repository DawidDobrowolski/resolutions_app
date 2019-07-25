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
    <title>Login</title>
    <script src="<c:url value="/webjars/jquery/3.0.0/jquery.min.js"/>"></script>
    <script src="<c:url value="/webjars/bootstrap/4.3.1/js/bootstrap.min.js"/>"></script>
    <link href="<c:url value="/webjars/bootstrap/4.3.1/css/bootstrap.min.css"/>" rel="stylesheet">
    <link rel="stylesheet" href="<c:url value="/resources/css/main.css"/>">
</head>
<body>
<%@include file="../fragments/header.jspf" %>


<form method="post">
    <div class="container">
        <header><h2>Login</h2></header>
        <c:if test="${success == false}">
            <div class="alert alert-danger" role="alert">
                <p>Login failed. Email or password is incorrect.</p>
            </div>
        </c:if>
        <div class="card">
            <div class="card-body">
                <div class="form-group">
                    <label>Email:</label>
                    <input type="email" name="email" class="form-control"/><br>
                </div>
                <div class="form-group">
                    <label>Password:</label>
                    <input type="password" name="password" class="form-control"/><br>
                </div>
                <input type="submit" value="Login" class="btn btn-submit">
            </div>
        </div>
    </div>
</form>
<br>
<%@include file="../fragments/footer.jspf" %>

</body>
</html>
