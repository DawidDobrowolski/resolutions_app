<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--
  Created by IntelliJ IDEA.
  User: dawid
  Date: 7/19/19
  Time: 11:58 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Top</title>
    <script src="<c:url value="/webjars/jquery/3.0.0/jquery.min.js"/>"></script>
    <script src="<c:url value="/webjars/bootstrap/4.3.1/js/bootstrap.min.js"/>"></script>
    <link href="<c:url value="/webjars/bootstrap/4.3.1/css/bootstrap.min.css"/>" rel="stylesheet">
    <link rel="stylesheet" href="<c:url value="/resources/css/main.css"/>">
    <link href="/webjars/font-awesome/5.9.0/css/all.css" rel="stylesheet">
<body>
<%@include file="./fragments/header.jspf" %>
<div class="container text-center">

    <header><h2>Top resolutions in 2019<i class="fas fa-globe-europe fa-1x" style="margin-left: 10px"></i></h2>
    </header>
    <div table-responsive>
        <table border="1" class="table table-striped table-top">
            <tr class="text-center">
                <td><i class="fas fa-utensils fa-2x"></i></td>
                <td class="align-middle">Diet or eat healthier</td>
                <td class="align-middle">71 %</td>
            </tr>
            <tr class="text-center">
                <td><i class="fas fa-running fa-2x"></i></td>
                <td class="align-middle">Exercise more</td>
                <td class="align-middle">65 %</td>
            </tr>
            <tr class="text-center">
                <td><i class="fas fa-weight fa-2x"></i></td>
                <td class="align-middle">Lose weight</td>
                <td class="align-middle">54 %</td>
            </tr>
            <tr class="text-center">
                <td><<i class="fas fa-hand-holding-usd fa-2x"></i></td>
                <td class="align-middle">Save more and spend less</td>
                <td class="align-middle">32 %</td>
            </tr>
            <tr class="text-center">
                <td><i class="fas fa-icons fa-2x"></i></td>
                <td class="align-middle">Learn a new skill or hobby</td>
                <td class="align-middle">26 %</td>
            </tr>
            <tr class="text-center">
                <td><i class="fas fa-smoking-ban fa-2x"></i></td>
                <td class="align-middle">Quit smoking</td>
                <td class="align-middle">21 %</td>
            </tr>
            <tr class="text-center">
                <td><i class="fas fa-book fa-2x"></i></td>
                <td class="align-middle">Read more</td>
                <td class="align-middle">17 %</td>
            </tr>
            <tr class="text-center">
                <td><i class="fas fa-briefcase fa-2x"></i></td>
                <td class="align-middle">Find another job</td>
                <td class="align-middle">16 %</td>
            </tr>
            <tr class="text-center">
                <td><i class="fas fa-wine-bottle fa-2x"></i></td>
                <td class="align-middle">Drink less alcohol</td>
                <td class="align-middle">15 %</td>
            </tr>
            <tr class="text-center">
                <td><i class="fas fa-users fa-2x"></i></td>
                <td class="align-middle">Spend more time with family and friends</td>
                <td class="align-middle">13 %</td>
                </td>
            </tr>
        </table>
    </div>

</div>
<br>
<%@include file="./fragments/footer.jspf" %>
</body>
</html>
