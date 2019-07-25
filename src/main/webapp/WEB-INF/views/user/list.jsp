<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: dawid
  Date: 7/24/19
  Time: 6:15 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User list</title>
    <script src="<c:url value="/webjars/jquery/3.0.0/jquery.min.js"/>"></script>
    <script src="<c:url value="/webjars/bootstrap/4.3.1/js/bootstrap.min.js"/>"></script>
    <link href="<c:url value="/webjars/bootstrap/4.3.1/css/bootstrap.min.css"/>" rel="stylesheet">
    <link rel="stylesheet" href="<c:url value="/resources/css/main.css"/>">
    <script type="text/javascript" src="<c:url value="/resources/js/userList.js" />"></script>
    <link href="/webjars/font-awesome/5.9.0/css/all.css" rel="stylesheet">
</head>
<body>

<%@include file="../fragments/header.jspf" %>

<div class="container">
    <header><h2>List of users</h2></header>
    <div class="card">
        <div class="card-body">
            <div table-responsive>
                <table border="1" class="table table-hover table-striped">
                    <thead class="text-center table-primary">
                    <tr>
                        <th class="align-middle">First name</th>
                        <th class="align-middle">Last name</th>
                        <th class="align-middle">E-mail</th>
                        <th class="align-middle">Is admin</th>
                        <th class="align-middle">Action</th>
                    </tr>
                    </thead>
                    <c:forEach items="${users}" var="user">
                        <tr class="text-center">
                            <td class="align-middle">${user.firstName}</td>
                            <td class="align-middle">${user.lastName}</td>
                            <td class="align-middle">${user.email}</td>
                            <td class="align-middle">
                                <c:choose>
                                    <c:when test="${user.admin}">
                                        <i class="fas fa-check fa-2x" style="color: green"></i>
                                    </c:when>
                                    <c:otherwise>
                                        <i class="fas fa-times fa-2x" style="color: red"></i>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td class="align-middle">
                                <a href="/user/edit/${user.id}" class="btn btn-warning btn-sm">Edit</a>
                                <a href="/user/delete/${user.id}" class="btn btn-danger btn-sm"
                                   data-toggle="modal"
                                   data-target="#deleteModal"
                                   data-user-id="${user.id}"
                                   data-user-name="${user.name}"
                                   title="Delete activity">Delete</a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="deleteModal" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Delete confirmation</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <p>Are you sure you want to delete user: <strong><span id="userName"></span></strong>?</p>
            </div>
            <div class="modal-footer">
                <button id="deleteId" type="button" class="btn btn-primary btn-submit">Confirm</button>
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>

<br>
<%@include file="../fragments/footer.jspf" %>
</body>
</html>
