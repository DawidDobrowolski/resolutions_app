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
    <title>Title</title>
</head>
<body>
<%@include file="../fragments/header.jspf"%>

<form method="post" action="/report/generate">
    <c:if test="${wrongDate}">
        Please input correct dates for report<br>
    </c:if>

    Report from: <input type="date" name="from"/><br>
    Report to: <input type="date" name="to"/><br>

    <input type="submit" value="Generate report"/>
</form>
<%@include file="../fragments/footer.jspf"%>
</body>
</html>
