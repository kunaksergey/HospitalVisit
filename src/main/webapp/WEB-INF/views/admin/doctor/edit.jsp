<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
    <title>Список мед-учереждений</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.5/umd/popper.min.js"></script>
    <script src="/js/bootstrap.js"></script>
    <script src="/js/bootstrap.bundle.js"></script>
</head>
<body>
<form:form method="POST" action="/hospital/save" modelAttribute="doctor">
    <div class="form-group">
        <form:hidden path="id" />
        <label for="name">Доктор:</label>
    </div>
    <div class="form-group">
        <form:input path="fullName" type="text" class="form-control" id="fullname" placeholder="ПІБ"/>
        <small class="form-text text-muted">Ім'я та по-батькові</small>
    </div>
    <div class="form-group">
        <form:input path="email" type="text" class="form-control" id="email" placeholder="E-mail"/>
        <small class="form-text text-muted">E-mail</small>
    </div>
    <div class="form-group">
        <form:input path="phone" type="text" class="form-control" id="phone" placeholder="Phone"/>
        <small class="form-text text-muted">Моб.телефон</small>
    </div>
    <div class="form-group">
        <form:input path="login" type="text" class="form-control" id="login" placeholder="Login"/>
        <small class="form-text text-muted">Логін</small>
    </div>
    <div class="form-group">
        <form:input path="password" type="text" class="form-control" id="password" placeholder="Password"/>
        <small class="form-text text-muted">Пароль</small>
    </div>
    <div class="form-group">
        <input type="text" class="form-control" id="confirm_pass" placeholder="Confirm password"/>
        <small class="form-text text-muted">Подвердіть пароль</small>
    </div>
      <div class="form-group">
        <form:select class="form-control" id="hospital_select" path="hospital">
            <option value="${hospital.id}"><c:out value="${hospital.name}"/></option>
            <c:forEach var="hospital" items="${hospitals}">
                <option value="${hospital}.id}">
                    <c:out value="${hospital.name}"/>
                </option>
            </c:forEach>
        </form:select>
        <small class="form-text text-muted">Выберите больницу</small>
    </div>
    <button type="submit" class="btn btn-info btn-sm">Сохранить</button>
</form:form>
<div id="schedule">
    <div id="schedule-content">
        <p><a id="schedule-close" href="#">Close</a></p>
        <div id="schedule-body"></div>
    </div>
</div>
</body>
</html>
