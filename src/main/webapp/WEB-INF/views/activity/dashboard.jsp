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
    <title>Activity DASHBOARD</title>
    <script src="<c:url value="/webjars/jquery/3.0.0/jquery.min.js"/>"></script>
    <script src="<c:url value="/webjars/bootstrap/4.3.1/js/bootstrap.min.js"/>"></script>
    <link href="<c:url value="/webjars/bootstrap/4.3.1/css/bootstrap.min.css"/>" rel="stylesheet">
    <link rel="stylesheet" href="<c:url value="/resources/css/main.css"/>">
    <link href="/webjars/font-awesome/5.9.0/css/all.css" rel="stylesheet">
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/activityDashboard.js" />"></script></head>
<body>
<%@include file="../fragments/header.jspf"%>

<input type="hidden" id="dashboardCharts" value='${dashboardCharts}'/>

<div class="container">

    <header><h2>Activities DASHBOARD</h2></header>
    <div class="chart-title">Activities divided by resolution type</div>
    <div id="chart_div"></div>

    <div class="card">
        <div class="card-body text-center">
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
                        <th>Date</th>
                        <th>Units of activity</th>
                        <th style="width:30%">Action</th>
                    </tr>
                    </thead>
                    <c:forEach items="${activities}" var="activity">
                        <tr class="text-center">
                            <td class="align-middle">${activity.userResolution.name}</td>
                            <td class="align-middle">${activity.userResolution.resolution.name}</td>
                            <td class="align-middle">${activity.date}</td>
                            <td class="align-middle">${activity.unitsOfActivity} ${activity.userResolution.resolution.unit}</td>
                            <td class="align-middle">
                                <a href="/activity/details/${activity.id}" class="btn btn-secondary btn-sm">Details</a>
                                <a href="/activity/edit/${activity.id}" class="btn btn-warning btn-sm">Edit</a>
                                <a href="/activity/delete/${activity.id}" class="btn btn-danger btn-sm"
                                   data-toggle="modal"
                                   data-target="#deleteModal"
                                   data-activity-id="${activity.id}"
                                   data-resolution-name="${activity.userResolution.name}"
                                   title="Delete activity">Delete</a>
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
                <p>Are you sure you want to delete activity for resolution <strong><span id="resolutionName"></span></strong>?</p>
            </div>
            <div class="modal-footer">
                <button id="deleteId" type="button" class="btn btn-primary btn-submit">Confirm</button>
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>











<%--<h2>DASHBOARD</h2>--%>
<%--<h3>ACTIVITIES</h3>--%>
<%--<table border="1">--%>
<%--    <tr>--%>
<%--        <th>Name</th>--%>
<%--        <th>Resolution type</th>--%>
<%--        <th>Date</th>--%>
<%--        <th>Units of activity</th>--%>
<%--        <th>Note</th>--%>
<%--        <th>Action</th>--%>
<%--    </tr>--%>
<%--    <c:forEach items="${activities}" var="activity">--%>
<%--        <tr>--%>
<%--            <td>${activity.userResolution.name}</td>--%>
<%--            <td>${activity.userResolution.resolution.name}</td>--%>
<%--            <td>${activity.date}</td>--%>
<%--            <td>${activity.unitsOfActivity}</td>--%>
<%--            <td>${activity.note}</td>--%>
<%--            <td>--%>
<%--                <a href="/activity/details/${activity.id}">Details</a>--%>
<%--                <a href="/activity/edit/${activity.id}">Edit</a>--%>
<%--                <a href="/activity/delete/${activity.id}">Delete</a>--%>
<%--            </td>--%>
<%--        </tr>--%>
<%--    </c:forEach>--%>
<%--</table>--%>
<%--<a href="/activity/add">Add new activity</a><br>--%>
<%--<a href="/resolution/add">Add new resolution</a>--%>
<%--<br><br>--%>
<%--<a href="/resolution/dashboard">Show resolution dashboard</a>--%>
<br>
<%@include file="../fragments/footer.jspf"%>

</body>
</html>
