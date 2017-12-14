$(document).ready(function () {
    $("#schedule-content").css("display", "none");

    $("#schedule-add").on("click", function (event) {
        event.preventDefault();
        console.log("test");
        $("#schedule-content").css("display", "block");
        $("#schedule-add").css("display", "none");
        $("#sched-time-add").css("display", "block");
        $("#sched-time-content").css("display", "none");

    });

    $("#sched-time-add").on("click", function (event) {
        event.preventDefault();
        $("#sched-time-content").css("display", "block");
        $("#sched-time-add").css("display", "none");
    });


    $(".schedule-open").on("click", function (event) {
        var urlSchedule=$(this).attr("href");
        event.preventDefault();
        $.ajax
        ({
            type: 'GET',
            url:urlSchedule,
            // beforeSend: function (xhr) {
            //
            //     xhr.setRequestHeader('Authorization', make_token($.cookie('coupon-token')));
            // },
            success: function (data) {
                console.log(data);

            },
            error: function (xhr, ajaxOptions, throwError) {
                login_dialog();
            }
        });

        $("#schedule-content").css("display", "block");
        $("#sched-time-add").css("display", "block");
        $("#sched-time-content").css("display", "none");
        $(this).css("display", "none");

    });


    $("#schedule-close").on("click", function (event) {
        $("#schedule-content").css("display", "none");
        $("#schedule-add").css("display", "block");
        $(".schedule-open").css("display", "block");
    });

    $("#sched-time-close").on("click", function (event) {
        event.preventDefault();
        $("#sched-time-content").css("display", "none");
        $("#sched-time-add").css("display", "block");
    });


});