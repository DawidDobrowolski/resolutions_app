<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: dawid
  Date: 7/19/19
  Time: 8:14 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Tips</title>
    <script src="<c:url value="/webjars/jquery/3.0.0/jquery.min.js"/>"></script>
    <script src="<c:url value="/webjars/bootstrap/4.3.1/js/bootstrap.min.js"/>"></script>
    <link href="<c:url value="/webjars/bootstrap/4.3.1/css/bootstrap.min.css"/>" rel="stylesheet">
    <link rel="stylesheet" href="<c:url value="/resources/css/main.css"/>">
    <link href="/webjars/font-awesome/5.9.0/css/all.css" rel="stylesheet">


    <script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery-slim.min.js"><\/script>')</script>

    <script>
        Holder.addTheme('thumb', {
            bg: '#55595c',
            fg: '#eceeef',
            text: 'Thumbnail'
        });
    </script>
</head>
<body>
<%@include file="./fragments/header.jspf" %>
<div class="container text-center">

    <header><h2>Major tips for resolutions <i class="fas fa-lightbulb fa-1x" style="margin-left: 10px"></i></h2>
    </header>

    <div class="align-content-center">

        <div class="d-flex justify-content-center w-100 my-md-3 pl-md-3">
            <div class="bg-dark mr-md-3 pt-3 px-3 pt-md-1 px-md-5 text-center text-white overflow-hidden">
                <div class="my-3 py-3">
                    <h2 class="display-5">BE REALISTIC</h2>
                    <p class="lead"><i class="fas fa-registered fa-5x"></i>
                    </p>
                </div>
            </div>
            <div class="bg-dark mr-md-3 pt-3 px-3 pt-md-1 px-md-5 text-center text-white overflow-hidden">
                <div class="my-3 py-3">
                    <h2 class="display-5">PLAN AHEAD</h2>
                    <p class="lead"><i class="far fa-calendar-alt fa-5x"></i>
                    </p>
                </div>
            </div>
        </div>
        <div class="d-flex justify-content-center w-100 my-md-3 pl-md-3">
            <div class="bg-dark mr-md-3 pt-3 px-3 pt-md-1 px-md-5 text-center text-white overflow-hidden">
                <div class="my-3 py-3">
                    <h2 class="display-5">BE PRECISE</h2>
                    <p class="lead"><i class="fas fa-search fa-5x"></i>
                    </p>
                </div>
            </div>
            <div class="bg-dark mr-md-3 pt-3 px-3 pt-md-1 px-md-5 text-center text-white overflow-hidden">
                <div class="my-3 py-3">
                    <h2 class="display-5">“+” AND “-” LIST</h2>
                    <p class="lead"><i class="fas fa-balance-scale-left fa-5x"></i>
                    </p>
                </div>
            </div>
        </div>
        <div class="d-flex justify-content-center w-100 my-md-3 pl-md-3">
            <div class="bg-dark mr-md-3 pt-3 px-3 pt-md-1 px-md-5 text-center text-white overflow-hidden">
                <div class="my-3 py-3">
                    <h2 class="display-5">TALK ABOUT IT</h2>
                    <p class="lead"><i class="far fa-comment-dots fa-5x"></i>
                    </p>
                </div>
            </div>
            <div class="bg-dark mr-md-3 pt-3 px-3 pt-md-1 px-md-5 text-center text-white overflow-hidden">
                <div class="my-3 py-3">
                    <h2 class="display-5">REWARD</h2>
                    <p class="lead"><i class="fas fa-award fa-5x"></i>
                    </p>
                </div>
            </div>
        </div>
        <div class="d-flex justify-content-center w-100 my-md-3 pl-md-3">
            <div class="bg-dark mr-md-3 pt-3 px-3 pt-md-1 px-md-5 text-center text-white overflow-hidden">
                <div class="my-3 py-3">
                    <h2 class="display-5">KEEP TRYING</h2>
                    <p class="lead"><i class="fas fa-sync fa-5x"></i>
                    </p>
                </div>
            </div>
            <div class="bg-dark mr-md-3 pt-3 px-3 pt-md-1 px-md-5 text-center text-white overflow-hidden">
                <div class="my-3 py-3">
                    <h2 class="display-5">TRACK</h2>
                    <p class="lead"><i class="fas fa-chart-line fa-5x"></i>
                    </p>
                </div>
            </div>
        </div>
    </div>
</div>
<br>
<%@include file="./fragments/footer.jspf" %>
</body>
</html>
