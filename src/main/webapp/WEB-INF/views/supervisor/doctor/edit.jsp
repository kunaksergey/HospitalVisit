<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Облікова картка лікаря</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/parts-selector.css">
    <link rel="stylesheet" href="/css/style.css">
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.7/angular.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.5/umd/popper.min.js"></script>
    <script src="/js/bootstrap.js"></script>
    <script src="/js/bootstrap.bundle.js"></script>
    <script src="/js/image-upload.js"></script>
    <script src="/js/user.js"></script>
    <script src="/js/parts-selector.js"></script>
    <script src="/js/schedule-angular.js"></script>
    <script src="/js/schedule.js"></script>
</head>
<body ng-app="myApp" ng-controller="myCtrl">
<div class="container">
    <h5>Облікова картка лікаря: <c:out value="${doctorForm.fullName}"/></h5>
    <form:form method="POST" action="/supervisor/doctor/save" modelAttribute="doctorForm">
        <div class="form-group row">
            <form:hidden path="id"/>
        </div>

        <div class="row">
            <div class="col-8" style="background-color: green">
                <div class="form-group row">
                    <label for="fullName" class="col-2 col-form-label">ПІБ:</label>
                    <div class="col-6">
                        <form:input path="fullName" class="form-control"/>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="birthDay" class="col-2 col-form-label">День народження:</label>
                    <div class="col-6">
                        <form:input path="birthDay" class="form-control"/>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="phone" class="col-2 col-form-label">Телефон:</label>
                    <div class="col-6">
                        <form:input path="phone" class="form-control"/>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="email" class="col-2 col-form-label">Email:</label>
                    <div class="col-6">
                        <form:input path="email" class="form-control"/>
                    </div>
                </div>
            </div>
            <div class="col-4" style="background-color: yellow">
                <div class="photo-doctor">
                    <img class="photo-doctor-img" src="/img/no_photo.png" ngf-src="imgFile"  draggable="false"/>
                </div>
                <form name="ImageUpload" method="POST" enctype="multipart/form-data">
                    <div>
                        Виберить фото:
                        <input type="file" id="upload-image" ng-click="uploadImg(imgFile)" name="uploadImage"  accept="image/*" />
                        <br><br>
                        <input type="submit" value="Зберегти" /><br><br>
                    </div>
                </form>
            </div>
        </div>

        <div class="checkbox checkbox-info">
            <ul>
                <form:checkboxes element="li" items="${specializations}"
                                 path="specializations" itemValue="id" itemLabel="name"/>

            </ul>

        </div>

        <div class="checkbox checkbox-info">
            <form:checkbox path="enable" label="Активован"/>
        </div>
        <button type="submit" class="btn btn-info btn-sm">Зберегти</button>
    </form:form>


    <%--Menu schedules --%>
    <div id="schedule-menu" class="list-group border rounded">
        <a id="schedule-add" class="list-group-item" ng-click="addSchedule()"
           data-toggle="modal" data-target="#scheduleModal" href=''>Розклад:додати</a>
        <div id="schedule-list">
            <%--hear we add schedules --%>
        </div>
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
                    <week even-or-odd="EVEN" title="Парні"></week>
                    <week even-or-odd="ODD" title="Непарні"></week>
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
<div class="modal fade mt-1" id="timeModal" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-sm" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Додати час</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form id="add-time" name="addTime" role="form" method="post">
                    <div class="form-group">
                        <select ng-model="selected" name="day" class="form-control" data-header="Виберіть день" data-width="75%" required>
                            <optgroup label="Парні">
                                <option value='{"evenOrOdd":"EVEN","weekDay":"MONDAY"}'>Понеділок</option>
                                <option value='{"evenOrOdd":"EVEN","weekDay":"TUESDAY"}'>Вівторок</option>
                                <option value='{"evenOrOdd":"EVEN","weekDay":"WEDNESDAY"}'>Середа</option>
                                <option value='{"evenOrOdd":"EVEN","weekDay":"THURSDAY"}'> Четверг</option>
                                <option value='{"evenOrOdd":"EVEN","weekDay":"FRIDAY"}'>П'ятниця</option>
                                <option value='{"evenOrOdd":"EVEN","weekDay":"SATURDAY"}'>Субота</option>
                                <option value='{"evenOrOdd":"EVEN","weekDay":"SANDAY"}'>Неділя</option>
                            </optgroup>
                            <optgroup label="Непарні">
                                <option value='{"evenOrOdd":"ODD","weekDay":"MONDAY"}'>Понеділок</option>
                                <option value='{"evenOrOdd":"ODD","weekDay":"TUESDAY"}'>Вівторок</option>
                                <option value='{"evenOrOdd":"ODD","weekDay":"WEDNESDAY"}'>Середа</option>
                                <option value='{"evenOrOdd":"ODD","weekDay":"THURSDAY"}'>Четверг</option>
                                <option value='{"evenOrOdd":"ODD","weekDay":"FRIDAY"}'>П'ятниця</option>
                                <option value='{"evenOrOdd":"ODD","weekDay":"SATURDAY"}'>Субота</option>
                                <option value='{"evenOrOdd":"ODD","weekDay":"SANDAY"}'>Неділя</option>
                            </optgroup>
                        </select>
                    </div>

                    <div class="form-group">
                        <input ng-model="time" class="form-control" type="text" name="time" placeholder="00:00" required validate-time>
                    </div>

                    <div class="modal-footer">
                        <button type="submit" ng-click="addTime_()" class="btn btn-info btn-sm" ng-disabled="addTime.$invalid">Додати</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<!-- !Time modal -->


