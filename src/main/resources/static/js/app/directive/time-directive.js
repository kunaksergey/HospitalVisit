var app = angular.module("myApp");
// directive for scheduleTimeSet in ticketPicker
app.directive("time", function (constants) {
    return {
        restrict: 'E',
        scope: true,
        link: function (scope, element, attrs) {
            scope.evenOrOdd = attrs.evenOrOdd;
            scope.weekDay = attrs.weekDay;
        },
        templateUrl: constants.templateUrl +"time-directive.html"
    }
});