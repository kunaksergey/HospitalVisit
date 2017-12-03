<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Добро пожаловать</title>
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
    <a class="nav-link active" href="#">Записаться</a>
    <a class="nav-link" href="#">О проекте</a>
    <a class="nav-link" href="#">Контакты</a>
</nav>

<div class="container">
<ul class="nav nav-pills">
    <li class="active"><a data-toggle="pill" href="#search_spec">Поиск по специализации</a></li>
    <li><a data-toggle="pill" href="#search_surename">Поиск по фамилии</a></li>
</ul>

<div class="tab-content">
    <div id="search_spec" class="tab-pane fade in active">
        <h3>Панель 1</h3>
        <p>Содержимое 1 панели...</p>
    </div>
    <div id="search_surename" class="tab-pane fade">
        <h3>Панель 2</h3>
        <p>Содержимое 2 панели...</p>
    </div>
</div>
</div>
</body>
</html>

