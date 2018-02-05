angular.module('myApp')
    .factory('ticketService', ticketService);

function ticketService($http, constants) {
    return {
        getPickerList:getPickerList,
        getListBy: getListBy,
        getListByPatient: getListByPatient,
        getListByDoctor: getListByDoctor,
        cancel:cancel,
        edit:edit,
        reserve:reserve,
        lock:lock,
        unlock:unlock

    };

    function getPickerList(doctorId){
        return $http.get(constants.ticketPickerListUrl.replace("{doctorId}", doctorId));
    }

    function getListBy(url) {
        return $http.get(url);
    }

    function getListByPatient(paramUrl) {
        return $http.get(constants.ticketsByPatientUrl + paramUrl);
    }

    function getListByDoctor(paramUrl) {
        return $http.get(constants.ticketsByDoctorUrl + paramUrl);
    }

    function getListByUrl(url) {
        return $http.get(url);
    }

    function cancel(ticket) {
        return $http.put(constants.ticketCancelUrl, ticket);
    }

    function edit(ticket) {
        return $http.put(constants.ticketEditUrl, ticket);
    }

    function reserve(ticket){
        return $http.post(constants.ticketReserveUrl,JSON.stringify(ticket));
    }

    function lock(ticket){
        return $http.put(constants.ticketLockUrl,JSON.stringify(ticket));
    }

    function unlock(ticket){
        return $http.put(constants.ticketUnlockUrl,JSON.stringify(ticket));
    }


}



