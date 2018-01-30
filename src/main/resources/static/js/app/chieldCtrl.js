var app = angular.module("myApp");

app.controller("chieldCtrl", chieldCtrl);

function chieldCtrl($scope, $http, $uibModal) {
    var baseEndPointUrl = "/api/secured/v1/chield";
    var listEndPointUrl = "/api/secured/v1/chield/listdata";
    var addEndPointUrl = "/api/secured/v1/chield/add";
    $scope.chieldList = [];
    getChields();

    $scope.closeModal = function () {
        $scope.chield={};
    };
    $scope.save = function () {
        addChield();
    };

    function getChields() {
        $http({
            method: 'GET',
            url: listEndPointUrl
        }).then(function successCallback(response) {
            $scope.chieldList = response.data;
            $scope.errorMessage = "";
        }, function errorCallback(response) {
            $scope.errorMessage = "Error add data";
        });
    }

    function addChield() {
        $uibModal.open({
            templateUrl: '/js/app/template/confirm-modal.html',
            windowClass: 'modal-zindex',
            resolve: {
                scopeChieldCtrl: function () {
                    return $scope;
                }
            },

            controller: function ($scope, $uibModalInstance, scopeChieldCtrl) {
                $scope.close = function () {
                    scopeChieldCtrl.chield={};
                    $uibModalInstance.close();
                };

                $scope.cancel = function () {
                    scopeChieldCtrl.chield={};
                    $uibModalInstance.close();
                };

                $scope.confirm = function () {
                    $http({
                        method: 'POST',
                        url: addEndPointUrl,
                        data: scopeChieldCtrl.chield
                    }).then(function successCallback(response) {
                        scopeChieldCtrl.chieldList.push(response.data);
                        scopeChieldCtrl.errorMessage = "";
                        $uibModalInstance.close();
                    }, function errorCallback(response) {
                        scopeChieldCtrl.errorMessage = "Error add data";
                        $uibModalInstance.close();
                    });
                    scopeChieldCtrl.chield={};
                }
            }
        });



    }
}