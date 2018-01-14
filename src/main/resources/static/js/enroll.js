var app = angular.module("enrollApp", []);

app.filter('slice', function () {
    return function (arr, start, end) {
        return (arr || []).slice(start, end);
    };
});

app.controller("enrollCtrl", function ($scope, $http) {
    var doctorId = angular.element($('#doctorId')).data('id');
    var urlTickets = "/api/v1/ticket/doctor/" + doctorId;
    var urlEnroll = "/api/v1/ticket";
    console.log(urlTickets);


    $http({
        method: 'get',
        url: urlTickets
    }).then(function (response) {
        $scope.ticketSlotList = response.data;
        console.log($scope.ticketSlotList);
    }, function (error) {
        console.log(error, 'can not get data.');
    });


    $scope.enroll = function (ticket) {

        $http({
            method: 'post',
            url: urlEnroll,
            data: JSON.stringify(ticket)
        }).then(function (response) {
            update(response.data);
        }, function (error) {
            console.log(error, 'can not get data.');
        });

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


