<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Лист користувачів</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.5/umd/popper.min.js"></script>
    <script src="/js/bootstrap.js"></script>
    <script src="/js/bootstrap.bundle.js"></script>
</head>
<body>
<div class="container">
    <h2>Лист користувачів</h2>
    <table class="table table-condensed">
        <thead>
        </thead>
        <tbody>
        <nav class="nav">
            <a class="nav-link active" href="/admin/users">Усі</a>
            <a class="nav-link" href="/admin/users/ROLE_DOCTOR">Лікарі</a>
            <a class="nav-link" href="/admin/users/ROLE_ADMIN_HOSPITAL">Адміністратор лікарні</a>
            <a class="nav-link" href="/admin/users/ROLE_ADMIN">Адміністратор</a>
        </nav>
        <c:forEach var="user" items="${users}">
        <table class="table table-bordered">
            <tr>
                <td>Id:</td>
                <td>${user.id}</td>
            </tr>
            <tr>
                <td>Користувач:</td>
                <td>${user.username}</td>
            </tr>
            <tr>
                <td>ПІБ:</td>
                <td>${user.fullName}</td>
            </tr>
            <tr>
                <td>
                    Дозволи:
                </td>
                <td>
                    <c:forEach var="item" items="${user.roles}">
                        <c:out value="${item.role}"/>
                    </c:forEach>
                </td>
                    <%--<form:form method="POST" action="/admin/user/save" modelAttribute="rolesForm" class="form-signin">--%>
                    <%--<form:checkboxes items="${roleSet}" path=""/>--%>
                    <%--<button type="submit" class="btn btn-info btn-sm">Зберегти</button>--%>
                    <%--</form:form>--%>
            </tr>
            <tr>
                <td>
                    <a href="/admin/user/edit/${user.id}" class="btn btn-info btn-sm">
                        <span class="glyphicon glyphicon-pencil">Редагувати</span>
                    </a>
                </td>
            </tr>
                <%--<tr>--%>
                <%--<td>--%>
                <%--<a href="user/edit/${user.id}" class="btn btn-info btn-sm">--%>
                <%--<span class="glyphicon glyphicon-pencil">Редагувати</span>--%>
                <%--</a>--%>
                <%--</td>--%>
                <%--</tr>--%>
            </c:forEach>
        </table>
        </tbody>
    </table>
</div>
</body>
</html>
