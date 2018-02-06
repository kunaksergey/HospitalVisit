$(document).ready(function () {
    // ticket-grid carusel
    $(".bt-next-ticket-grid").click(function () {
        console.log("tee");
        $("#first-ticket-grid").toggle("slide", {direction: "left"}, 500);
        $("#second-ticket-grid").toggle("slide", {direction: "right"}, 500);
    });
    // !ticket-grid carusel
});