<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Products Report</title>
    <link href="<c:url value='/css/bootstrap.css' />" rel="stylesheet"/>
    <link href="<c:url value='/css/app.css' />" rel="stylesheet"/>
</head>

<body>
<div class="generic-container">
    <div class="panel panel-default">
        <!-- Default panel contents -->

        <c:forEach items="${products}" var="product">

        <div class="panel-heading"><span class="lead">${product.key}</span></div>
        <table class="table table-hover">
            <thead>
            <tr>
                <th>Product type name</th>
                <th>Bulk</th>
                <th>Product ID</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${product.value}" var="p">
                <tr>
                    <td>${p.productType.name}</td>
                    <td>${p.productType.type}</td>
                    <td>${p.bulk}</td>
                    <td>${p.id}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        </c:forEach>
    </div>
</div>
</body>
</html>