var app = angular.module("myApp", ['ui.bootstrap']);
app.config(function (uibDropdownConfig) {
    uibDropdownConfig.openClass = 'show';
});

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


// directive for scheduleTimeSet in ticketPicker
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


function myCtrl($scope, $http, $uibModal) {
    $scope.weekDays = ["MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY", "SANDAY"];

    $scope.$watch('doctorId', function () {
        var scheduleUrl = '/api/v1/schedule/doctor/{id}'.replace('{id}', $scope.doctorId);
        $http({
            method: 'GET',
            url: scheduleUrl
        }).then(function successCallback(response) {
            $scope.scheduleList = response.data;
        }, function errorCallback(response) {
            // called asynchronously if an error occurs
            // or server returns response with an error status.
        });
    });

    $scope.addSchedule = function () {
        $scope.schedule = {
            id: null,
            doctorId: $scope.doctorId,
            room: $scope.room,
            notice: $scope.notice,
            scheduleDaySet: []
        };
        $scope.scheduleDaySet = $scope.schedule.scheduleDaySet;
    };

    //load particular schedule from REST
    $scope.loadSchedule = function (scheduleId) {
        scheduleUrl='/api/v1/schedule/{id}'.replace('{id}',scheduleId);
        $http({
            method: 'GET',
            url: scheduleUrl
        }).then(function successCallback(response) {
            $scope.schedule = response.data;
            $scope.scheduleDaySet = response.data.scheduleDaySet;
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
        var currentScheduleDaySet = null;
        angular.forEach($scope.scheduleDaySet, function (value, key) {
            if ((evenOrOdd === value.evenOrOdd) && (weekDay === value.weekDay)) {
                currentScheduleDaySet = value;
            }
        });
        if (currentScheduleDaySet !== null) {
            var index = 0;
            var hasTime = false;
            angular.forEach(currentScheduleDaySet.scheduleTimeSet, function (value, key) {
                var currentTime = new Date('1970-01-01T' + value.time + 'Z');
                if (time > currentTime) {
                    index = key + 1;
                }
                if (time.getTime() === currentTime.getTime()) {
                    hasTime = true;
                }
            });

            if (index >= 0 && !hasTime) {
                currentScheduleDaySet.scheduleTimeSet.splice(index, 0, {"time": $scope.time});
            }
        } else {
            $scope.scheduleDaySet.push({
                "evenOrOdd": evenOrOdd,
                "weekDay": weekDay,
                "scheduleTimeSet": [{"time": $scope.time}]
            });
        }

        $scope.selected = null;
        $scope.time = null;

    };

    //remove time from scheduleDaySet
    $scope.removeTime_ = function (evenOrOdd, weekDay, time) {
        var scheduleDaySet = $scope.scheduleDaySet;

        $uibModal.open({
            templateUrl: '/js/template/delete-modal.html',
            windowClass: 'modal-zindex',
            controller: function ($scope, $uibModalInstance) {

                $scope.delete = function () {

                    angular.forEach(scheduleDaySet, function (value, key) {
                        var ticketPicker = value;
                        var scheduleTimeSetList = value.scheduleTimeSet;
                        angular.forEach(scheduleTimeSetList, function (value, key) {
                            if ((evenOrOdd === ticketPicker.evenOrOdd) && (weekDay === ticketPicker.weekDay) && (time === value.time)) {
                                console.log("удаляем");
                                console.log("key:" + key);
                                scheduleTimeSetList.splice(key, 1);
                            }
                        });
                    });
                    $uibModalInstance.close();
                };

                $scope.cancel = function () {
                    $uibModalInstance.dismiss('cancel');
                };
            }
        });


    };
    $scope.save = function () {
        if ($scope.schedule.id === null) {
            add();
        } else {
            update();
        }
    };

    function add() {
        scheduleUrl = "/api/v1/schedule";
        $http({
            method: 'POST',
            url: scheduleUrl,
            data: $scope.schedule
        }).then(function successCallback(response) {
            $scope.schedule = response.data;
            $scope.scheduleDaySet = response.data.scheduleDaySet;
        }, function errorCallback(response) {
            // called asynchronously if an error occurs
            // or server returns response with an error status.
        });
    }

    function update() {
        scheduleUrl = "/api/v1/schedule";
        $http({
            method: 'PUT',
            url: scheduleUrl,
            data: $scope.schedule
        }).then(function successCallback(response) {
            $scope.schedule = response.data;
            $scope.scheduleDaySet = response.data.scheduleDaySet;
        }, function errorCallback(response) {
            // called asynchronously if an error occurs
            // or server returns response with an error status.
        });
    }

    $scope.deleteSchedule = function (scheduleId) {
        scheduleUrl='/api/v1/schedule/{id}'.replace('{id}',scheduleId);
        $http({
            method: 'DELETE',
            url: scheduleUrl
        }).then(function successCallback(response) {

        }, function errorCallback(response) {
            // called asynchronously if an error occurs
            // or server returns response with an error status.
        });
    }

}