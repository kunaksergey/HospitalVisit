<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Список мед.закладів</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.5/umd/popper.min.js"></script>
    <script src="/js/bootstrap.js"></script>
    <script src="/js/bootstrap.bundle.js"></script>
    <script src="/js/user.js"></script>
</head>
<body>
<div class="container">
    <form:form method="POST" action="/admin/user/save" modelAttribute="user">
        <div class="form-group">
            <form:hidden path="id"/>
        </div>
        <div class="form-group">
            <form:input path="username"/>
        </div>
        <div class="form-group">
            <form:input path="fullName"/>
        </div>
        <div class="form-group">
            <form:input path="birthday"/>
        </div>
        <div class="form-group">
            <form:input path="phone"/>
        </div>
        <div class="form-group">
            <form:input path="email"/>
        </div>

        <div class="form-group">
            <form:select class="form-control" path="hospital">
                <form:option value="">--None--</form:option>
                <form:options items="${hospitals}" itemLabel="name" itemValue="id"/>
                    <form:options items="${hospitals}" itemLabel="name" itemValue="id"/>
            </form:select>
            <small class="form-text text-muted">Выберіть лікарню</small>
        </div>

        <div class="checkbox-list">
            <form:checkboxes items="${roles}"
                             path="roles"/>
        </div>
        <button type="submit" class="btn btn-info btn-sm">Зберегти</button>
    </form:form>
</div>
<div>

</div>
</body>
</html>
