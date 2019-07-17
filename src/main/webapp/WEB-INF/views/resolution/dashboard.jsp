<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: dawid
  Date: 7/15/19
  Time: 6:57 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@include file="../fragments/header.jspf" %>
<h2>DASHBOARD</h2>
<h3>RESOLUTIONS</h3>
<table border="1">
    <tr>
        <th>Name</th>
        <th>Type of resolution</th>
        <th>Start date</th>
        <th>Weekly plan</th>
        <th>Activity for last 7 days</th>
        <th>Status</th>
        <th>Action</th>
    </tr>
    <c:forEach items="${userResolutions}" var="userResolution">
        <tr>
            <td>${userResolution.name}</td>
            <td>${userResolution.resolution.name}</td>
            <td>${userResolution.startDate}</td>
            <td>${userResolution.weeklyPlan}</td>
            <td>${userResolution.lastActivitiesUnits}</td>
            <td>${userResolution.active}</td>
            <td>
                <c:if test="${userResolution.active}">
                    <a href="/activity/add/${userResolution.id}">Add activity</a>
                </c:if>
                <a href="/resolution/details/${userResolution.id}">Details</a>
                <a href="/resolution/edit/${userResolution.id}">Edit</a>
                <a href="/resolution/delete/${userResolution.id}">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>

<a href="/resolution/add">Add new resolution</a><br>
<a href="/activity/add">Add new activity</a>
<br><br>
<a href="/activity/dashboard">Show activity dashboard</a><br>
<a href="/report/generate">Generate report</a><br>
<a href="/ranking/choose">Show ranking</a>


<%@include file="../fragments/footer.jspf"%>
</body>
</html>
