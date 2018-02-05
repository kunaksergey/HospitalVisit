var app = angular.module("myApp");
app.controller("scheduleCtrl", scheduleCtrl);

function scheduleCtrl($scope, $uibModal, scheduleService, constants) {
    $scope.weekDays = constants.weekdays;

    $scope.$watch('doctorId', function () {
        scheduleService.getListByDoctor($scope.doctorId)
            .then(function successCallback(response) {
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
        scheduleService.findById(scheduleId)
            .then(function successCallback(response) {
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
        angular.forEach(scheduleDaySet, function (value, key) {
            var ticketPicker = value;
            var scheduleTimeSetList = value.scheduleTimeSet;
            angular.forEach(scheduleTimeSetList, function (value, key) {
                if ((evenOrOdd === ticketPicker.evenOrOdd) && (weekDay === ticketPicker.weekDay) && (time === value.time)) {
                    scheduleTimeSetList.splice(key, 1);
                }
            });
        });
    };

    //if schedule is new then invoke add(), otherwise invoke update()
    $scope.save = function () {
        if ($scope.schedule.id === null) {
            add();
        } else {
            update();
        }
    };

    function add() {
        scheduleService.add($scope.schedule)
            .then(function successCallback(response) {
                $scope.schedule = response.data;
                $scope.scheduleDaySet = response.data.scheduleDaySet;
                $scope.scheduleList.push($scope.schedule);
            }, function errorCallback(response) {
                // called asynchronously if an error occurs
                // or server returns response with an error status.
            });
    }

    function update() {
        scheduleService.update($scope.schedule)
            .then(function successCallback(response) {
                $scope.schedule = response.data;
                $scope.scheduleDaySet = response.data.scheduleDaySet;
            }, function errorCallback(response) {
                // called asynchronously if an error occurs
                // or server returns response with an error status.
            });
    }

    $scope.removeSchedule = function (schedule) {
        var scheduleList = $scope.scheduleList;
        scheduleService.delete(schedule)
            .then(function successCallback(response) {
                var i = scheduleList.indexOf(schedule);
                if (i !== -1) {
                    scheduleList.splice(i, 1);
                }
            }, function errorCallback(response) {
                // called asynchronously if an error occurs
                // or server returns response with an error status.
            });
    };

    //function for order item by date
    $scope.orderByDate = function (item) {
        return new Date(item.start.replace(/(\d{2})-(\d{2})-(\d{4})/, "$2/$1/$3"));
    };
}