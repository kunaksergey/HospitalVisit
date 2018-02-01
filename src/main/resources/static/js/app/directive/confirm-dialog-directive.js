var app = angular.module("myApp");
app.directive("confirmDialog", confirmDialog);

function confirmDialog() {
    return {
        restrict: 'A',
        priority: 1,
        terminal: true,
        scope: {
            template: '@',
            ngClick: '&'
        },
        link: function (scope, element, attr) {
            element.bind('click', function (event) {
                scope.openModal();
            });
        },
        controller: confirmDialogCtrl
    }
}

function confirmDialogCtrl($scope, $uibModal, constants) {

    $scope.openModal = function () {
        $uibModal.open({
            templateUrl: constants.templateUrl + $scope.template,
            windowClass: 'modal-zindex-confirm',
            backdrop: false,
            resolve: {
                clickAction: function () {
                    return function () {
                        $scope.$eval($scope.ngClick)
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
