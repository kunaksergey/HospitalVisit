<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Лікарі</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.5/umd/popper.min.js"></script>
    <script src="/js/bootstrap.js"></script>
    <script src="/js/bootstrap.bundle.js"></script>
</head>
<body>
<h2>Лист лікарів</h2>
<table class="table table-condensed">
    <thead>
    </thead>
    <tbody>
   <c:forEach var="doctor" items="${doctors}">
    <table class="table table-bordered">
        <tr>
            <td>ПІБ:</td>
            <td>${doctor.fullName}</td>
        </tr>
        <tr>
            <td>
                <a href="doctor/edit/${doctor.id}" class="btn btn-info btn-sm">
                    <span class="glyphicon glyphicon-pencil">Редагувати</span>
                </a>
            </td>
        </tr>
        </c:forEach>
    </table>
    </tbody>
</table>
</body>
</html>
