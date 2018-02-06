var app = angular.module("myApp");
//validator for input time
app.directive('validateTime', function () {
    return {
        require: 'ngModel',
        link: function ($scope, element, attrs, ngModel) {
            ngModel.$validators.validateTime = function (modelValue, viewValue) {
                var value = modelValue || viewValue;
                return /^(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$/.test(value)
            };
        }
    }
});