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
    <title>Title</title>
</head>
<body>
<%@include file="../fragments/header.jspf" %>

<h2>Show ranking for one of the following resolution: </h2>

<c:forEach items="${resolutions}" var="resolution">
    <a href="/ranking/ranking/${resolution.id}">${resolution.name}</a><br>
</c:forEach>


<%@include file="../fragments/footer.jspf"%>

</body>
</html>
