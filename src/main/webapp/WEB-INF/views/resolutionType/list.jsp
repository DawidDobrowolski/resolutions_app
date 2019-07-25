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
    <script type="text/javascript" src="<c:url value="/resources/js/resolutionTypeList.js" />"></script>
    <link href="/webjars/font-awesome/5.9.0/css/all.css" rel="stylesheet">
</head>
<body>

<%@include file="../fragments/header.jspf" %>

<div class="container">
    <header><h2>List of resolutions</h2></header>
    <div class="card">
        <div class="card-body text-center" style="border: none">
            <a href="/resolutionType/add" class="btn btn-submit btn-lg">Add new resolution</a>
        </div>
    </div>
    <div class="card">
        <div class="card-body">
            <div table-responsive>
                <table border="1" class="table table-hover table-striped">
                    <thead class="text-center table-primary">
                    <tr>
                        <th class="align-middle">Name</th>
                        <th class="align-middle">Unit</th>
                        <th class="align-middle">Action</th>
                    </tr>
                    </thead>
                    <c:forEach items="${resolutionTypes}" var="resolutionType">
                        <tr class="text-center">
                            <td class="align-middle">${resolutionType.name}</td>
                            <td class="align-middle">${resolutionType.unit}</td>
                            <td class="align-middle">
                                <a href="/resolutionType/edit/${resolutionType.id}" class="btn btn-warning btn-sm">Edit</a>
                                <a href="/resolutionType/delete/${resolutionType.id}" class="btn btn-danger btn-sm"
                                   data-toggle="modal"
                                   data-target="#deleteModal"
                                   data-resolution-id="${resolutionType.id}"
                                   data-resolution-name="${resolutionType.name}"
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
                <p>Are you sure you want to delete resolution: <strong><span id="resolutionName"></span></strong>?</p>
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
