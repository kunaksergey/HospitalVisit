<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.5/umd/popper.min.js"></script>
    <script src="/js/registration.js"></script>
    <script src="/js/bootstrap.js"></script>
    <script src="/js/bootstrap.bundle.js"></script>
    <link rel="stylesheet" href="/css/style.css">
    <title>Реєстрація</title>
</head>

<body>

<div class="container">
    <div class="row">
        <div class="col-lg-3">
        </div>

        <div class="col-lg-6">
            <form:form method="POST" action="${regUri}" modelAttribute="userForm" class="form-signin">
                <h2 class="form-signin-heading">Реєстрація </h2>

                <spring:bind path="username">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <form:input type="text" path="username" class="form-control" placeholder="Ім'я користувача"
                                    autofocus="true"/>
                        <form:errors path="username"/>
                    </div>
                </spring:bind>

                <spring:bind path="fullName">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <form:input type="text" path="fullName" class="form-control" placeholder="ПІБ"
                        />
                        <form:errors path="fullName"/>
                    </div>
                </spring:bind>

                <spring:bind path="phone">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <form:input type="text" path="phone" class="form-control" placeholder="+38(067)_______"
                        />
                        <form:errors path="phone"/>
                    </div>
                </spring:bind>

                <spring:bind path="email">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <form:input type="text" path="email" class="form-control" placeholder="Email"
                        />
                        <form:errors path="email"/>
                    </div>
                </spring:bind>

                <spring:bind path="password">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <form:input type="password" path="password" class="form-control"
                                    placeholder="Пароль"/>
                        <form:errors path="password"/>
                    </div>
                </spring:bind>

                <spring:bind path="confirmPassword">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <form:input type="password" path="confirmPassword" class="form-control"
                                    placeholder="Підтвердження паролю"/>
                        <form:errors path="confirmPassword"/>
                    </div>
                </spring:bind>


                <sec:authorize access="isAuthenticated() && hasRole('ROLE_ADMIN')">
                    <div class="form-group checkbox-list">
                        <form:checkboxes items="${roles}"
                                         path="roles"/>
                    </div>
                </sec:authorize>


                <sec:authorize access="isAuthenticated() && hasRole('ROLE_ADMIN')">
                    <div class="form-group">
                        <label for="hospital">Лікарня:</label>
                        <form:select path="hospital" class="form-control">
                            <option value="-1"/>
                            <c:forEach var="hospital" items="${hospitals}">
                                <option value="${hospital.id}">
                                    <c:out value="${hospital.name}"/>
                                </option>
                            </c:forEach>
                        </form:select>
                    </div>
                </sec:authorize>

                <button class="btn btn-lg btn-primary btn-block" type="submit">Створити</button>
            </form:form>
        </div>

        <div class="col-lg-3">
        </div>
    </div>
</div>
</body>
</html>