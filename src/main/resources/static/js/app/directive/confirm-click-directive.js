var app = angular.module("myApp");
app.directive("confirmClick", confirmClick);

function confirmClick() {
    return {
        restrict: 'A',
        priority: 1000,
        scope: {
            template: '@',
            confirmClick: '&'
        },
        link: function (scope, element, attr) {
            element.bind('click', function (event) {
                scope.openModal();
            });
        },
        controller: confirmClickCtrl
    }
}

function confirmClickCtrl($scope, $uibModal, constants) {

    $scope.openModal = function () {
        $uibModal.open({
            templateUrl: constants.templateUrl + $scope.template,
            windowClass: 'modal-zindex-confirm',
            backdrop: false,
            resolve: {
                clickAction: function () {
                    return function () {
                         $scope.$eval($scope.confirmClick);
                    }
                }
            },
            controller: function ($scope, $uibModalInstance, clickAction) {
                $scope.cancel = function () {
                    $uibModalInstance.close();
                };

                $scope.ok = function () {
                    clickAction();
                    $uibModalInstance.close();
                }
            }
        });

    }

}
