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
    <title>Report</title>
    <script src="<c:url value="/webjars/jquery/3.0.0/jquery.min.js"/>"></script>
    <script src="<c:url value="/webjars/bootstrap/4.3.1/js/bootstrap.min.js"/>"></script>
    <link href="<c:url value="/webjars/bootstrap/4.3.1/css/bootstrap.min.css"/>" rel="stylesheet">
    <link rel="stylesheet" href="<c:url value="/resources/css/main.css"/>">
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/report.js" />"></script>
</head>
<body>
<%@include file="../fragments/header.jspf" %>

<input type="hidden" id="dashboardCharts" value='${dashboardCharts}'/>
<input type="hidden" id="average" value='${average}'/>

<div class="container">

    <header><h2>Resolutions report for date between ${from} and ${to} </h2></header>

    <div class="chart-title">Units of activities</div>
    <div id="chart_div3" class="chart-title d-flex justify-content-center" ></div>

    <div class="chart-title">Total fulfilment of resolutions</div>
    <div id="chart_div2" class="chart-title d-flex justify-content-center"></div>



    <div class="chart-title"><br>Fulfilment of resolutions</div>
    <div id="chart_div"></div>
    <div class="card">
        <div class="card-body text-center">
            <a href="/report/generate" class="btn btn-submit btn-lg">Generate new report</a>
        </div>
    </div>
    <div class="card">
        <div class="card-body">
            <div table-responsive>
                <table border="1" class="table table-hover table-striped">
                    <thead class="text-center table-primary">
                    <tr>
                        <th class="align-middle" style="width:22%">Name</th>
                        <th class="align-middle">Type of resolution</th>
                        <th class="align-middle"style="width:14%">Number of days with resolution</th>
                        <th class="align-middle">Plan for chosen days</th>
                        <th class="align-middle">Completed units</th>
                        <th class="align-middle">Resolution plan realization</th>
                    </tr>
                    </thead>
                    <c:forEach items="${userResolutionReports}" var="userResolutionReport">
                        <tr class="text-center">
                            <td class="align-middle">${userResolutionReport.name}</td>
                            <td class="align-middle">${userResolutionReport.resolutionType}</td>
                            <td class="align-middle">${userResolutionReport.numberOfDays}</td>
                            <td class="align-middle">${userResolutionReport.planForSetDays} ${userResolutionReport.resolutionUnit}</td>
                            <td class="align-middle">${userResolutionReport.unitsInActions} ${userResolutionReport.resolutionUnit}</td>
                            <td class="align-middle">${userResolutionReport.resolutionRealization} %</td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </div>
</div>




<%--<h2>REPORT</h2>--%>
<%--<h3>RESOLUTIONS FOR DATE BETWEEN ${from} AND ${to} </h3>--%>
<%--<table border="1">--%>
<%--    <tr>--%>
<%--        <th>Name</th>--%>
<%--        <th>Type of resolution</th>--%>
<%--        <th>Number of days</th>--%>
<%--        <th>Plan for set days</th>--%>
<%--        <th>Units in activities</th>--%>
<%--    </tr>--%>
<%--    <c:forEach items="${userResolutionReports}" var="userResolutionReport">--%>
<%--        <tr>--%>
<%--            <td>${userResolutionReport.userResolution.name}</td>--%>
<%--            <td>${userResolutionReport.userResolution.resolution.name}</td>--%>
<%--            <td>${userResolutionReport.numberOfDays}</td>--%>
<%--            <td>${userResolutionReport.planForSetDays}</td>--%>
<%--            <td>${userResolutionReport.unitsInActions}</td>--%>
<%--        </tr>--%>
<%--    </c:forEach>--%>
<%--</table>--%>

<%--<h3>Average realization of resolutions is ${userResolutionAverageRealization} %</h3>--%>
<br>
<%@include file="../fragments/footer.jspf"%>

</body>
</html>
