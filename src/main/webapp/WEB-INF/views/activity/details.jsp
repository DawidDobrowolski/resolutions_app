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
    <title>Activity details</title>
    <script src="<c:url value="/webjars/jquery/3.0.0/jquery.min.js"/>"></script>
    <script src="<c:url value="/webjars/bootstrap/4.3.1/js/bootstrap.min.js"/>"></script>
    <link href="<c:url value="/webjars/bootstrap/4.3.1/css/bootstrap.min.css"/>" rel="stylesheet">
    <link rel="stylesheet" href="<c:url value="/resources/css/main.css"/>">
    <link href="/webjars/font-awesome/5.9.0/css/all.css" rel="stylesheet"></head>
<body>
<%@include file="../fragments/header.jspf"%>

<div class="container">
    <header><h2>Activity details
    </h2></header>
    <div class="card mt-3">
        <div class="card-body">
            <div table-responsive>
                <table border="3" class="table font-weight-bold">
                    <tr class="text-center">
                        <td class="align-middle table-dark" style="width: 12%">
                            <i class="fas fa-audio-description fa-2x"></i>
                        </td>
                        <td class="align-middle table-secondary" style="width: 25%">Name</td>
                        <td class="align-middle">${activity.userResolution.name}</td>
                    </tr>
                    <tr class="text-center">
                        <td class="align-middle table-dark">
                            <i class="fas fa-biking fa-2x"></i>&nbsp;
                            <i class="fas fa-book fa-2x"></i>
                        </td>
                        <td class="align-middle table-secondary">Resolution type</td>
                        <td class="align-middle">${activity.userResolution.resolution.name}</td>
                    </tr>
                    <tr class="text-center">
                        <td class="align-middle table-dark">
                            <i class="far fa-calendar-alt fa-2x"></i>
                        </td>
                        <td class="align-middle table-secondary">Date</td>
                        <td class="align-middle">${activity.date}</td>
                    </tr>
                    <tr class="text-center">
                        <td class="align-middle table-dark">
                            <i class="far fa-calendar-check fa-2x"></i>
                        </td>
                        <td class="align-middle table-secondary">Units of activity</td>
                        <td class="align-middle">${activity.unitsOfActivity} ${activity.userResolution.resolution.unit}</td>
                    </tr>
                    <tr class="text-center">
                        <td class="align-middle table-dark">
                            <i class="fas fa-align-justify fa-2x"></i>
                        </td>
                        <td class="align-middle table-secondary">Note</td>
                        <td class="align-middle">${activity.note}</td>
                    </tr>
                </table>
            </div>
            <div class="text-center">
            <a href="/activity/add" class="btn btn-add btn-lg">Add new activity</a>
            </div>
        </div>
    </div>

</div>





<%--<h3>Name: </h3>${activity.userResolution.name}--%>
<%--<h3>Resolution type: </h3>${activity.userResolution.resolution.name}--%>
<%--<h3>Date: </h3>${activity.date}--%>
<%--<h3>Units of activity: </h3>${activity.unitsOfActivity}--%>
<%--<h3>Note: </h3>${activity.note}--%>
<%--<br><br>--%>
<%--<c:if test="${activity.userResolution.active}">--%>
<%--<a href="/activity/add/${activity.userResolution.id}">Add new activity</a><br>--%>
<%--</c:if>--%>
<%--<a href="/resolution/details/${activity.userResolution.id}">Show resolution details</a><br>--%>
<%--<a href="/activity/dashboard">Show activities dashboard</a><br>--%>
<%--<a href="/resolution/dashboard">Show resolution dashboard</a>--%>
<br>
<%@include file="../fragments/footer.jspf"%>

</body>
</html>
