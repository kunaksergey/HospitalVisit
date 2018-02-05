<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Особистий кабінет пацієнта</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.7/angular.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.5/umd/popper.min.js"></script>
    <script src="https://angular-ui.github.io/bootstrap/ui-bootstrap-tpls-1.2.4.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.6.4/js/bootstrap-datepicker.min.js"></script>
    <script src="/js/bootstrap-datepicker.ua.js" charset="UTF-8"></script>
    <script src="/js/bootstrap.bundle.js"></script>
    <script src="/js/bootstrap.js"></script>
    <script src="/js/app/app.js"></script>
    <script src="/js/app/common/constants.js"></script>
    <script src="/js/app/service/ticket-service.js"></script>
    <script src="/js/app/service/chield-service.js"></script>
    <script src="/js/app/directive/history-ticket-directive.js"></script>
    <script src="/js/app/directive/confirm-click-directive.js"></script>
    <script src="/js/app/directive/photo-upload-directive.js"></script>
    <script src="/js/app/controller/patientCtrl.js"></script>
    <script src="/js/app/controller/chieldCtrl.js"></script>
    <script src="/js/app/lib/dateFormat.js"></script>
    <link rel="stylesheet" href="/css/style.css">
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.6.4/css/bootstrap-datepicker.standalone.min.css"/>
</head>

<body ng-app="myApp" ng-controller="patientCtrl" ng-init="userId=${user.id}">
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
    <h3>Особистий кабінет пацієнта:</h3>
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

            <!-- Card Patient -->
            <div class="card">
                <div class="card-block">
                    <div class="row">
                        <!-- Form -->
                        <div class="col-6">
                            <form:form method="POST" action="/patient/cabinet" modelAttribute="user">
                                <div class="form-group">
                                    <form:hidden path="id"/>
                                </div>

                                <div class="form-group">
                                    <label for="fullName">ПІБ:</label>
                                    <form:input path="fullName" class="form-control form-control-sm"/>
                                </div>

                                <div class="form-group">
                                    <label for="birthDay" class="control-label">День народження:</label>
                                    <form:input path="birthday" class="form-control form-control-sm"/>
                                </div>

                                <div class="form-group">
                                    <label for="phone" class="control-label">Телефон:</label>
                                    <form:input path="phone" class="form-control form-control-sm"/>
                                </div>

                                <div class="form-group">
                                    <label for="email" class="control-label">Email:</label>
                                    <form:input path="email" class="form-control form-control-sm"/>
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
            <%--!Card Patient--%>

            <!-- Card chield-->
            <div class="card" ng-controller="chieldCtrl">
                <div class="card-header">
                    <a data-toggle="modal" data-target="#addChieldModal" class="btn btn-success btn-sm" href=''>
                        <i class="fa fa-plus-circle" aria-hidden="true"></i>
                        <span>Зареєструвати дитину</span>
                    </a>
                </div>
                <div class="card-block">
                    <h4 class="card-title">Діти</h4>
                    <div ng-repeat="item in chieldList">
                        {{item.fullName}}:{{item.birthDay}}
                    </div>

                </div>

                <!-- Chield modal -->
                <div class="modal fade" id="addChieldModal" tabindex="-1" role="dialog" aria-hidden="true">
                    <div class="modal-dialog modal-lg" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title">Додати дитину</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"
                                        ng-click="closeModal()">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body" ng-form name="chieldForm">
                                <div class="row">
                                    <div class="col-lg-6">
                                        <div class="input-group">
                                            <span class="input-group-addon" id="basic-addon1">ПІБ</span>
                                            <input ng-model="chield.fullName" class="form-control"
                                                   placeholder="ПІБ Дитини"
                                                   aria-describedby="basic-addon1" required>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-lg-6">
                                        <div class="input-group">
                                            <span class="input-group-addon" id="basic-addon2">День народження</span>
                                            <input class="datepicker" ng-model="chield.birthDay" class="form-control"
                                                   placeholder="dd-MM-yyyy"
                                                   aria-describedby="basic-addon2" required validate-date>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <a href='' class="btn btn-default btn-sm" ng-click="closeModal()" data-dismiss="modal">Відміна
                                </a>
                                <a href='' class="btn btn-info btn-sm" confirm-click="save()" template="confirm-modal.html">
                                    <%--ng-disabled="chieldForm.$invalid"--%>
                                    Зберегти
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- !Chield modal -->
            </div>
            <!-- !Card chield-->
        </div>
        <!-- !Tab Profile -->

        <!-- Tab History -->
        <div class="tab-pane" id="tab_history" role="tabpanel">
            <history-ticket target-history-user="patient" template="history-ticket-patient.html"/>
        </div>
        <!-- !Tab History -->

    </div>
    <!-- !Tab panes -->
</div>


</body>
</html>




