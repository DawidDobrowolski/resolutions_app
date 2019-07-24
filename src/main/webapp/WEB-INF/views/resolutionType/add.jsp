<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: dawid
  Date: 7/24/19
  Time: 8:20 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Resolution form</title>
    <script src="<c:url value="/webjars/jquery/3.0.0/jquery.min.js"/>"></script>
    <script src="<c:url value="/webjars/bootstrap/4.3.1/js/bootstrap.min.js"/>"></script>
    <link href="<c:url value="/webjars/bootstrap/4.3.1/css/bootstrap.min.css"/>" rel="stylesheet">
    <link rel="stylesheet" href="<c:url value="/resources/css/main.css"/>"></head></head>
<body>
<%@include file="../fragments/header.jspf" %>

<form:form modelAttribute="resolution" action="/resolutionType/add" method="post">
    <div class="container">
         <header><h2>Resolution form</h2></header>
        <div class="card">
            <div class="card-body">
                <div class="form-group">
                    <form:hidden path="id"/>
                    <label>Name:</label>
                    <form:input type="text" path="name" class="form-control"/><br>
                    <form:errors path="name" element="div class='alert alert-danger'"/>
                </div>
                <div class="form-group">
                    <label>Unit:</label>
                    <form:input type="text" path="unit" class="form-control"/><br>
                    <form:errors path="unit" element="div class='alert alert-danger'"/>
                </div>
                <input type="submit" value="Save resolution" class="btn btn-submit">
            </div>
        </div>
    </div>
</form:form>

<br>
<%@include file="../fragments/footer.jspf" %>
</body>
</html>
