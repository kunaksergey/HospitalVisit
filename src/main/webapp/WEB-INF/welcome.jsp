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
    <script src="/js/bootstrap.bundle.js"></script>
    <script src="/js/bootstrap.js"></script>
    <script src="/js/welcome.js"></script>
    <link rel="stylesheet" href="/css/style.css">
</head>

<body>
<div class="container">
    <%--Menu--%>
    <div class="row">
        <div class="col-lg-11">
            <div class="main-navbar">
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
        </div>
    </div>
    <%--End menu--%>

    <%--Search--%>
    <div class="row">
        <div class="col-lg-11">
            <div class="bd-example bd-example-tabs" role="tabpanel">
                <ul class="nav nav-tabs" id="myTab" role="tablist">
                    <li class="nav-item">
                        <a class="nav-link active" data-toggle="tab" href="#tab_search_spec" role="tab"
                        >Поиск по специализации</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" data-toggle="tab" href="#tab_search_name" role="tab"
                        >Поиск по фамилии</a>
                    </li>
                </ul>

                <div class="tab-content" id="myTabContent">

                    <%--Tab search spec--%>
                    <div role="tabpanel" class="tab-pane fade show active" id="tab_search_spec">
                        <form id="f_search_spec" class="form-search form-inline" role="form">
                            <div class="input-group col-lg-6">
                                <input type="text" name="search" class="form-control input-medium search-query" required
                                       placeholder="Поиск по специализации">
                            </div>
                            <%--Select district--%>
                            <div class="form-group">
                                <label for="district_select_1">Район:</label>
                                <select class="form-control" id="district_select_1" name="district">
                                    <option value="-1">Выбрать все</option>
                                    <c:forEach var="district" items="${districts}">
                                        <option value="${district.id}">
                                            <c:out value="${district.name}"/>
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>

                            <div class="form-group">
                                <button type="submit" class="btn btn-info btn-sm">Найти</button>
                            </div>
                        </form>
                    </div>
                    <%--End Tab--%>

                    <%--Tab search name--%>
                    <div role="tabpanel" class="tab-pane fade" id="tab_search_name">
                        <form id="f_search_name" class="form-search form-inline" role="form" method="post">
                            <div class="input-group col-lg-6">
                                <input type="text" name="search" class="form-control search-query" required
                                       placeholder="Поиск по фамилии">
                            </div>
                            <%--Select district--%>
                            <div class="form-group">
                                <label for="district_select_2" class="control-label">Район:</label>
                                <select class="form-control" id="district_select_2" name="district">
                                    <option value="-1">Выбрать все</option>
                                    <c:forEach var="district" items="${districts}">
                                        <option value="${district.id}">
                                            <c:out value="${district.name}"/>
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>
                            <%--End select district--%>
                            <div class="form-group">
                                <button type="submit" class="btn btn-info btn-sm">Найти</button>
                            </div>
                        </form>
                    </div>
                    <%--End Tab--%>
                </div>
            </div>
        </div>
    </div>
    <%--End search--%>

    <%--News--%>
    <div class="row">
        <div class="col-lg-11">
            <div class="list-group">
                <c:forEach var="it" items="${news}">
                    <c:out value="${it.date}"/>
                    <p>
                        <c:out value="${it.message}"/>
                    </p>
                </c:forEach>
            </div>
        </div>
    </div>
    <%--End News--%>
</div>
</body>
</html>




