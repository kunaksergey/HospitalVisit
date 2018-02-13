angular.module('myApp')
    .value('constants', {
        weekdays:["MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY", "SANDAY"],
        weekDaysIn18:["Понеділок","Вівторок","Середа","Четверг","П'ятниця","Субота","Неділя"],
        templateUrl:'/js/app/template/',
        ticketsByUrl: '/api/secured/v1/ticket/listdata/',
        ticketsByPatientUrl: '/api/secured/v1/ticket/listdata/patient',
        ticketsByDoctorUrl: '/api/secured/v1/ticket/listdata/doctor',
        ticketCancelUrl:'/api/secured/v1/ticket/cancel',
        ticketEditUrl:'/api/secured/v1/ticket/edit',
        ticketLockUrl:'/api/secured/v1/ticket/lock',
        ticketUnlockUrl:'/api/secured/v1/ticket/unlock',
        ticketPickerListUrl:'/api/v1/ticket/listdata/doctor/{doctorId}',
        ticketReserveUrl:'/api/secured/v1/ticket',
        schedulesByDoctorUrl:'/api/secured/v1/schedule/doctor/{doctorId}',
        scheduleById:'/api/secured/v1/schedule/{id}',
        scheduleAddUrl:'/api/secured/v1/schedule',
        scheduleUpdateUrl:'/api/secured/v1/schedule',
        scheduleDeleteUrl:'/api/secured/v1/schedule/{id}',
        chieldsByPatientUrl:'/api/secured/v1/chield/listdata/patient',
        chieldAddUrl:'/api/secured/v1/chield'
    });

