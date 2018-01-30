<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Інформіція про лікаря</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.5/umd/popper.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.7/angular.min.js"></script>
    <script src="https://angular-ui.github.io/bootstrap/ui-bootstrap-tpls-1.2.4.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="/js/bootstrap.js"></script>
    <script src="/js/bootstrap.bundle.js"></script>
    <script src="/js/jquery-ui.js"></script>
    <script src="/js/ticket-grid.js"></script>
    <link rel="stylesheet" href="/css/style.css"/>
</head>

<body>
<div class="container">
    <div class="card-doctor-big">
        <div class="doctor-card__content">
            <div class="doctor-card__top">
                <div class="doctor-photo doctor-photo_big user-photo user-photo_loaded">
                    <img class="doctor-photo__img doctor-photo__img_big user-photo__img"
                         src=""
                         draggable="false">
                </div>
                <div class="doctor-card__info doctor-card__info_big">
                    <h3 class="doctor-card__name">${doctor.user.fullName}</h3>
                    <div class="doctor-card__speciality">
                        <c:forEach var="item" items="${doctor.specializations}">
                            <div><c:out value="${item.name}"/></div>
                        </c:forEach>
                    </div>
                </div>
            </div>

            <div class="doctor-card__bottom">
                <div class="doctor-card__address">
                    <div class="doctor-card__clinic">${doctor.hospital.name}-${doctor.hospital.address}</div>
                    <div class="doctor-card__cabinet">кабінет 212</div>
                </div>
            </div>
        </div>
    </div>


    <div id="detail-content" ng-app="enrollApp" ng-controller="enrollCtrl" ng-init="doctorId=${doctor.id}">
        <%--First ticket-grid--%>
        <div id="first-ticket-grid" class="ticket-grid">
            <div class="ticket-grid-header">
                <button class="bt-next-ticket-grid">></button>
            </div>
            <ticket-grid ng-if="ticketSlotList" start=0 end=7></ticket-grid>
            <div class="ticket-grid-footer"></div>
        </div>
        <%--!First ticket-grid--%>

        <%--Second ticket-grid--%>
        <div id="second-ticket-grid" class="ticket-grid">
            <div class="ticket-grid-header">
                <button class="bt-next-ticket-grid"><</button>
            </div>
            <ticket-grid ng-if="ticketSlotList" start=7 end=14></ticket-grid>
            <div class="ticket-grid-footer"></div>
        </div>
        <%--!Second ticket-grid--%>
    </div>


</div>

</body>
</html>
