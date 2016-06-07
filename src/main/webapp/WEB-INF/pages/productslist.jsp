<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Products List</title>
    <link href="<c:url value='/css/bootstrap.css' />" rel="stylesheet"/>
    <link href="<c:url value='/css/app.css' />" rel="stylesheet"/>
</head>

<body>
<div class="generic-container">
    <div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="lead">List of Products</span></div>
        <table class="table table-hover">
            <thead>
            <tr>
                <th>Product type name</th>
                <th>Product type</th>
                <th>Bulk</th>
                <th>Product ID</th>
                <%--<th width="100"></th>--%>
                <%--<th width="100"></th>--%>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${products}" var="product">
                <tr>
                    <td>${product.productType.name}</td>
                    <td>${product.productType.type}</td>
                    <td>${product.bulk}</td>
                    <td>${product.id}</td>
                    <%--<td><a href="<c:url value='/edit-product-${product.id}' />" class="btn btn-success--%>

<%--custom-width">edit</a></td>--%>
                    <%--<td><a href="<c:url value='/delete-product-${product.id}' />" class="btn btn-danger--%>

<%--custom-width">delete</a></td>--%>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <%--<div class="well">--%>
        <%--<a href="<c:url value='/newproduct' />">Add New Product</a>--%>
    <%--</div>--%>
    <div class="well">
        <a href="<c:url value='/report' />">Show report</a>
    </div>
</div>
</body>
</html>