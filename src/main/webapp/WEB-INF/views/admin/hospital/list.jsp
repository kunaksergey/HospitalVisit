<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Мед.заклади</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.5/umd/popper.min.js"></script>
    <script src="/js/bootstrap.js"></script>
    <script src="/js/bootstrap.bundle.js"></script>
    <link rel="stylesheet" href="/css/style.css"/>
</head>
<body>
<div class="container">
    <div class="row">
        <h2>Усі мед. заклади</h2>
        <table class="table table-striped">
            <thead>
            <tr>
                <th>Найменування</th>
                <th>Адреса</th>
                <th>Район</th>
                <th>
                    <a href="hospital/add" class="btn btn-success btn-sm">
                        <i class="fa fa-plus-circle" aria-hidden="true"></i>
                        <span>Створити</span>
                    </a>
                </th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="hospital" items="${hospitals}">
                <tr>
                    <td><c:out value="${hospital.name}"/></td>
                    <td><c:out value="${hospital.address}"/></td>
                    <td><c:out value="${hospital.district.name}"/></td>
                    <td>
                        <a href="hospital/edit/${hospital.id}">
                                <%--class="btn btn-info btn-sm"--%>
                                <%--<span class="glyphicon glyphicon-pencil">Зберегти</span>--%>
                            <i class="fa fa-pencil" aria-hidden="true"></i>
                        </a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
