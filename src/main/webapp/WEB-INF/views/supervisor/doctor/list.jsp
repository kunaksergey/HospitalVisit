<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Лікарі</title>
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
    <h2>Лист лікарів</h2>
    <div class=" list-group border rounded">
        <c:forEach var="doctor" items="${doctors}">
            <div class="list-group-item">
                ПІБ:${doctor.user.fullName}
                <a href="doctor/card/${doctor.id}">
                    <i class="fa fa-pencil pull-right" aria-hidden="true"></i>
                </a>
            </div>
        </c:forEach>
    </div>
</div>
</body>
</html>
