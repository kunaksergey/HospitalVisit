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
    <title>Вхід</title>
</head>

<body>
<div class="container">
    <spring:url var = "loginUrl" value = "/j_spring_security_check" />
    <form method="POST" action="${loginUrl}" class="form-signin">
        <h2 class="form-heading">Вхід</h2>

        <div class="form-group">
            <input name="username" type="text" class="form-control" placeholder="Користувач"
                   autofocus=/>
        </div>

        <div class="form-group">
            <input name="password" type="password" class="form-control" placeholder="Пароль"/>
        </div>
        <span class="${error != null ? 'has-error' : ''}">${error}</span>
        <div class="form-group">
            <button class="btn btn-lg btn-info btn-block" type="submit">Війти</button>
        </div>

    </form>
</div>
</body>
</html>