<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Список мед учереждений</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.5/umd/popper.min.js"></script>
    <script src="/js/bootstrap.js"></script>
    <script src="/js/bootstrap.bundle.js"></script>
</head>
<body>
<h2>Все мед. учереждения</h2>
<table  class="table table-striped">
    <thead>
    <tr>
        <th>Наименование</th>
        <th>Адресс</th>
        <th>Район</th>
        <%--<th> <span class="glyphicon">&#x270f;</span></th>--%>
        <th> <a href="hospital/add" class="btn btn-info btn-sm">
            <span class="glyphicon glyphicon-pencil">Создать</span>
        </a></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="hospital" items="${hospitals}">
        <tr>
            <td><c:out value="${hospital.name}"/></td>
            <td><c:out value="${hospital.address}"/></td>
            <td><c:out value="${hospital.district.name}"/></td>
            <td>
                <a href="hospital/edit/${hospital.id}" class="btn btn-info btn-sm">
                    <span class="glyphicon glyphicon-pencil">Edit</span>
                </a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
