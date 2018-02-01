angular.module('myApp')
    .factory('ticketService', ticketService);

function ticketService($http, constants) {
    return {
        getTicketsByPatient: getTicketsByPatient,
        getTicketsByDoctor: getTicketsByDoctor,
        cancelTicket:cancelTicket,
        doneTicket:doneTicket
    };

    function getTicketsByPatient(paramUrl) {
        return $http.get(constants.ticketsByPatientUrl + paramUrl);
    }

    function getTicketsByDoctor(paramUrl) {
        return $http.get(constants.ticketsByDoctorUrl + paramUrl);
    }

    function cancelTicket(ticket) {
        return $http.put(constants.ticketCancelUrl, ticket);
    }

    function doneTicket(ticket) {
        return $http.put(constants.ticketDoneUrl, ticket);
    }
}



