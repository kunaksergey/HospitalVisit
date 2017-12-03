<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Список районов</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.5/umd/popper.min.js"></script>
    <script src="/js/bootstrap.js"></script>
    <script src="/js/bootstrap.bundle.js"></script>
</head>
<body>
<h1>Все районы</h1>
<table  class="table table-striped">
    <thead>
    <tr>
        <th>Наименование</th>
        <%--<th> <span class="glyphicon">&#x270f;</span></th>--%>
        <th> <a href="district/add" class="btn btn-info btn-sm">
            <span class="glyphicon glyphicon-pencil">Создать</span>
        </a></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="district" items="${districts}">
        <tr>
            <td><c:out value="${district.name}"/></td>
            <td>
                <a href="district/edit/${district.id}" class="btn btn-info btn-sm">
                    <span class="glyphicon glyphicon-pencil">Edit</span>
                </a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
