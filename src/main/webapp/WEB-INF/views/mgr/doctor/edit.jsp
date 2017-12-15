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
    <script src="/js/schedule.js"></script>
</head>
<body>
<div class="container">
    <form:form method="POST" action="/admin_hosp/doctor/save" modelAttribute="doctor">
        <div class="form-group">
            <form:hidden path="id"/>
        </div>

        <div class="form-group">
            <label for="fullName">ПІБ:</label>
            <form:input path="fullName"/>
        </div>
        <div class="form-group">
            <label for="birthday">День народження:</label>
            <form:input path="birthday"/>
        </div>
        <div class="form-group">
            <label for="birthday">Телефон:</label>
            <form:input path="phone"/>
        </div>
        <div class="form-group">
            <label for="birthday">Email:</label>
            <form:input path="email"/>
        </div>
        <div class="checkbox-list">
            <form:checkboxes items="${roles}"
                             path="roles"/>
        </div>
        <button type="submit" class="btn btn-info btn-sm">Зберегти</button>
    </form:form>


    <%--Menu schedules --%>
    <div id="schedule-menu" class="list-group border rounded">
        <a id="schedule-add" class="list-group-item"
           data-toggle="modal" data-target="#scheduleModal" href=''>Розклад:додати</a>
        <c:forEach var="schedule" items="${doctor.schedules}">
            <a class="schedule-loaded list-group-item"
               data-toggle="modal" data-target="#scheduleModal" href="/schedule/${schedule.id}">
                <c:out value="Розклад:${schedule.start}"/>
            </a>
        </c:forEach>
    </div>
    <%--End menu schedules --%>
</div>

<!-- Schedule modal -->
<div class="modal fade" id="scheduleModal" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Розклад</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div id="table-content">
                    <%--Сюда вставляем склонировынные таблицы--%>
                </div>
                <p><a href="#" data-toggle="modal" data-target="#timeModal">Час:додати</a></p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary btn-sm" data-dismiss="modal">Відміна</button>
                <button type="button" class="btn btn-info btn-sm">Зберегти</button>
            </div>
        </div>
    </div>
</div>
<!-- End schedule modal -->

<!-- Time modal -->
<div class="modal fade" id="timeModal" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-sm" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Додати час</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form id="add-time" role="form" method="post">
                    <div class="form-group">
                        <select name="day" class="selectpicker">
                            <optgroup label="Парні">
                                <option value="even_monday">Понеділок</option>
                                <option value="even_tuesday">Вівторок</option>
                                <option value="even_wednesday">Середа</option>
                                <option value="even_thusday"> Четверг</option>
                                <option value="even_friday">П'ятниця</option>
                                <option value="even_saturday">Субота</option>
                                <option value="even_sanday">Неділя</option>
                            </optgroup>
                            <optgroup label="Непарні">
                                <option value="odd_monday">Понеділок</option>
                                <option value="odd_tuesday">Вівторок</option>
                                <option value="odd_wednesday">Середа</option>
                                <option value="odd_thusday">Четверг</option>
                                <option value="odd_friday">П'ятниця</option>
                                <option value="odd_saturday">Субота</option>
                                <option value="odd_sanday">Неділя</option>
                            </optgroup>
                        </select>
                    </div>

                    <div class="form-group">
                        <input type="text" name="time" placeholder="00:00">
                    </div>
                    <div class="form-group modal-footer">
                        <button type="submit" class="btn btn-info btn-sm">Додати</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<!-- End time modal -->
<!-- Delete modal -->
<div class="modal fade" id="confirm-delete" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Confirm Delete</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>

            <div class="modal-body">
               <p>Do you want to proceed?</p>
             </div>

            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                <a class="btn btn-danger btn-ok" data-dismiss="modal">Delete</a>
            </div>
        </div>
    </div>
</div>
<!-- End delete modal -->
<%--Table body for clone --%>
<div id="tables-for-clone" class="d-none">
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
    <%--Не парні--%>
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
<%--End Table body for clone --%>
</body>
</html>
