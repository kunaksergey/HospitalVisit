var app = angular.module("myApp");
//validator for schedule date
app.directive('validateDate', function () {
    return {
        require: 'ngModel',
        link: function ($scope, element, attrs, ngModel) {
            ngModel.$validators.validateDate = function (modelValue, viewValue) {
                var value = modelValue || viewValue;
                return /^(0[0-9]|1[0-9]|2[0-9]|3[0-1])-(0[1-9]|1[1-2]|)-(19[0-9][0-9]|20[0-9][0-9])$/.test(value)
            };
        }
    }
});