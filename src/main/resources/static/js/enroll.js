(function () {
    var app = angular.module("enrollApp", []);
    var urlTickets = "/ticket/3";

    app.controller("enrollCtrl", function ($scope, $http) {
        //  var element = angular.element('#doctorId');
        // // var doctorId=element.getAttribute('data-doctorId');
        //  console.log(element);

        $http({
            method: 'get',
            url: urlTickets
        }).then(function (response) {
            $scope.tickets = response.data;
            console.log($scope.tickets);
        }, function (error) {
            console.log(error, 'can not get data.');
        });
        $scope.enroll = function (ticket) {
            console.log(ticket);
        }
    });

})();