<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Ласкаво просимо</title>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="/css/bootstrap.min.css"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.5/umd/popper.min.js"></script>
    <script src="/js/bootstrap.bundle.js"></script>
    <script src="/js/bootstrap.js"></script>
    <script src="/js/welcome.js"></script>
    <%--<link rel="stylesheet" href="/css/card-doctor.css">--%>
    <link rel="stylesheet" href="/css/style.css"/>

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
                    <sec:authorize access="!isAuthenticated()">
                        <a href="/login/" id="login" class="btn btn_x-large btn_navbar_login">Ввійти</a>
                        <a href="/registration/" class="btn btn_x-large btn_navbar_registration">Реєстрація</a>
                    </sec:authorize>
                    <sec:authorize access="isAuthenticated()">
                        <a class="nav-link" href="/logout">Выйти</a>
                    </sec:authorize>
                </div>
            </div>
        </div>
    </div>
    <%--End menu--%>


    <%--Search--%>
    <div class="row">
        <div class="col-lg-10">
            <div class="bd-example bd-example-tabs" role="tabpanel">
                <ul class="nav nav-tabs" id="myTab" role="tablist">
                    <li class="nav-item">
                        <a class="nav-link active" data-toggle="tab" href="#tab_search_spec" role="tab"
                        >Пошук за спеціалізацією</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" data-toggle="tab" href="#tab_search_name" role="tab"
                        >Пошук за ПІБ</a>
                    </li>
                </ul>

                <div class="tab-content" id="myTabContent">
                    <%--Tab search --%>
                    <form:form modelAttribute="searchCriteria" action="/search/spec" id="f_search"
                               class="form-search" role="form" method="post">
                        <div class="row">
                            <div class="form-group col-lg-4">
                                <form:input path="searchStr" type="text"
                                            class="form-control form-control-sm search-query"
                                            placeholder="Спеціалізація"/>
                            </div>
                                <%--Select district--%>
                            <div class="input-group col-lg-5">
                                <label for="district">Район:</label>
                                <form:select class="form-control form-control-sm" path="district">
                                    <form:option value="-1">Вибрати усі</form:option>
                                    <form:options items="${districts}" itemLabel="name" itemValue="id"/>
                                </form:select>
                            </div>
                                <%--End select district--%>
                            <div class="form-group col-lg-3">
                                <button type="submit" class="btn btn-info btn-sm">Знайти</button>
                            </div>
                        </div>
                    </form:form>
                    <%--End search --%>
                </div>

            </div>
        </div>
        <div class="col-lg-2">
            <div class="list-group">
                <sec:authorize access="hasRole('ROLE_USER')">
                    <a href="/patient/cabinet" class="list-group-item">Особистий кабінет</a>
                </sec:authorize>
                <sec:authorize access="hasRole('ROLE_DOCTOR')">
                    <a href="/doctor" class="list-group-item">Кабінет лікаря</a>
                </sec:authorize>
                <sec:authorize access="hasRole('ROLE_SUPERVISOR')">
                    <a href="/supervisor" class="list-group-item">Завідуючий</a>
                </sec:authorize>
                <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <a href="/admin" class="list-group-item">Адмінка</a>
                </sec:authorize>
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


    <div id="search-doctor-grid" class="card-grid">


    </div>
    <div id="card-doctor-for-clone" class="d-none">
        <div class="card-doctor">
            <a href="">
                <div class="card-doctor-content">
                    <div class="card-doctor-top">
                        <div class="photo-doctor">
                            <img class="photo-doctor-img" src="/img/no_photo.png" draggable="false"/>
                        </div>
                        <div class="card-doctor-info">
                            <h3 class="card-doctor-name">
                                <%--ПІБ--%>
                            </h3>
                            <div class="card-doctor-speciality">
                                <%--Спеціалізація--%>
                            </div>
                        </div>
                    </div>
                    <div class="card-doctor-button">
                        <div class="card-doctor-address">
                            <div class="card-doctor-clinic">
                                <%--Лікарня--%>
                            </div>
                            <div class="сard-doctor-cabinet">
                                <%--Кабінет--%>
                            </div>
                        </div>
                    </div>
                </div>
            </a>
            <div class="card-doctor-btn">
                <a href="" class="btn btn-info btn-sm doctor-details-link">
                    <span class="glyphicon glyphicon-pencil">Записатися на прийом</span>
                </a>
            </div>
        </div>
    </div>
</div>

</body>
</html>




