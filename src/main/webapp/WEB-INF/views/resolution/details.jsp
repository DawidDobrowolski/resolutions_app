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
    <title>Resolution details</title>
    <script src="<c:url value="/webjars/jquery/3.0.0/jquery.min.js"/>"></script>
    <script src="<c:url value="/webjars/bootstrap/4.3.1/js/bootstrap.min.js"/>"></script>
    <link href="<c:url value="/webjars/bootstrap/4.3.1/css/bootstrap.min.css"/>" rel="stylesheet">
    <link rel="stylesheet" href="<c:url value="/resources/css/main.css"/>">
    <link href="/webjars/font-awesome/5.9.0/css/all.css" rel="stylesheet">
</head>
<body>
<%@include file="../fragments/header.jspf" %>
<div class="container">
    <header><h2>Resolution details</h2></header>

    <div class="card mt-3">
        <div class="card-body">
            <div table-responsive>
                <table border="3" class="table font-weight-bold">
                    <tr class="text-center">
                        <td class="align-middle table-dark" style="width: 12%">
                            <i class="fas fa-audio-description fa-2x"></i>
                        </td>
                        <td class="align-middle table-secondary" style="width: 25%">Name</td>
                        <td class="align-middle">${userResolution.name}</td>
                    </tr>
                    <tr class="text-center">
                        <td class="align-middle table-dark">
                            <i class="fas fa-biking fa-2x"></i>&nbsp;
                            <i class="fas fa-book fa-2x"></i>
                        </td>
                        <td class="align-middle table-secondary">Resolution type</td>
                        <td class="align-middle">${userResolution.resolution.name}</td>
                    </tr>
                    <tr class="text-center">
                        <td class="align-middle table-dark">
                            <i class="far fa-calendar-alt fa-2x"></i>
                        </td>
                        <td class="align-middle table-secondary">Start date</td>
                        <td class="align-middle">${userResolution.startDate}</td>
                    </tr>
                    <c:if test="${!userResolution.active}">
                        <tr class="text-center">
                            <td class="align-middle table-dark">
                                <i class="far fa-calendar-times fa-2x"></i>
                            </td>
                            <td class="align-middle table-secondary">End date</td>
                            <td class="align-middle">${userResolution.endDate}</td>
                        </tr>
                    </c:if>
                    <tr class="text-center">
                        <td class="align-middle table-dark">
                            <i class="fas fa-crosshairs fa-2x"></i>
                        </td>
                        <td class="align-middle table-secondary">Weekly plan</td>
                        <td class="align-middle">${userResolution.weeklyPlan} ${userResolution.resolution.unit}</td>
                    </tr>
                    <tr class="text-center">
                        <td class="align-middle table-dark">
                            <i class="far fa-calendar-check fa-2x"></i>
                        </td>
                        <td class="align-middle table-secondary">Activity for last 7 days</td>
                        <td class="align-middle">${userResolution.lastActivitiesUnits} ${userResolution.resolution.unit}</td>
                    </tr>
                    <tr class="text-center">
                        <td class="align-middle table-dark">
                            <i class="fas fa-info-circle fa-2x"></i>
                        </td>
                        <td class="align-middle table-secondary">Status</td>
                        <td class="align-middle">
                            <c:choose>
                                <c:when test="${userResolution.active}">
                                    <i class="fas fa-check fa-2x" style="color: green"></i>
                                </c:when>
                                <c:otherwise>
                                    <i class="fas fa-times fa-2x" style="color: red"></i>
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                    <tr class="text-center">
                        <td class="align-middle table-dark">
                            <i class="fas fa-at fa-2x"></i>
                        </td>
                        <td class="align-middle table-secondary">E-mail reminder</td>
                        <td class="align-middle">
                            <c:choose>
                                <c:when test="${userResolution.emailReminder}">
                                    <i class="fas fa-check fa-2x" style="color: green"></i>
                                </c:when>
                                <c:otherwise>
                                    <i class="fas fa-times fa-2x" style="color: red"></i>
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                    <tr class="text-center">
                        <td class="align-middle table-dark">
                            <i class="fas fa-align-justify fa-2x"></i>
                        </td>
                        <td class="align-middle table-secondary">Description</td>
                        <td class="align-middle">${userResolution.description}</td>
                    </tr>
                </table>
            </div>
            <header class="sub-table-second "><h3>
                Resolution activities <i class="fas fa-flag-checkered fa-2x"></i>
                <a href="/activity/add/${userResolution.id}" class="btn btn-add float-sm-right" style="margin-top: 20px">Add new activity</a>
            </h3></header>
            <div table-responsive>
                <table border="1" class="table table-borderless">
                    <thead class="text-center table-success">
                    <tr class="text-center">
                        <th>Date</th>
                        <th>Units of activity</th>
                        <th style="width:35%">Note</th>
                        <th style="width:25%">Action</th>
                    </tr>
                    </thead>
                    <c:forEach items="${userResolution.activities}" var="activity">
                        <tr class="text-center">
                            <td class="align-middle">${activity.date}</td>
                            <td class="align-middle">${activity.unitsOfActivity} ${userResolution.resolution.unit}</td>
                            <td class="align-middle">${activity.note}</td>
                            <td class="align-middle">
                                <a href="/activity/details/${activity.id}" class="btn btn-secondary btn-sm">Details</a>
                                <a href="/activity/edit/${activity.id}" class="btn btn-warning btn-sm">Edit</a>
                                <a href="/activity/delete/${activity.id}" class="btn btn-danger btn-sm">Delete</a>
                            </td>
                        </tr>
                    </c:forEach>


                </table>
            </div>
        </div>
    </div>

