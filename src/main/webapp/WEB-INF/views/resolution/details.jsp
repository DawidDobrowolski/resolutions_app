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
    <script type="text/javascript" src="<c:url value="/resources/js/details.js" />"></script></head>
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
<br>
<%@include file="../fragments/footer.jspf" %>


</body>
</html>
