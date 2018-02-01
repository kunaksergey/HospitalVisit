var app = angular.module("myApp");

app.controller("doctorCtrl", function ($scope, $uibModal, ticketService) {
    var baseEndPointUrl = "/api/secured/v1/ticket";
    var now = new Date();
    $scope.start = now.format("dd-mm-yyyy");
    $scope.end = now.format("dd-mm-yyyy");
    $scope.currentStatus = "PROCESED";
    var mainScope = $scope;

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
                    console.log($scope);
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

    $scope.cancelTicket = function (ticket) {
        ticketService.cancelTicket(ticket)
            .then(function successCallback(response) {
                ticket.status = "CANCELED";
                var index = mainScope.ticketList.indexOf(ticket);
                mainScope.ticketList.splice(index, 1);
            }, function errorCallback(response) {
                $scope.errorMessage = "Error load data";
            });
    };

    function httpHendler(url) {
        ticketService.getTicketsByDoctor(url)
            .then(function successCallback(response) {
                $scope.ticketList = response.data;
                $scope.errorMessage = "";
            }, function errorCallback(response) {
                $scope.errorMessage = "Error load data";
            });
    }
});




