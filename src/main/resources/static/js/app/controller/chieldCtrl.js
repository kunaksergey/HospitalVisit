var app = angular.module("myApp");

app.controller("chieldCtrl", chieldCtrl);

function chieldCtrl($scope,chieldService) {
    $scope.chieldList = [];
    getChields();

    $scope.closeModal = function () {
        $scope.chield = {};
    };

    function getChields() {
        chieldService.getListByPatient()
            .then(function successCallback(response) {
                $scope.chieldList = response.data;
                $scope.errorMessage = "";
            }, function errorCallback(response) {
                $scope.errorMessage = "Error add data";
            });
    }

    $scope.save=function() {
        chieldService.add($scope.chield)
            .then(function successCallback(response) {
                $scope.chieldList.push(response.data);
                $scope.errorMessage = "";
            }, function errorCallback(response) {
                $scope.errorMessage = "Error add data";
            });
        $scope.chield = {};
    }
}