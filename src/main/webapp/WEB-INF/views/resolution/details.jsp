<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: dawid
  Date: 7/16/19
  Time: 12:55 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@include file="../fragments/header.jspf"%>
<h3>Name: </h3>${userResolution.resolution.name}
<h3>Start date: </h3>${userResolution.startDate}
<h3>Weekly plan: </h3>${userResolution.weeklyPlan}
<h3>Activity for last 7 days: </h3>${userResolution.lastActivitiesUnits}
<h3>Resolution unit: </h3>${userResolution.resolution.unit}
<h3>Status: </h3>${userResolution.active}
<h3>E-mail reminder: </h3>${userResolution.emailReminder}
<h3>Description: </h3>${userResolution.description}
<br>
<h3>Activities: </h3>

<table border="1">
    <tr>
        <th>Date</th>
        <th>Units of activity</th>
        <th>Note</th>
        <th>Action</th>
    </tr>
    <c:forEach items="${userResolution.activities}" var="activity">
        <tr>
            <td>${activity.date}</td>
            <td>${activity.unitsOfActivity}</td>
            <td>${activity.note}</td>
            <td>
                <a href="/action/details/${activity.id}">Details</a>
                <a href="/action/edit/${activity.id}">Edit</a>
                <a href="/action/delete/${activity.id}">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>

<a href="/action/add">Add new activity</a>


<%@include file="../fragments/footer.jspf"%>



</body>
</html>
