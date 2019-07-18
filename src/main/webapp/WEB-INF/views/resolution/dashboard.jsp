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
    <title>Resolution DASHBOARD</title>
    <script src="<c:url value="/webjars/jquery/3.0.0/jquery.min.js"/>"></script>
    <script src="<c:url value="/webjars/bootstrap/4.3.1/js/bootstrap.min.js"/>"></script>
    <link href="<c:url value="/webjars/bootstrap/4.3.1/css/bootstrap.min.css"/>" rel="stylesheet">
    <link rel="stylesheet" href="<c:url value="/resources/css/main.css"/>">
    <link href="/webjars/font-awesome/5.9.0/css/all.css" rel="stylesheet">
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/resolutionDashboard.js" />"></script>
</head>
<body>
<%@include file="../fragments/header.jspf" %>
<input type="hidden" id="dashboardCharts" value='${dashboardCharts}'/>

<div class="container">

    <header><h2>Resolutions DASHBOARD</h2></header>
    <div class="chart-title">Fulfilment of resolutions during last 7 days</div>
    <div id="chart_div"></div>

    <div class="card">
        <div class="card-body text-center">
            <a href="/resolution/add" class="btn btn-submit btn-lg" style="align-self: center">Add new resolution</a>
            <a href="/activity/add" class="btn btn-submit btn-lg">Add new activity</a>
        </div>
    </div>

    <div class="card mt-3">
        <div class="card-body">
            <div table-responsive>
                <table border="1" class="table table-hover table-striped">
                    <thead class="thead-dark text-center">
                    <tr>
                        <th>Name</th>
                        <th>Type of resolution</th>
                        <th>Start date</th>
                        <th>Weekly plan</th>
                        <th>Activity for last 7 days</th>
                        <th>Status</th>
                        <th style="width:30%">Action</th>
                    </tr>
                    </thead>
                    <c:forEach items="${userResolutions}" var="userResolution">
                        <tr class="text-center">
                            <td class="align-middle">${userResolution.name}</td>
                            <td class="align-middle">${userResolution.resolution.name}</td>
                            <td class="align-middle">${userResolution.startDate}</td>
                            <td class="align-middle">${userResolution.weeklyPlan} ${userResolution.resolution.unit}</td>
                            <td class="align-middle">${userResolution.lastActivitiesUnits} ${userResolution.resolution.unit}</td>
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
                            <td class="align-middle">
                                <c:if test="${userResolution.active}">
                                    <a href="/activity/add/${userResolution.id}" class="btn btn-success btn-sm">Add
                                        activity</a>
                                </c:if>
                                <a href="/resolution/details/${userResolution.id}" class="btn btn-secondary btn-sm">Details</a>
                                <a href="/resolution/edit/${userResolution.id}" class="btn btn-warning btn-sm">Edit</a>
                                <a href="/resolution/delete/${userResolution.id}" class="btn btn-danger btn-sm"
                                   data-toggle="modal"
                                   data-target="#deleteModal"
                                   data-resolution-id="${userResolution.id}"
                                   data-resolution-name="${userResolution.name}"
                                   title="Delete resolution">Delete</a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="deleteModal" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Delete confirmation</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <p>Are you sure you want to delete resolution <strong><span id="resolutionName"></span></strong>?</p>
            </div>
            <div class="modal-footer">
                <button id="deleteId" type="button" class="btn btn-primary btn-submit">Confirm</button>
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>

<br>
<%@include file="../fragments/footer.jspf" %>
</body>
</html>
