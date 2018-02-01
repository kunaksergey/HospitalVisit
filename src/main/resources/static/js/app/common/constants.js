angular.module('myApp')
    .value('constants', {
        ticketsByPatientUrl: '/api/secured/v1/ticket/listdata/patient',
        ticketsByDoctorUrl: '/api/secured/v1/ticket/listdata/doctor',
        ticketCancelUrl:'/api/secured/v1/ticket/cancel',
        ticketDoneUrl:'/api/secured/v1/ticket/done',
        templateUrl:'/js/app/template/'
    });
