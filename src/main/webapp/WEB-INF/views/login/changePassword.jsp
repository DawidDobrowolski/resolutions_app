<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: dawid
  Date: 7/24/19
  Time: 4:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Change password</title>
    <script src="<c:url value="/webjars/jquery/3.0.0/jquery.min.js"/>"></script>
    <script src="<c:url value="/webjars/bootstrap/4.3.1/js/bootstrap.min.js"/>"></script>
    <link href="<c:url value="/webjars/bootstrap/4.3.1/css/bootstrap.min.css"/>" rel="stylesheet">
    <link rel="stylesheet" href="<c:url value="/resources/css/main.css"/>"></head></head>
<body>
<%@include file="../fragments/header.jspf" %>

<form:form modelAttribute="user" method="post">
    <div class="container">
        <header><h2>Change password</h2></header>
        <div class="card">
            <div class="card-body">
                <div class="form-group">
                    <label>Current password:</label>
                    <input type="password" name="currentPassword" class="form-control"/><br>
                    <c:if test="${currentFalse}">
                    <div class="alert alert-danger">current password is not correct</div>
                    </c:if>
                </div>
                <div class="form-group">
                    <label>New password:</label>
                    <form:input type="password" path="password" class="form-control"/><br>
                    <form:errors path="password" element="div class='alert alert-danger'"/>
                </div>
                <div class="form-group">
                    <label>Confirm new password:</label>
                    <input type="password" name="confirmPassword" class="form-control"/><br>
                </div>
                <input type="submit" value="Change password" class="btn btn-submit">
            </div>
        </div>
    </div>
</form:form>

<br>
<%@include file="../fragments/footer.jspf" %>
</body>
</html>