<%--    <h3>Name: </h3>${userResolution.name}--%>
<%--    <h3>Resolution type: </h3>${userResolution.resolution.name}--%>
<%--    <h3>Start date: </h3>${userResolution.startDate}--%>
<%--    <c:if test="${!userResolution.active}">--%>
<%--        <h3>End date: </h3>${userResolution.endDate}--%>
<%--    </c:if>--%>
<%--    <h3>Weekly plan: </h3>${userResolution.weeklyPlan}--%>
<%--    <h3>Activity for last 7 days: </h3>${userResolution.lastActivitiesUnits}--%>
<%--    <h3>Resolution unit: </h3>${userResolution.resolution.unit}--%>
<%--    <h3>Status: </h3>${userResolution.active}--%>
<%--    <h3>E-mail reminder: </h3>${userResolution.emailReminder}--%>
<%--    <h3>Description: </h3>${userResolution.description}--%>
<%--    <br>--%>
<%--    <h3>Activities: </h3>--%>

<%--    <table border="1">--%>
<%--        <tr>--%>
<%--            <th>Date</th>--%>
<%--            <th>Units of activity</th>--%>
<%--            <th>Note</th>--%>
<%--            <th>Action</th>--%>
<%--        </tr>--%>
<%--        <c:forEach items="${userResolution.activities}" var="activity">--%>
<%--            <tr>--%>
<%--                <td>${activity.date}</td>--%>
<%--                <td>${activity.unitsOfActivity}</td>--%>
<%--                <td>${activity.note}</td>--%>
<%--                <td>--%>
<%--                    <a href="/activity/details/${activity.id}">Details</a>--%>
<%--                    <a href="/activity/edit/${activity.id}">Edit</a>--%>
<%--                    <a href="/activity/delete/${activity.id}">Delete</a>--%>
<%--                </td>--%>
<%--            </tr>--%>
<%--        </c:forEach>--%>
<%--    </table>--%>
<%--    <c:if test="${userResolution.active}">--%>
<%--        <a href="/activity/add/${userResolution.id}">Add new activity</a>--%>
<%--    </c:if>--%>
<%--    <br><br>--%>

</div><br>
<%@include file="../fragments/footer.jspf" %>


</body>
</html>
