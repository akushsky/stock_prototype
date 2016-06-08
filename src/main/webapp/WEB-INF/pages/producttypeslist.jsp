<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Product types List</title>
    <link href="<c:url value='/css/bootstrap.css' />" rel="stylesheet"/>
    <link href="<c:url value='/css/app.css' />" rel="stylesheet"/>
</head>

<body>
<div class="generic-container">
    <div class="panel panel-default" style="overflow:scroll;height:750px;width:100%;overflow:auto">
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="lead">List of Product types</span></div>
        <table class="table table-hover">
            <thead>
            <tr>
                <th>Product type name</th>
                <th>Product type</th>
                <th>Extern providers</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${products}" var="product">
                <tr>
                    <td>${product.name}</td>
                    <td>${product.type}</td>
                    <td><c:forEach items="${product.externProviders}" var="provider">${provider.name}</c:forEach></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="well">
        <a href="<c:url value='/update-product-types' />" class="btn btn-success custom-width">Update</a>
        <a href="<c:url value='/delete-product-types' />" class="btn btn-danger custom-width">Delete</a>
    </div>
</div>
</body>
</html>