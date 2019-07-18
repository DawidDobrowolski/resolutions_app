<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: dawid
  Date: 7/17/19
  Time: 1:09 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ranking chose</title>
    <script src="<c:url value="/webjars/jquery/3.0.0/jquery.min.js"/>"></script>
    <script src="<c:url value="/webjars/bootstrap/4.3.1/js/bootstrap.min.js"/>"></script>
    <link href="<c:url value="/webjars/bootstrap/4.3.1/css/bootstrap.min.css"/>" rel="stylesheet">
    <link rel="stylesheet" href="<c:url value="/resources/css/main.css"/>">
    <link href="/webjars/font-awesome/5.9.0/css/all.css" rel="stylesheet"></head>
<body>
<%@include file="../fragments/header.jspf" %>

<div class="container">

    <header><h2>Chose resolution for ranking<i class="fas fa-trophy fa-1x" style="margin-left: 10px"></i></h2></header>
    <div class="card">
        <div class="card-body">
            <c:forEach items="${resolutions}" var="resolution">
                <a href="/ranking/ranking/${resolution.id}" class="btn btn-submit btn-lg btn-block">${resolution.name}</a><br>
            </c:forEach>
        </div>
    </div>

</div>

<br>
<%@include file="../fragments/footer.jspf"%>

</body>
</html>