<!-- Delete modal -->
<div class="modal fade" id="confirm-delete" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Підтверження!</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>

            <div class="modal-body">
                <p>Ви бажаєте продовжити?</p>
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
<%--<div id="tables-for-clone" class="d-none">--%>
    <%--<table class="table">--%>
        <%--<thead>--%>
        <%--<tr>--%>
            <%--<th colspan="7">Парні</th>--%>
        <%--</tr>--%>
        <%--<tr>--%>
            <%--<th>Понеділок</th>--%>
            <%--<th>Вівторок</th>--%>
            <%--<th>Середа</th>--%>
            <%--<th>Четверг</th>--%>
            <%--<th>П'ятниця</th>--%>
            <%--<th>Субота</th>--%>
            <%--<th>Неділя</th>--%>
        <%--</tr>--%>
        <%--</thead>--%>
        <%--<tbody>--%>
        <%--<tr>--%>
            <%--<td id="even_monday"><!--ПН-->--%>
            <%--</td>--%>
            <%--<td id="even_tuesday"><!--ВТ-->--%>
            <%--</td>--%>
            <%--<td id="even_wednesday"><!--СР-->--%>
            <%--</td>--%>
            <%--<td id="even_thursday"><!--ЧТ-->--%>
            <%--</td>--%>
            <%--<td id="even_friday"><!--ПН-->--%>
            <%--</td>--%>
            <%--<td id="even_saturday"><!--СБ-->--%>
            <%--</td>--%>
            <%--<td id="even_sanday"><!--НД-->--%>
            <%--</td>--%>
        <%--</tr>--%>
        <%--</tbody>--%>
    <%--</table>--%>
    <%--&lt;%&ndash;Не парні&ndash;%&gt;--%>
    <%--<table class="table">--%>
        <%--<thead>--%>
        <%--<tr>--%>
            <%--<th colspan="7">Непарні</th>--%>
        <%--</tr>--%>
        <%--<tr>--%>
            <%--<th>Понеділок</th>--%>
            <%--<th>Вівторок</th>--%>
            <%--<th>Середа</th>--%>
            <%--<th>Четверг</th>--%>
            <%--<th>П'ятниця</th>--%>
            <%--<th>Субота</th>--%>
            <%--<th>Неділя</th>--%>
        <%--</tr>--%>
        <%--</thead>--%>
        <%--<tbody>--%>
        <%--<tr>--%>
            <%--<td id="odd_monday"><!--ПН-->--%>
            <%--</td>--%>
            <%--<td id="odd_tuesday"><!--ВТ-->--%>
            <%--</td>--%>
            <%--<td id="odd_wednesday"><!--СР-->--%>
            <%--</td>--%>
            <%--<td id="odd_thursday"><!--ЧТ-->--%>
            <%--</td>--%>
            <%--<td id="odd_friday"><!--ПН-->--%>
            <%--</td>--%>
            <%--<td id="odd_saturday"><!--СБ-->--%>
            <%--</td>--%>
            <%--<td id="odd_sanday"><!--НД-->--%>
            <%--</td>--%>
        <%--</tr>--%>
        <%--</tbody>--%>
    <%--</table>--%>
<%--</div>--%>
<%--!Table body for clone --%>
</body>
</html>
