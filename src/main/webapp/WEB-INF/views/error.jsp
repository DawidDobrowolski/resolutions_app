<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: dawid
  Date: 13.08.2019
  Time: 12:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error</title>
    <script src="<c:url value="/webjars/jquery/3.0.0/jquery.min.js"/>"></script>
    <script src="<c:url value="/webjars/bootstrap/4.3.1/js/bootstrap.min.js"/>"></script>
    <link href="<c:url value="/webjars/bootstrap/4.3.1/css/bootstrap.min.css"/>" rel="stylesheet">
    <link rel="stylesheet" href="<c:url value="/resources/css/main.css"/>"></head>
<link href="/webjars/font-awesome/5.9.0/css/all.css" rel="stylesheet">
<body>
<div class="index-image">
    <div class="jumbotron" style="background-color: rgba(0,0,0,0)">
        <div style="vertical-align: middle" class="container card mt-3 col-md-6 text-center transparent ">
            <div class="card-body">
                <h1>Error page</h1>
                <h1 style="text-shadow: 0px 0px 10px #000000; color: yellow"><i class="fas fa-exclamation-triangle fa-6x"></i> </h1>
                <br>
                <a class="btn btn-submit btn-lg" href="/">Return to home page</a>
            </div>
        </div>
    </div>
</div>
</body>
</html>
