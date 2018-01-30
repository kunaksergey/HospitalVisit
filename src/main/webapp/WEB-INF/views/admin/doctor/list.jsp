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
<h2>Доктора:</h2>
<table  class="table table-striped">
    <thead>
    <tr>
        <th>Ім'я</th>
        <th>Лікарня</th>
         <th> <a href="doctor/add" class="btn btn-info btn-sm">
            <span class="glyphicon glyphicon-pencil">Створити</span>
        </a></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="doctor" items="${doctors}">
        <tr>
            <td><c:out value="${doctor.name}"/></td>
            <td><c:out value="${doctor.hospital.name}"/></td>

            <td>
                <a href="doctor/edit/${doctor.id}">
                    <i class="fa fa-pencil" aria-hidden="true"></i>
                </a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
