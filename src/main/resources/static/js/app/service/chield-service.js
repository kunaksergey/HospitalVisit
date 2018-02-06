angular.module('myApp')
    .factory('chieldService', chieldService);

function chieldService($http, constants) {
    return {
        getListByPatient:getListByPatient,
        add: add
    };

    function getListByPatient(){
        return $http.get(constants.chieldsByPatientUrl);
    }

    function add(chield){
        return $http.post(constants.chieldAddUrl,chield);
    }


}



