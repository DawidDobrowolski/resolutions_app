<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: dawid
  Date: 7/16/19
  Time: 9:05 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@include file="../fragments/header.jspf" %>
<h2>REPORT</h2>
<h3>RESOLUTIONS FOR DATE BETWEEN ${from} AND ${to} </h3>
<table border="1">
    <tr>
        <th>Name</th>
        <th>Type of resolution</th>
        <th>Number of days</th>
        <th>Plan for set days</th>
        <th>Units in activities</th>
    </tr>
    <c:forEach items="${userResolutionReports}" var="userResolutionReport">
        <tr>
            <td>${userResolutionReport.userResolution.name}</td>
            <td>${userResolutionReport.userResolution.resolution.name}</td>
            <td>${userResolutionReport.numberOfDays}</td>
            <td>${userResolutionReport.planForSetDays}</td>
            <td>${userResolutionReport.unitsInActions}</td>
        </tr>
    </c:forEach>
</table>

<h3>Average realization of resolutions is ${userResolutionAverageRealization} %</h3>

<%@include file="../fragments/footer.jspf"%>

</body>
</html>
