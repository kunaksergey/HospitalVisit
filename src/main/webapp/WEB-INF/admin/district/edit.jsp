<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
<form method="POST" action="/district/edit" modelAttribute="district">
    <div class="form-group">
        <label for="nameDistrict">Район:</label>
        <form:input path="name" type="text" class="form-control" id="nameDistrict" aria-describedby="nameHelp" placeholder="Название района">
        <small id="nameHelp" class="form-text text-muted">Необходимо название района</small>
    </div>
    <button type="submit" class="btn btn-primary">Submit</button>
</form>
</body>
</html>
