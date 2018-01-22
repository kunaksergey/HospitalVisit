$(document).ready(function () {
    var doctorId = $("#id").val();
    loadShedule(doctorId);


});

//load list of schedules
function loadShedule(doctorId) {
    var urlSchedule = '/api/v1/schedule/doctor/{id}'.replace('{id}', doctorId);
    $.ajax
    ({
        type: 'GET',
        url: urlSchedule,
        dataType: 'json',
        success: function (data) {
            var $div = createListSheduleFromRequest(data);
            $("#schedule-list").empty().append($div);
            angular.element(document.body).injector().invoke(function($compile) {
                var scope = angular.element($div).scope();
                $compile($div)(scope);
            });
        },
        error: function (xhr, ajaxOptions, throwError) {

        }
    });
}

function createListSheduleFromRequest(data) {
    var html = '';
    $.each(data, function (index, value) {
        html += '<a class="schedule-loaded list-group-item"' +
            'data-toggle="modal" data-target="#scheduleModal"' +
            'ng-click="loadSchedule(\'{id}\')"'.replace('{id}', value.id) +
            'href="/schedule/{id}">'.replace('{id}', value.id) +
            'Розклад:{start}'.replace('{start}', value.start) +
            '({notice})'.replace('{notice}', value.notice)+
            '<a href="#" ng-click="deleteSchedule(\'{id}\')">delete</a>'.replace('{id}', value.id) +
            '</a>';


    });
    return $.parseHTML(html);
}

