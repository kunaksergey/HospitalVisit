$(document).ready(function () {
    $('.datepicker').datepicker({
        format: 'dd-mm-yyyy',
        minDate: -0,
        maxDate: new Date(2017, 12, 10),
        language: 'ua'
    });
});

