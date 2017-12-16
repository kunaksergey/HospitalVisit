$(document).ready(function () {
    // $("#schedule-content").css("display", "none");
    //
    $("#schedule-add").on("click", function (event) {
        event.preventDefault();
        $("#table-content").empty().append(createEmptyTables());
    });

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

    $("#add-time").on("submit", function (ex) {
        ex.preventDefault();
        var dayId = $(this).find("[name='day']").val();
        var timeValue = $(this).find("[name='time']").val();
        var append = $("#table-content").find('#' + dayId).append(createScheduleTime(timeValue));
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
function createEmptyTables() {
    var clone = $("#tables-for-clone").clone();
    return clone.children();
}

function createScheduleTime(value) {
    var html = '';
    html += '<div class="schedule-time m-1 p-1 bg-info text-white rounded">' +
        '<span>{value}</span>'.replace('{value}', value) +
        '<a href="#" class="del-time" data-toggle="modal" data-target="#confirm-delete">del</a>' +
        '</div>';
    return $.parseHTML(html);
}



