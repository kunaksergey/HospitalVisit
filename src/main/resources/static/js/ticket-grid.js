$(document).ready(function () {
    // ticket-grid carusel
    $(".bt-next-ticket-grid").click(function () {
        $("#first-ticket-grid").toggle("slide", {direction: "left"}, 500);
        $("#second-ticket-grid").toggle("slide", {direction: "right"}, 500);
    });
    // !ticket-grid carusel
});

var app = angular.module("enrollApp", []);

app.filter('slice', function () {
    return function (arr, start, end) {
        return (arr || []).slice(start, end);
    };
});

app.controller("enrollCtrl", function ($scope, $http) {
    var doctorId = angular.element($('#doctorId')).data('id');
    var urlTicketListData = "/api/v1/ticket/listdata/doctor/" + doctorId;
    var urlTicketAdd = "/api/v1/ticket";
    console.log(urlTicketListData);


    $http({
        method: 'get',
        url: urlTicketListData
    }).then(function (response) {
        $scope.ticketSlotList = response.data;
        console.log($scope.ticketSlotList);
    }, function (error) {
        console.log(error, 'can not get data.');
    });


    $scope.add = function (ticket) {

        $http({
            method: 'post',
            url: urlTicketAdd,
            data: JSON.stringify(ticket)
        }).then(function (response) {
            update(response.data);
        }, function (error) {
            console.log(error, 'can not get data.');
        });

        // Update data model
        function update(data) {
            for (var i = 0; i < $scope.ticketSlotList.length; i++) {
                for (var j = 0; j < $scope.ticketSlotList[i].ticketDtoList.length; j++) {
                    if ($scope.ticketSlotList[i].ticketDtoList[j] === ticket) {
                        console.log(ticket);
                        $scope.ticketSlotList[i].ticketDtoList[j] = data;
                        break;
                    }
                }
            }
        }
    };


});


