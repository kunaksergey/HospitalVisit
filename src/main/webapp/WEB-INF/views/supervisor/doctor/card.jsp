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
    <%--<link rel="stylesheet" href="/css/parts-selector.css">--%>
    <link rel="stylesheet" href="/css/style.css">
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.7/angular.min.js"></script>
    <script src="https://angular-ui.github.io/bootstrap/ui-bootstrap-tpls-1.2.4.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.5/umd/popper.min.js"></script>
    <script src="/js/bootstrap.js"></script>
    <script src="/js/bootstrap.bundle.js"></script>
    <script src="/js/photo-upload.js"></script>
    <%--<script src="/js/parts-selector.js"></script>--%>
    <script src="/js/app/app.js"></script>
    <script src="/js/app/common/constants.js"></script>
    <script src="/js/app/directive/photo-upload-directive.js"></script>
    <script src="/js/app/directive/confirm-click-directive.js"></script>
    <script src="/js/app/directive/week-directive.js"></script>
    <script src="/js/app/directive/time-directive.js"></script>
    <script src="/js/app/directive/validate-time-directive.js"></script>
    <script src="/js/app/directive/validate-data-directive.js"></script>
    <script src="/js/app/service/schedule-service.js"></script>
    <script src="/js/app/controller/scheduleCtrl.js"></script>
    <style>

    </style>
</head>
<body ng-app="myApp" ng-controller="scheduleCtrl" ng-init="doctorId=${doctorForm.id}; userId=${doctorForm.userId}">
<!-- Nav -->
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <ul class="nav navbar-nav">
            <li class="active"><a href="/">Головна</a></li>
        </ul>
    </div>
</nav>
<!-- !Nav -->
<div class="container">
    <%--Card doctor--%>
    <div class="card">
        <h3 class="card-header">Облікова картка лікаря:</h3>
        <div class="card-block">
            <h5 class="card-title"><c:out value="${doctorForm.fullName}"/></h5>
            <div class="container">
                <div class="row">
                    <!-- Form -->
                    <div class="col-6">
                        <form:form method="POST" action="/supervisor/doctor/save" modelAttribute="doctorForm">
                            <div class="form-group">
                                <form:hidden path="id"/>
                            </div>

                            <div class="form-group">
                                <label for="fullName">ПІБ:</label>
                                <form:input path="fullName" class="form-control form-control-sm"/>
                            </div>

                            <div class="form-group">
                                <label for="birthDay" class="control-label">День народження:</label>
                                <form:input path="birthDay" class="form-control form-control-sm"/>
                            </div>

                            <div class="form-group">
                                <label for="phone" class="control-label">Телефон:</label>
                                <form:input path="phone" class="form-control form-control-sm"/>
                            </div>

                            <div class="form-group">
                                <label for="email" class="control-label">Email:</label>
                                <form:input path="email" class="form-control form-control-sm"/>
                            </div>

                            <div class="form-group">
                                <div class="checkbox checkbox-info">
                                    <ul>
                                        <form:checkboxes element="li" items="${specializations}"
                                                         path="specializations" itemValue="id" itemLabel="name"/>
                                    </ul>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="checkbox checkbox-info">
                                    <form:checkbox path="enable" label="Активован"/>
                                </div>
                            </div>

                            <button type="submit" class="btn btn-info btn-sm">Зберегти</button>

                        </form:form>
                        <!-- !Form -->
                    </div>

                    <!-- Photo upload -->
                    <div class="col-2 text-center">
                        <photo-upload user-id="{{userId}}"></photo-upload>
                    </div>
                    <!-- !Photo upload -->


                </div>
            </div>
            <%--!Card doctor--%>

            <%--ScheduleList --%>
            <div id="schedule-menu" class="list-group">
                <div class="list-group-item">
                    <a href='' ng-click="addSchedule()" class="btn btn-success btn-sm"
                       data-toggle="modal" data-target="#scheduleModal">
                        <i class="fa fa-plus-circle" aria-hidden="true"></i>
                        <span>Створити</span>
                    </a>
                </div>

                <div class="list-group-item" ng-repeat="schedule in scheduleList | orderBy:orderByDate:false">
                    <span>Розклад:&nbsp{{schedule.start}} ({{schedule.notice}})</span>

                    <span class="pull-right">
                        <a href='' title="Редагувати"
                           ng-click="loadSchedule(schedule.id)"
                           data-toggle="modal" data-target="#scheduleModal">
                            <i class="fa fa-pencil" aria-hidden="true"></i>
                        </a>
                        <i class="fa fa-trash-o" aria-hidden="true"
                                 confirm-click="removeSchedule(schedule)" template="delete-modal.html">
                        </i>
                    </span>
                </div>
            </div>
            <%--!ScheduleList --%>

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
                    <div class="modal-body" ng-form name="scheduleForm">
                        <div class="row">
                            <div class="col-lg-6">
                                <div class="input-group">
                                    <span class="input-group-addon" id="basic-addon1">Кабінет</span>
                                    <input ng-model="schedule.room" class="form-control" placeholder="номер кабінету"
                                           aria-describedby="basic-addon1" required>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-lg-6">
                                <div class="input-group">
                                    <span class="input-group-addon" id="basic-addon2">Дата</span>
                                    <input ng-model="schedule.start" class="form-control" placeholder="dd-MM-yyyy"
                                           aria-describedby="basic-addon2" required validate-date>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-lg-6">
                                <div class="input-group">
                                    <span class="input-group-addon" id="basic-addon3">Замітки</span>
                                    <input ng-model="schedule.notice" class="form-control" placeholder="замітки"
                                           aria-describedby="basic-addon3">
                                </div>
                            </div>
                        </div>
                        <!--Week Even or Odd-->
                        <div id="table-content">
                            <week even-or-odd="EVEN" title="Парні"></week>
                            <week even-or-odd="ODD" title="Непарні"></week>
                        </div>
                        <!--!Week Even or Odd-->
                        <p><a href="#" data-toggle="modal" data-target="#timeModal">Час:додати</a></p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary btn-sm" data-dismiss="modal">Відміна</button>
                        <button type="button" class="btn btn-info btn-sm" ng-disabled="scheduleForm.$invalid"
                                data-toggle="modal" data-target="#confirm-save">
                            Зберегти
                        </button>
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
                                <select ng-model="selected" name="day" class="form-control" data-header="Виберіть день"
                                        data-width="75%" required>
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
                                <input ng-model="time" class="form-control" type="text" name="time" placeholder="00:00"
                                       required
                                       validate-time>
                            </div>

                            <div class="modal-footer">
                                <button type="submit" ng-click="addTime_()" class="btn btn-info btn-sm"
                                        ng-disabled="addTime.$invalid">Додати
                                </button>
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
        <!-- !Delete modal -->

        <!-- Save schedule modal -->
        <div class="modal fade" id="confirm-save" tabindex="-1" role="dialog" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">Підтверження!</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>

                    <div class="modal-body">
                        <p>Зберегти?</p>
                    </div>

                    <div class="modal-footer">
                        <button class="btn btn-default" data-dismiss="modal">Відмінити</button>
                        <button class="btn btn-success btn-ok" data-dismiss="modal" ng-click="save()">Зберегти</button>
                    </div>
                </div>
            </div>
        </div>
        <!-- !Save schedule modal -->

    </div>
</body>
</html>
