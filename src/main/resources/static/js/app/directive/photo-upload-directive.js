var app = angular.module("myApp");

app.directive("photoUpload", function () {
    return {
        scope: true,
        link: function (scope, element, attrs) {
            scope.userId = attrs.userId;
            scope.photoUrl = '/photo/user/' + attrs.userId;
        },
        controller:photoUploadCtrl,
        templateUrl: '/js/app/template/photo-upload.html'
    }
});

function photoUploadCtrl($scope, $http) {

    $scope.savePhoto = function () {
        var endPointUrl = "/photo/upload";
        var formData = new FormData();
        formData.append('file', $scope.fileUpload);
        formData.append('userId', $scope.userId);
        $http({
            url: endPointUrl,
            method: "POST",
            data: formData,
            headers: {'Content-Type': undefined}
        }).then(function successCallback(response) {
            var random = (new Date()).toString();
            $scope.photoUrl = $scope.photoUrl + "?cb=" + random;
        }, function errorCallback(response) {
            // called asynchronously if an error occurs
            // or server returns response with an error status.
        });
    };

    $scope.setFile = function (element) {
        $scope.fileUpload = element.files[0];
    }


}