<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Панель администратора</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.5/umd/popper.min.js"></script>
    <script src="/js/bootstrap.js"></script>
    <script src="/js/bootstrap.bundle.js"></script>
</head>
<body>
<%--Menu--%>
<nav class="nav">
    <a class="nav-link active" href="/district">Район</a>
    <a class="nav-link active" href="/admin/users">Користувачі</a>
    <a class="nav-link" href="/hospital">Больница</a>
    <a class="nav-link" href="#">Администраторы больниц</a>
    <a class="nav-link" href="/doctor">Доктора</a>
    <a class="nav-link" href="/specialization">Спеціалізації</a>
    <a class="nav-link" href="#">Пациенты</a>
    <a class="nav-link" href="#">Заявки</a>
    <a class="nav-link" href="#">Новости</a>
</nav>
</body>
</html>
