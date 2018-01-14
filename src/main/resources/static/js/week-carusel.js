$(document).ready(function () {
    $(".bt-next-week").click(function () {
        $("#first-week-schedule").toggle("slide", {direction: "left"}, 500);
        $("#second-week-schedule").toggle("slide", {direction: "right"}, 500);
    });
});