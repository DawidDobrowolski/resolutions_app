<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: dawid
  Date: 7/17/19
  Time: 10:39 AM
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
    <script type="text/javascript" src="<c:url value="/resources/js/ranking.js" />"></script>
</head>

<body>
<%@include file="../fragments/header.jspf" %>
<input type="hidden" id="dashboardCharts" value='${dashboardCharts}'/>

<div class="container">
    <header><h2>Ranking for resolution type: ${resolution.name}</h2></header>
    <div class="chart-title">Units of activities</div>
    <div id="chart_div" class="chart-title d-flex justify-content-center" ></div>
    <div class="card">
        <div class="card-body">
            <div table-responsive>
                <table border="1" class="table table-hover table-striped">
                    <thead class="text-center table-primary">
                    <tr>
                        <th class="align-middle" style="width:45%">User</th>
                        <th class="align-middle">Units completed</th>
                    </tr>
                    </thead>
                    <c:forEach items="${userResolutionRankings}" var="userResolutionRanking">
                        <tr class="text-center">
                            <td class="align-middle">${userResolutionRanking.user}</td>
                            <td class="align-middle">${userResolutionRanking.sumUnits} ${userResolutionRanking.unitName}</td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </div>

</div>


<%--<h2>RANKING</h2>--%>
<%--<h3>RANKING FOR LAST 30 DAYS FOR FOLLOWING RESOLUTION: ${resolution.name}</h3>--%>
<%--<table border="1">--%>
<%--    <tr>--%>
<%--        <th>User</th>--%>
<%--        <th>Units made</th>--%>
<%--    </tr>--%>
<%--    <c:forEach items="${userResolutionRankings}" var="userResolutionRanking">--%>
<%--        <tr>--%>
<%--            <td>${userResolutionRanking.user.name}</td>--%>
<%--            <td>${userResolutionRanking.sumUnits}</td>--%>
<%--        </tr>--%>
<%--    </c:forEach>--%>
<%--</table>--%>

<br>
<%@include file="../fragments/footer.jspf"%>
</body>
</html>
