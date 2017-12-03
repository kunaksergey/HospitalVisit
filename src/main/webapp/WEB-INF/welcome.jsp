<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Добро пожаловать</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/style.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.5/umd/popper.min.js"></script>
    <script src="/js/bootstrap.js"></script>
    <script src="/js/bootstrap.bundle.js"></script>
</head>

<body>
<div class="main-navbar">
    <%--Menu--%>
    <div class="main-navbar__section main-navbar__section_left">
        <nav class="nav">
            <a class="nav-link active" href="#">Записаться</a>
            <a class="nav-link" href="#">О проекте</a>
            <a class="nav-link" href="#">Контакты</a>
        </nav>
    </div>
    <div class="main-navbar__section main-navbar__section_right">
        <a href="/login/" id="login" class="btn btn_x-large btn_navbar_login">Войти</a>
        <a href="/register/" class="btn btn_x-large btn_navbar_registration">Регистрация</a>
    </div>
</div>

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
        <%--<div class="form-group">--%>
            <%--<label for="districtSelect">Выберите район:</label>--%>
            <%--<select class="form-control" id="districtSelect">--%>
                <%--&lt;%&ndash;<option>Выбрать все</option>&ndash;%&gt;--%>
                <%--<c:forEach var="district" items="${districts}">--%>
                    <%--<option>--%>
                        <%--<c:out value="${district.name}"/>--%>
                    <%--</option>--%>
                <%--</c:forEach>--%>
            <%--</select>--%>
            <%--<c:forEach var="district" items="${districts}">--%>
                 <%--<c:out value="${district.name}"/>--%>
            <%--</c:forEach>--%>
        <%--</div>--%>
        <div class="formRow">
            <label>Devices:</label>
            <div class="formRight">
                <form:select path="deviceId" data-placeholder="Your Favorite Types of Bear" multiple="true" class="chzn-select width-100 form-control" tabindex="8">

                    <form:option value="">-- Choose Device --</form:option>
                    <c:forEach var="district" items="${districts}" varStatus="index">
                        <form:option value="${district.id}">${district.name}</form:option>
                    </c:forEach>
                </form:select>
                <form:errors path="deviceId" cssClass="help-inline alert-error" />
            </div>
        </div>
    </div>
</div>

<%--News--%>
<div class="list-group">
    <c:forEach var="it" items="${news}">
        <c:out value="${it.date}"/>
        <p>
            <c:out value="${it.message}"/>
        </p>
    </c:forEach>

</div>
</body>
</html>

