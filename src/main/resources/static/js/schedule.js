$(document).ready(function () {
    var doctorId = $("#id").val();
    loadShedule(doctorId);

    $("#schedule-add").on("click", function (event) {
        event.preventDefault();
        $("#table-content").empty().append(createEmptyScheduleTables());
    });

    $("#add-time").on("submit", function (ex) {
        ex.preventDefault();
        var dayId = $(this).find("[name='day']").val();
        var timeValue = $(this).find("[name='time']").val();
        var append = $("#table-content").find('#' + dayId).append(createScheduleTime(timeValue));
        $('input[name="time"]').val('');
        $('#timeModal').modal('toggle');

    });

    $('#confirm-delete').on('show.bs.modal', function (e) {
        var nodeForRemove = $(e.relatedTarget).parent(".schedule-time");
        $(this).find('.btn-ok').on("click", function (e) {
            nodeForRemove.remove();
        });
    });

});

//создаем tables из полученных данных
function createTablesFromRequest(data) {
    var cloneNode = $("#tables-for-clone").clone();

    //заполняем данными из запроса
    $.each(data.detailsMap, function (index, value) {
        var evenOrOdd = index;
        $.each(value, function (index, value) {
            var weekDay = index;
            $.each(value, function (index, value) {
                var idNode = evenOrOdd + '_' + weekDay;
                cloneNode.find('#' + idNode).append(createScheduleTime(value));
            });
        });
    });

    //End each
    return cloneNode.children();
}

//создаем пустые tables
function createEmptyScheduleTables() {
    var clone = $("#tables-for-clone").clone();
    return clone.children();
}

function createScheduleTime(value) {
    var html = '';
    html += '<div class="schedule-time m-1 p-1 text-success border border-info rounded">' +
        '<span>{value}</span>'.replace('{value}', value) +
        '  <a href="#" class="del-time fa fa-trash-o pull-right" aria-hidden="true" data-toggle="modal" data-target="#confirm-delete"/>' +
        '</div>';
    return $.parseHTML(html);
}

//загружаем графики работы
function loadShedule(doctorId) {
    var urlSchedule = '/schedule/doctor/{id}'.replace('{id}', doctorId);
    $.ajax
    ({
        type: 'GET',
        url: urlSchedule,
        dataType: 'json',
        success: function (data) {
            $("#schedule-list").empty().append(createListSheduleFromRequest(data));
            addScheduleLoadEvent();
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
            'href="/schedule/{id}">'.replace('{id}', value.id) +
            'Розклад:{start}'.replace('{start}', value.start) +
            '({notice})</a>'.replace('{notice}', value.notice);
    });
    return $.parseHTML(html);
}

/**
 * після завантаження роскладів вішаємо обробник
 */
function addScheduleLoadEvent() {
    $(".schedule-loaded").on("click", function (event) {
        event.preventDefault();
        var urlSchedule = $(this).attr("href");
        $.ajax
        ({
            type: 'GET',
            url: urlSchedule,
            dataType: 'json',
            success: function (data) {
                $("#table-content").empty().append(createTablesFromRequest(data));
            },
            error: function (xhr, ajaxOptions, throwError) {

            }
        });
    });
}
