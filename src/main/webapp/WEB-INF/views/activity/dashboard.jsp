<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: dawid
  Date: 7/16/19
  Time: 4:16 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@include file="../fragments/header.jspf"%>

<h2>DASHBOARD</h2>
<h3>ACTIVITIES</h3>
<table border="1">
    <tr>
        <th>Name</th>
        <th>Resolution type</th>
        <th>Date</th>
        <th>Units of activity</th>
        <th>Note</th>
        <th>Action</th>
    </tr>
    <c:forEach items="${activities}" var="activity">
        <tr>
            <td>${activity.userResolution.name}</td>
            <td>${activity.userResolution.resolution.name}</td>
            <td>${activity.date}</td>
            <td>${activity.unitsOfActivity}</td>
            <td>${activity.note}</td>
            <td>
                <a href="/activity/details/${activity.id}">Details</a>
                <a href="/activity/edit/${activity.id}">Edit</a>
                <a href="/activity/delete/${activity.id}">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
<a href="/activity/add">Add new activity</a><br>
<a href="/resolution/add">Add new resolution</a>
<br><br>
<a href="/resolution/dashboard">Show resolution dashboard</a>
<%@include file="../fragments/footer.jspf"%>

</body>
</html>
