<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Кабінет лікаря</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.5/umd/popper.min.js"></script>
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.7/angular.min.js"></script>
    <script src="https://angular-ui.github.io/bootstrap/ui-bootstrap-tpls-1.2.4.js"></script>
    <script src="/js/bootstrap.js"></script>
    <script src="/js/bootstrap.bundle.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.6.4/js/bootstrap-datepicker.min.js"></script>
    <script src="/js/bootstrap-datepicker.ua.js" charset="UTF-8"></script>

    <script src="/js/app/app.js"></script>
    <script src="/js/app/common/constants.js"></script>
    <script src="/js/app/service/ticket-service.js"></script>
    <script src="/js/app/directive/confirm-dialog-directive.js"></script>
    <script src="/js/app/doctorCtrl.js"></script>
    <script src="/js/app/lib/dateFormat.js"></script>

    <link rel="stylesheet" href="/css/style.css">
    <link rel="stylesheet" href="/css/bootstrap-datepicker.css">
</head>

<body ng-app="myApp" ng-controller="doctorCtrl" ng-init="userId=${doctor.userId}">
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
    <h3>Кабінет лікаря:</h3>
    <!-- Tab nav -->
    <ul class="nav nav-tabs" role="tablist">
        <li class="nav-item">
            <a class="nav-link active" data-toggle="tab" href="#tab_profile"
               role="tab">Профіль</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" data-toggle="tab" href="#tab_history"
               role="tab">Історія візитів</a>
        </li>
    </ul>
    <!-- !Tab nav -->

    <!-- Tab panes -->
    <div class="tab-content">

        <!-- Tab Profile -->
        <div class="tab-pane active" id="tab_profile" role="tabpanel">
           <!-- Card Doctor -->
            <div class="card">
                <div class="card-block">
                    <div class="row">
                        <!-- Form -->
                        <div class="col-6">
                            <form:form method="POST" modelAttribute="doctor">
                                <div class="form-group">
                                    <form:hidden path="id"/>
                                </div>

                                <div class="form-group">
                                    <label for="fullName">ПІБ:</label>
                                    <form:input path="fullName" id="fullName" class="form-control form-control-sm"/>
                                </div>

                                <div class="form-group">
                                    <label for="birthDay" class="control-label">День народження:</label>
                                    <form:input path="birthDay" id="birthDay" class="form-control form-control-sm"/>
                                </div>

                                <div class="form-group">
                                    <label for="phone" class="control-label">Телефон:</label>
                                    <form:input path="phone" id="phone" class="form-control form-control-sm"/>
                                </div>

                                <div class="form-group">
                                    <label for="email" class="control-label">Email:</label>
                                    <form:input path="email" id="email" class="form-control form-control-sm"/>
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
            </div>
           <%--!Card Doctor--%>
        </div>
        <!-- !Tab Profile -->

        <!-- Tab History -->
        <div class="tab-pane" id="tab_history" role="tabpanel">
            <!--Data picker-->
            {{start}}-{{end}}
            <div class="input-daterange input-group">
                <input type="text" class="form-control form-control-sm datepicker" name="start" ng-model="start"/>
                <span class="input-group-addon">-</span>
                <input type="text" class="form-control form-control-sm datepicker" name="end" ng-model="end"/>
                <button type="button" class="btn btn-info btn-sm" ng-click="reload()">
                    <i class="fa fa-search" aria-hidden="true"></i>
                </button>
            </div>
            <!--!Data picker-->

            <!--History menu-->
            <ul class="nav justify-content-center">
                <li class="nav-item">
                    <a class="nav-link" href='' ng-click='currentTickets()'>Поточні</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href='' ng-click='completedTickets()'>Завершені</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href='' ng-click='canceledTickets()'>Відмінені</a>
                </li>
            </ul>
            <!--!History menu-->

            <!-- History content-->
            <div ng-repeat="item in ticketList | orderBy:['date','time']">
                <div class="row">
                    <div class="col-4">
                        <i class="fa fa-calendar" aria-hidden="true"> {{item.date}}</i>
                        <i class="fa fa-clock-o" aria-hidden="true"> {{item.time}}</i>
                        <span>({{item.patientFullName}})</span>
                        <span ng-if='isProcesed(item.status)' class="pull-right">
                            <i class="fa fa-pencil" aria-hidden="true"
                               ng-click='editTicket(item)'>
                            </i>
                            <i class="fa fa-trash-o" aria-hidden="true"
                               ng-click='cancelTicket(item)' confirm-dialog template="delete-modal.html">
                            </i>
                        </span>
                        <i ng-if='isDone(item.status)' class="fa fa-eye pull-right" aria-hidden="true"
                           data-toggle="collapse"
                           data-target="#collapse_{{$index}}">
                        </i>
                    </div>
                </div>
                <div class="collapse" id="collapse_{{$index}}">
                    <div class="card card-block">
                        {{item.note}}
                    </div>
                </div>
            </div>

        </div>
        <!-- !Tab History -->

    </div>
    <!-- !Tab panes -->
</div>


</body>
</html>
