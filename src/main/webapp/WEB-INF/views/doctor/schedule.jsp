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
    <title>Росклад</title>
</head>

<body>
<div id="schedule" class="container">
    <div class="row">
        <table class="table">
            <thead>
            <tr>
                <th colspan="7">Парні</th>
            </tr>
            <tr>
                <th>Понеділок</th>
                <th>Вівторок</th>
                <th>Середа</th>
                <th>Четверг</th>
                <th>П'ятниця</th>
                <th>Субота</th>
                <th>Неділя</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td id="even_monday"><!--ПН-->
                </td>
                <td id="even_tuesday"><!--ВТ-->
                </td>
                <td id="even_wednesday"><!--СР-->
                </td>
                <td id="even_thusday"><!--ЧТ-->
                </td>
                <td id="even_friday"><!--ПН-->
                </td>
                <td id="even_saturday"><!--СБ-->
                </td>
                <td id="even_sanday"><!--НД-->
                </td>
            </tr>
            </tbody>
        </table>
        <table class="table">
            <thead>
            <tr>
                <th colspan="7">Непарні</th>
            </tr>
            <tr>
                <th>Понеділок</th>
                <th>Вівторок</th>
                <th>Середа</th>
                <th>Четверг</th>
                <th>П'ятниця</th>
                <th>Субота</th>
                <th>Неділя</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td id="odd_monday"><!--ПН-->
                </td>
                <td id="odd_tuesday"><!--ВТ-->
                </td>
                <td id="odd_wednesday"><!--СР-->
                </td>
                <td id="odd_thusday"><!--ЧТ-->
                </td>
                <td id="odd_friday"><!--ПН-->
                </td>
                <td id="odd_saturday"><!--СБ-->
                </td>
                <td id="odd_sanday"><!--НД-->
                </td>
            </tr>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
