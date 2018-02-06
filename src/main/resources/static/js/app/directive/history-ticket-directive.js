var app = angular.module("myApp");

app.directive("historyTicket", historyTicket);

function historyTicket(constants) {
    return {
        replace: true,
        scope: {
            targetHistoryUser:'@'
        },
        link: function (scope, element, attrs) {
                $('.datepicker').datepicker({
                format: 'dd-mm-yyyy',
                minDate: -0,
                maxDate: new Date(2017, 12, 10),
                language: 'ua',
                todayBtn:true,
                todayHighlight:true,
                toggleActive:true,
                autoclose: true
            });
            var now = new Date();
            scope.start = now.format("dd-mm-yyyy");
            scope.end = now.format("dd-mm-yyyy");
        },
        controller: historyCtrl,
        templateUrl:function(element, attrs) {
            return constants.templateUrl+attrs.template
        }


    }
}

function historyCtrl($scope, $uibModal, ticketService,constants) {
    var now = new Date();
    $scope.start = now.format("dd-mm-yyyy");
    $scope.end = now.format("dd-mm-yyyy");
    $scope.currentStatus = "PROCESED";

    httpHendler(getRequestParamStr("PROCESED"));

    $scope.currentTickets = function () {
        $scope.currentStatus = "PROCESED";
        httpHendler(getRequestParamStr("PROCESED"));
    };


    $scope.completedTickets = function () {
        $scope.currentStatus = "DONE";
        httpHendler(getRequestParamStr("DONE"));

    };

    $scope.canceledTickets = function () {
        $scope.currentStatus = "CANCELED";
        httpHendler(getRequestParamStr("CANCELED"));
    };

    $scope.reload = function () {
        httpHendler(getRequestParamStr($scope.currentStatus));
    };

    $scope.isProcesed = function (status) {
        return status === 'PROCESED';
    };

    $scope.isDone = function (status) {
        return status === 'DONE';
    };

    $scope.cancelTicket = function (ticket) {
        ticketService.cancel(ticket)
            .then(function successCallback(response) {
                ticket.status = "CANCELED";
                var index = $scope.ticketList.indexOf(ticket);
                $scope.ticketList.splice(index, 1);
            }, function errorCallback(response) {
                $scope.errorMessage = "Error load data";
            });
    };

    $scope.lockTicket = function (ticket) {
        ticketService.lock(ticket)
            .then(function (response) {
                ticket.status = "DONE";
                var index = $scope.ticketList.indexOf(ticket);
                $scope.ticketList.splice(index, 1);
            }, function (error) {
                console.log(error.status);
                if (error.status === 401) {
                    $window.location.href = "/login";
                }
            });
    };

    $scope.unlockTicket = function (ticket) {
        ticketService.unlock(ticket)
            .then(function (response) {
                ticket.status = "PROCESED";
                var index = $scope.ticketList.indexOf(ticket);
                $scope.ticketList.splice(index, 1);
            }, function (error) {
                console.log(error.status);
                if (error.status === 401) {
                    $window.location.href = "/login";
                }
            });
    };



    $scope.editTicket = function (ticket) {
        $uibModal.open({
            templateUrl: '/js/app/template/note-modal.html',
            windowClass: 'modal-zindex',
            resolve: {
                mainScope: function () {
                    return $scope;
                },
                ticket: function () {
                    return ticket;
                }
            },

            controller: function ($scope, $uibModalInstance, mainScope, ticket) {
                $scope.note = ticket.note;
                $scope.close = function () {
                    $uibModalInstance.close();
                };

                $scope.cancel = function () {
                    $uibModalInstance.close();
                };

                $scope.save = function () {
                    ticket.note = $scope.note;
                    ticketService.edit(ticket);
                    $uibModalInstance.close();
                }
            }
        });
    };

    function getRequestParamStr(status) {
        return "?status={status}".replace("{status}", status) +
            "&start={start}".replace("{start}", $scope.start) +
            "&end={end}".replace("{end}", $scope.end)
    }


    function httpHendler(paramUrl) {
       var url=constants.ticketsByUrl+$scope.targetHistoryUser+paramUrl;
        ticketService.getListBy(url)
            .then(function successCallback(response) {
                $scope.ticketList = response.data;
                $scope.errorMessage = "";
            }, function errorCallback(response) {
                $scope.errorMessage = "Error load data";
            });
    }
}
