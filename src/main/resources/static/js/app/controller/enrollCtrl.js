var app = angular.module("myApp");

app.controller("enrollCtrl", function ($scope, $http, $window, ticketService) {

    $scope.$watch('doctorId', function () {
        ticketService.getPickerList($scope.doctorId)
            .then(function (response) {
                $scope.ticketSlotList = response.data;
                console.log(response.data);
            }, function (error) {
                console.log(error, 'can not get data.');
            });
    });


    $scope.reserve = function (ticket) {
        var ticket1=ticket;
        ticket1.time="";
        ticketService.reserve(ticket1)
            .then(function (response) {
                update(ticket,response.data);
            }, function (error) {
                console.log(error.status);
                if (error.status === 401) {
                    $window.location.href = "/login";
                }
            });
        $scope.selectedTicket={};
    };

    // Update data model
    function update(ticket,data) {
        console.log(data);
        console.log(ticket);
        for (var i = 0; i < $scope.ticketSlotList.length; i++) {
            for (var j = 0; j < $scope.ticketSlotList[i].ticketDtoList.length; j++) {
                if ($scope.ticketSlotList[i].ticketDtoList[j] === ticket) {
                    $scope.ticketSlotList[i].ticketDtoList[j] = data;
                    break;
                }
            }
        }
    }
});