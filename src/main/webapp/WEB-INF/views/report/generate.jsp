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
    <title>Report form</title>
    <script src="<c:url value="/webjars/jquery/3.0.0/jquery.min.js"/>"></script>
    <script src="<c:url value="/webjars/bootstrap/4.3.1/js/bootstrap.min.js"/>"></script>
    <link href="<c:url value="/webjars/bootstrap/4.3.1/css/bootstrap.min.css"/>" rel="stylesheet">
    <link rel="stylesheet" href="<c:url value="/resources/css/main.css"/>">
    <link href="/webjars/font-awesome/5.9.0/css/all.css" rel="stylesheet">
</head>
<body>
<%@include file="../fragments/header.jspf" %>
<div class="container">

    <header><h2>Generate report <i class="fas fa-chart-line fa-1x" style="margin-left: 10px"></i></h2></header>

    <form method="post" action="/report/generate">
        <div class="card">
            <div class="card-body">
                <c:if test="${wrongDate}">
                    <div class="alert alert-danger" role="alert">
                        <p>Please input correct dates for report</p>
                    </div>
                </c:if>
                <div class="form-group">
                    <label>Report from:</label>
                    <input type="date" name="from" class="form-control"/><br>
                </div>
                <div class="form-group">
                    <label>Report to:</label>
                    <input type="date" name="to" class="form-control"/><br>
                </div>
                <input type="submit" value="Generate report" class="btn btn-submit">
            </div>
        </div>
    </form>

</div>

<br>
<%@include file="../fragments/footer.jspf" %>
</body>
</html>
