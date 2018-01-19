var app = angular.module("myApp", []);
app.controller("myCtrl", myCtrl);

// directive for week(Even or Odd)
app.directive("week", function () {
    return {
        scope: true,
        link: function (scope, element, attrs) {
            scope.evenOrOdd = attrs.evenOrOdd;
            scope.title = attrs.title;
        },
        templateUrl: '/js/template/week-directive.html'
    }

});


// directive for scheduleTime in ticketPicker
app.directive("time", function () {
    return {
        restrict: 'E',
        scope: true,
        link: function (scope, element, attrs) {
            scope.evenOrOdd = attrs.evenOrOdd;
            scope.weekDay = attrs.weekDay;
        },
        templateUrl: '/js/template/time-directive.html'

    }

});

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


function myCtrl($scope, $http) {
    $scope.weekDays = ["MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY", "SANDAY"];
    $scope.addSchedule = function () {
        $scope.schedule = [];
    };

    //load particular schedule from REST
    $scope.loadSchedule = function (scheduleUrl) {
        $http({
            method: 'GET',
            url: scheduleUrl
        }).then(function successCallback(response) {
            $scope.schedule = response.data.scheduleDays;
        }, function errorCallback(response) {
            // called asynchronously if an error occurs
            // or server returns response with an error status.
        });
    };

    //add time to schedule
    $scope.addTime_ = function () {
        var evenOrOdd = JSON.parse($scope.selected).evenOrOdd;
        var weekDay = JSON.parse($scope.selected).weekDay;
        var time = new Date('1970-01-01T' + $scope.time + 'Z');
        var currentSchedule = null;
        angular.forEach($scope.schedule, function (value, key) {
            if ((evenOrOdd === value.evenOrOdd) && (weekDay === value.weekDay)) {
                currentSchedule = value;
            }
        });
        if (currentSchedule !== null) {
            var index = 0;
            var hasTime = false;
            angular.forEach(currentSchedule.scheduleTime, function (value, key) {
                var currentTime = new Date('1970-01-01T' + value.time + 'Z');
                if (time > currentTime) {
                    index = key + 1;
                }
                if (time.getTime() === currentTime.getTime()) {
                    hasTime = true;
                }
            });

            if (index >= 0 && !hasTime) {
                currentSchedule.scheduleTime.splice(index, 0, {"time": $scope.time});
            }
        } else {
            $scope.schedule.push({"evenOrOdd": evenOrOdd, "weekDay": weekDay, "scheduleTime": [{"time": $scope.time}]});
        }

        $scope.selected = null;
        $scope.time = null;

    };


    //remove time from schedule
    $scope.removeTime_ = function (evenOrOdd, weekDay, time) {
        angular.forEach($scope.schedule, function (value, key) {
            var ticketPicker = value;
            var scheduleTimeList = value.scheduleTime;
            angular.forEach(scheduleTimeList, function (value, key) {
                if ((evenOrOdd === ticketPicker.evenOrOdd) && (weekDay === ticketPicker.weekDay) && (time === value.time)) {
                    console.log("удаляем");
                    console.log("key:" + key);
                    scheduleTimeList.splice(key, 1);
                }
            });
        });

    };

}