angular.module('myApp')
    .factory('scheduleService', scheduleService);

function scheduleService($http, constants) {
    return {
        getListByDoctor:getListByDoctor,
        findById:findById,
        add:add,
        update:update,
        delete:delete_
    };

    function getListByDoctor(doctorId){
        return $http.get(constants.schedulesByDoctorUrl.replace('{doctorId}', doctorId));
    }

    function findById(scheduleId){
        return $http.get(constants.scheduleById.replace('{id}', scheduleId));
    }

    function add(schedule){
        return $http.post(constants.scheduleAddUrl,schedule);
    }

    function update(schedule){
        return $http.put(constants.scheduleUpdateUrl,schedule);
    }

    function delete_(schedule){
        return $http.delete(constants.scheduleDeleteUrl.replace('{id}', schedule.id));
    }
}



