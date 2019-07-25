<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <title>Activity form</title>
    <script src="<c:url value="/webjars/jquery/3.0.0/jquery.min.js"/>"></script>
    <script src="<c:url value="/webjars/bootstrap/4.3.1/js/bootstrap.min.js"/>"></script>
    <link href="<c:url value="/webjars/bootstrap/4.3.1/css/bootstrap.min.css"/>" rel="stylesheet">
    <link rel="stylesheet" href="<c:url value="/resources/css/main.css"/>">
    <script type="text/javascript" src="<c:url value="/resources/js/activityAdd.js" />"></script>
</head>
<body>
<%@include file="../fragments/header.jspf" %>
<input type="hidden" id="unitsNames" value='${unitsNames}'/>
<form:form method="post" action="/activity/add" modelAttribute="activity">
    <div class="container">
        <header><h2>Activity form</h2></header>
        <div class="card">
            <div class="card-body">
                <div class="form-group">
                    <form:hidden path="id"/>
                    <label>Resolution:</label>
                    <br><form:select id="selectResolution" path="userResolution" items="${userResolutions}"
                                     itemLabel="name" itemValue="id" class="form-control"/>
                    <form:errors path="userResolution" element="div class='alert alert-danger'"/><br>
                </div>
                <div class="form-group">
                    <label>Date:</label>
                    <form:input type="date" path="date" class="form-control"/>
                    <form:errors path="date" element="div class='alert alert-danger'"/><br>
                </div>
                <div class="form-group">
                    <label>Units of activity:</label>
                    <div class="input-group">
                        <form:input type="number" path="unitsOfActivity" class="form-control"/>
                        <div class="input-group-append">
                            <span class="input-group-text" id="unitInput"></span>
                        </div>
                    </div>
                    <form:errors path="unitsOfActivity" element="div class='alert alert-danger'"/><br>
                </div>
                <div class="form-group">
                    <label>Note:</label>
                    <br><form:textarea path="note" rows="3" cols="20" class="form-control"/>
                    <form:errors path="note" element="div class='alert alert-danger'"/><br>
                </div>
                <input type="submit" value="Save" class="btn btn-submit">
            </div>
        </div>
    </div>
</form:form>
<br>
<%@include file="../fragments/footer.jspf" %>
</body>
</html>
