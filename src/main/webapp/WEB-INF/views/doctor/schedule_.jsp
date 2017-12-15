<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.5/umd/popper.min.js"></script>
    <script src="/js/bootstrap.js"></script>
    <script src="/js/bootstrap.bundle.js"></script>
    <link rel="stylesheet" href="/css/style.css">
    <title>Расклад</title>
</head>

<body>
<div id="schedule" class="container">
    <div class="form-group">
        <input class="input-group" name="name" placeholder="дата">
    </div>
    <div class="form-group">
        <input class="datepicker" name="birthDay" placeholder="дата">
    </div>
    <div class="row">
        <div id="even" class="col-lg-5 "><!--Парна неділя-->
            <div class="row">
                Парні
            </div>
            <div class="row">
                <div id="even_monday" class="col-lg-1"><!--ПН-->
                    <div>
                        Понеділок
                    </div>
                </div>
                <div id="even_tuesday" class="col-lg-1"><!--ВТ-->
                    <div>
                        Вівторок
                    </div>
                </div>
                <div id="even_wednesday" class="col-lg-1"><!--СР-->
                    <div>
                        Середа
                    </div>
                </div>
                <div id="even_thusday" class="col-lg-1"><!--ЧТ-->
                    <div>
                        Четверг
                    </div>
                </div>
                <div id="even_friday" class="col-lg-1"><!--ПН-->
                    <div>
                        П'ятниця
                    </div>
                </div>
                <div id="even_saturday" class="col-lg-1"><!--СБ-->
                    <div>
                        Субота
                    </div>
                </div>
                <div id="even_sanday" class="col-lg-1"><!--НД-->
                    <div>
                        Неділя
                    </div>
                </div>
            </div>
        </div>
        <div id="odd" class="col-lg-5"><!--Непарна неділя-->
            <div class="row">
                Непарні
            </div>
            <div id="odd_monday" class="col-lg-1"><!--ПН-->
                <div>
                    Понеділок
                </div>
            </div>
            <div id="odd_tuesday" class="col-lg-1"><!--ВТ-->
                <div>
                    Вівторок
                </div>
            </div>
            <div id="odd_wednesday" class="col-lg-1"><!--СР-->
                <div>
                    Середа
                </div>
            </div>
            <div id="odd_thusday" class="col-lg-1"><!--ЧТ-->
                <div>
                    Четверг
                </div>
            </div>
            <div id="odd_friday" class="col-lg-1"><!--ПН-->
                <div>
                    П'ятниця
                </div>
            </div>
            <div id="odd_saturday" class="col-lg-1"><!--СБ-->
                <div>
                    Субота
                </div>
            </div>
            <div id="odd_sanday" class="col-lg-1"><!--НД-->
                <div>
                    Неділя
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
