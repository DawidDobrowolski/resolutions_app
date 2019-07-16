<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: dawid
  Date: 7/15/19
  Time: 3:53 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
<%--    <style>--%>
<%--        button {--%>
<%--            float: right;--%>
<%--        }--%>
<%--    </style>--%>
    <script src="<c:url value="/webjars/jquery/3.0.0/jquery.min.js"/>"></script>
    <script src="<c:url value="/webjars/bootstrap/4.3.1/js/bootstrap.min.js"/>"></script>
    <link href="<c:url value="/webjars/bootstrap/4.3.1/css/bootstrap.min.css"/>" rel="stylesheet">
    <link rel="stylesheet" href="../../resources/css/main.css">
</head>
<body>
<c:choose>
    <c:when test="${empty email}">
        <button class="login" onclick="javascript:document.location.href='/login/register'">Register</button>
        <button class="login" onclick="javascript:document.location.href='/login/login'">Login</button>
        <h2>Welcome</h2>
    </c:when>
    <c:otherwise>
        <button class="login" onclick="javascript:document.location.href='/login/logout'">Logout</button>
        <h2>Welcome, <c:out value="${firstName}"/> </h2>
    </c:otherwise>

</c:choose>
</body>
</html>
