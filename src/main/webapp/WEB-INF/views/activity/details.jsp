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
    <title>Title</title>
</head>
<body>
<%@include file="../fragments/header.jspf"%>
<h3>Name: </h3>${activity.userResolution.name}
<h3>Resolution type: </h3>${activity.userResolution.resolution.name}
<h3>Date: </h3>${activity.date}
<h3>Units of activity: </h3>${activity.unitsOfActivity}
<h3>Note: </h3>${activity.note}
<br><br>
<c:if test="${activity.userResolution.active}">
<a href="/activity/add/${activity.userResolution.id}">Add new activity</a><br>
</c:if>
<a href="/resolution/details/${activity.userResolution.id}">Show resolution details</a><br>
<a href="/activity/dashboard">Show activities dashboard</a><br>
<a href="/resolution/dashboard">Show resolution dashboard</a>

<%@include file="../fragments/footer.jspf"%>

</body>
</html>
