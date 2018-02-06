var app = angular.module("myApp");
// directive for week(Even or Odd)
app.directive("week",function (constants) {
    return {
        scope: true,
        link: function (scope, element, attrs) {
            scope.evenOrOdd = attrs.evenOrOdd;
            scope.title = attrs.title;
        },
        templateUrl: constants.templateUrl +"week-directive.html"
    }
});