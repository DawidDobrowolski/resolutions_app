<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
    <title>Resolution form</title>
    <script src="<c:url value="/webjars/jquery/3.0.0/jquery.min.js"/>"></script>
    <script src="<c:url value="/webjars/bootstrap/4.3.1/js/bootstrap.min.js"/>"></script>
    <link href="<c:url value="/webjars/bootstrap/4.3.1/css/bootstrap.min.css"/>" rel="stylesheet">
    <link rel="stylesheet" href="<c:url value="/resources/css/main.css"/>">
    <script type="text/javascript" src="<c:url value="/resources/js/resolutionAdd.js" />"></script>
</head>
<body>
<%@include file="../fragments/header.jspf" %>
<input type="hidden" id="unitsNames" value='${unitsNames}'/>
<form:form method="post" action="/resolution/add" modelAttribute="userResolution">
    <div class="container">
        <header><h2>Resolution form</h2></header>
        <div class="card">
            <div class="card-body">
                <div class="form-group">
                    <form:hidden path="id" />
                    <label>Resolution:</label>
                    <form:select id="selectResolution" path="resolution" items="${resolutions}" itemLabel="name" itemValue="id" class="form-control"/>
                    <form:errors path="resolution" element="div class='alert alert-danger'"/><br>
                </div>
                <div class="form-group">
                    <label>Name:</label>
                    <form:input type="text" path="name" class="form-control"/>
                    <form:errors path="name" element="div class='alert alert-danger'"/><br>
                </div>
                <div class="form-group">
                    <label>Start date:</label>
                    <form:input type="date" path="startDate" class="form-control"/>
                    <form:errors path="startDate" element="div class='alert alert-danger'"/><br>
                </div>
                <div class="form-group">
                    <label>Description:</label>
                    <br><form:textarea path="description" rows="3" cols="20" class="form-control"/>
                    <form:errors path="description" element="div class='alert alert-danger'"/><br>
                </div>
                <div class="form-group">
                    <label>Weekly plan:</label>
                    <div class="input-group">
                    <form:input type="number" path="weeklyPlan" class="form-control"/>
                    <div class="input-group-append">
                        <span class="input-group-text" id="unitInput"></span>
                    </div>
                    </div>
                    <form:errors path="weeklyPlan" element="div class='alert alert-danger'"/><br>
                </div>
                <div class="form-check">
                    <form:checkbox path="emailReminder" class="form-check-input"/>
                    <label class="form-check-label" style="font-size: x-large">E-mail remainder:</label>
                    <form:errors path="emailReminder" element="div class='alert alert-danger'"/><br>
                </div>
                <div class="form-check">
                    <form:checkbox path="active" class="form-check-input"/>
                    <label class="form-check-label" style="font-size: x-large">Is active</label>
                    <form:errors path="active" element="div class='alert alert-danger'"/><br>
                </div><br>
                <input type="submit" value="Save" class="btn btn-submit">
            </div>
        </div>
    </div>
</form:form>




<%--<form:form method="post" action="/resolution/add" modelAttribute="userResolution">--%>
<%--    <form:hidden path="id" />--%>
<%--    Resolution: <br><form:select path="resolution" items="${resolutions}" itemLabel="name" itemValue="id"/>--%>
<%--    <form:errors path="resolution"/><br>--%>
<%--    Name: <form:input type="text" path="name"/>--%>
<%--    <form:errors path="name"/><br>--%>
<%--    Start date: <form:input type="date" path="startDate"/>--%>
<%--    <form:errors path="startDate"/><br>--%>
<%--    Description: <br><form:textarea path="description" rows="3" cols="20"/>--%>
<%--    <form:errors path="description"/><br>--%>
<%--    Weekly plan: <form:input type="number" path="weeklyPlan"/>--%>
<%--    <form:errors path="weeklyPlan"/><br>--%>
<%--    E-mail remainder: <form:checkbox path="emailReminder"/>--%>
<%--    <form:errors path="emailReminder"/><br>--%>
<%--    Is active: <form:checkbox path="active"/>--%>
<%--    <form:errors path="active"/><br>--%>
<%--    <input type="submit" value="Save"/>--%>
<%--</form:form>--%>


<%@include file="../fragments/footer.jspf" %>

</body>
</html>
