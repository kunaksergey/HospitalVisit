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
</head>
<body>
<div class="container">
    <div class="row">
        <form:form method="POST" action="/hospital/save" modelAttribute="hospital">
            <div class="form-group">
                <form:hidden path="id"/>
                <label for="name">Мед.Заклади:</label>
            </div>
            <div class="form-group">
                <form:input path="name" type="text" class="form-control" id="name"
                            placeholder="Название мед.учреждения"/>
                <small class="form-text text-muted">Вкажіть найменування</small>
            </div>
            <div class="form-group">
                <form:input path="address" type="text" class="form-control" id="address" placeholder="Адресс"/>
                <small class="form-text text-muted">Вкажіть адресу</small>
            </div>
            <div class="form-group">
                <form:select class="form-control" id="district_select" path="district">
                    <option value="${hospital.district.id}"><c:out value="${hospital.district.name}"/></option>
                    <c:forEach var="district" items="${districts}">
                        <option value="${district.id}">
                            <c:out value="${district.name}"/>
                        </option>
                    </c:forEach>
                </form:select>
                <small class="form-text text-muted">Выберіть район</small>
            </div>
            <button type="submit" class="btn btn-info btn-sm">Зберегти</button>
        </form:form>
    </div>
</div>
</body>
</html>
