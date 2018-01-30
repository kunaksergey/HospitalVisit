var app = angular.module("myApp");

app.controller("patientCtrl", function ($scope, $http, $uibModal) {
    var baseEndPointUrl = "/api/secured/v1/ticket";

    var mainScope = $scope;

    httpHendler(baseEndPointUrl + "/listdata/patient?status=PROCESED");

    $scope.currentTickets = function () {
        httpHendler(baseEndPointUrl + "/listdata/patient?status=PROCESED");
    };


    $scope.completedTickets = function () {
        httpHendler(baseEndPointUrl + "/listdata/patient?status=DONE");
    };

    $scope.canceledTickets = function () {
        httpHendler(baseEndPointUrl + "/listdata/patient?status=CANCELED");
    };

    $scope.isProcesed = function (status) {
        return status === 'PROCESED';
    };

    $scope.isDone = function (status) {
        return status === 'DONE';
    };

    $scope.cancelTicket = function (ticket) {
        $uibModal.open({
            templateUrl: '/js/app/template/delete-modal.html',
            windowClass: 'modal-zindex',
            resolve: {
                ticket: function () {
                    return ticket;
                }
            },
            controller: function ($scope, $uibModalInstance, ticket) {
                $scope.close = function () {
                    $uibModalInstance.close();
                };

                $scope.cancel = function () {
                    $uibModalInstance.close();
                };

                $scope.delete = function () {
                    console.log(ticket);
                    $http({
                        method: 'PUT',
                        url: baseEndPointUrl + "/cancel",
                        data: ticket
                    }).then(function successCallback(response) {
                        ticket.status = "CANCELED";
                        var index = mainScope.ticketList.indexOf(ticket);
                        mainScope.ticketList.splice(index, 1);
                        $uibModalInstance.close();
                    }, function errorCallback(response) {
                        $scope.errorMessage = "Error load data";
                    });
                }
            }
        });
    };

    function httpHendler(url) {
        $http({
            method: 'GET',
            url: url
        }).then(function successCallback(response) {
            $scope.ticketList = response.data;
            $scope.errorMessage = "";
        }, function errorCallback(response) {
            $scope.errorMessage = "Error load data";
        });
    }
});




