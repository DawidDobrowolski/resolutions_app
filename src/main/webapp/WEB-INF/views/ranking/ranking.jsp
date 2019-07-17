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
    <title>Title</title>
</head>
<body>
<%@include file="../fragments/header.jspf" %>
<h2>RANKING</h2>
<h3>RANKING FOR LAST 30 DAYS FOR FOLLOWING RESOLUTION: ${resolution.name}</h3>
<table border="1">
    <tr>
        <th>User</th>
        <th>Units made</th>
    </tr>
    <c:forEach items="${userResolutionRankings}" var="userResolutionRanking">
        <tr>
            <td>${userResolutionRanking.user.name}</td>
            <td>${userResolutionRanking.sumUnits}</td>
        </tr>
    </c:forEach>
</table>


<%@include file="../fragments/footer.jspf"%>
</body>
</html>
