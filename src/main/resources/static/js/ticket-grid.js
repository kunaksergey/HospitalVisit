$(document).ready(function () {
    // ticket-grid carusel
    $(".bt-next-ticket-grid").click(function () {
        console.log("tee");
        $("#first-ticket-grid").toggle("slide", {direction: "left"}, 500);
        $("#second-ticket-grid").toggle("slide", {direction: "right"}, 500);
    });
    // !ticket-grid carusel
});

var app = angular.module("enrollApp", ['ui.bootstrap'])
    .config(function (uibDropdownConfig) {
        uibDropdownConfig.openClass = 'show';
    });

app.controller("enrollCtrl", function ($scope, $http, $window) {
    var urlTicketListData = "/api/v1/ticket/listdata/doctor/{doctorId}";
    var urlTicketAdd = "/api/secured/v1/ticket";

    $scope.$watch('doctorId', function () {
        $http({
            method: 'get',
            url: urlTicketListData.replace("{doctorId}", $scope.doctorId)
        }).then(function (response) {
            $scope.ticketSlotList = response.data;
        }, function (error) {
            console.log(error, 'can not get data.');
        });
    });


    //
    $scope.add = function (ticket) {
        $http({
            method: 'post',
            url: urlTicketAdd,
            data: JSON.stringify(ticket)
        }).then(function (response) {
            update(response.data);
        }, function (error) {
            console.log(error.status);
            if (error.status === 401) {
                $window.location.href = "/login";
            }
        });

        // Update data model
        function update(data) {
            for (var i = 0; i < $scope.ticketSlotList.length; i++) {
                for (var j = 0; j < $scope.ticketSlotList[i].ticketDtoList.length; j++) {
                    if ($scope.ticketSlotList[i].ticketDtoList[j] === ticket) {
                        $scope.ticketSlotList[i].ticketDtoList[j] = data;
                        break;
                    }
                }
            }
        }
    };

});

app.directive("ticketGrid", function () {
    return {
        replace:true,
        scope: true,
        link: function (scope, element, attrs) {
            scope.ticketSlotSubList = (scope.ticketSlotList || []).slice(attrs.start, attrs.end);
        },
        controller: ticketGridCtrl,
        templateUrl: '/js/app/template/test.html'
    }
});

function ticketGridCtrl($scope, $uibModal) {
    $scope.selectTicket = function (ticket) {
        var mainScope = $scope;

        $uibModal.open({
            templateUrl: '/js/app/template/confirm-modal.html',
            windowClass: 'modal-zindex',
            controller: function ($scope, $uibModalInstance) {
                $scope.confirm = function () {
                    mainScope.add(ticket);
                    $uibModalInstance.close();
                };

                $scope.cancel = function () {
                    $uibModalInstance.dismiss('cancel');
                    $uibModalInstance.close();
                };
            }
        });


    };


}

