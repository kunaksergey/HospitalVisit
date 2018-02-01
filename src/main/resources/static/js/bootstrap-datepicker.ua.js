/**
 * Ukrainian translation for bootstrap-datepicker
 * Igor Polynets
 */
;(function($){
    $.fn.datepicker.dates['ua'] = {
        days: ["Неділя", "Понеділок", "Вівторок", "Середа", "Четверг", "П'ятниця", "Субота", "Неділя"],
        daysShort: ["Нед", "Пнд", "Втр", "Срд", "Чтв", "Птн", "Суб", "Нед"],
        daysMin: ["Нд", "Пн", "Вт", "Ср", "Чт", "Пт", "Сб", "Нд"],
        months: ["Cічень", "Лютий", "Березень", "Квітень", "Травень", "Червень", "Липень", "Серпень", "Вересень", "Жовтень", "Листопад", "Грудень"],
        monthsShort: ["Січ", "Лют", "Бер", "Квт", "Трв", "Чер", "Лип", "Сер", "Вер", "Жов", "Лис", "Грд"],
        today: "Сьогодні",
        weekStart: 1

    };
}(jQuery));

$(document).ready(function () {
    $('.datepicker').datepicker({
        format: 'dd-mm-yyyy',
        minDate: -0,
        maxDate: new Date(2017, 12, 10),
        language: 'ua',
        todayBtn:true,
        todayHighlight:true,
        toggleActive:true,
        autoclose: true
    });
});
