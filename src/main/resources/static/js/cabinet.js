$(document).ready(function () {
    var urlGetChield = "/patient/getChield";
    var urlAddChield = "/patient/addChield";
    $.ajax({
        type: "GET",
        url: urlGetChield,
        success: function (data) {
            addTableChield(data); // show response from the php script.
        }
    });


    $("#reg-child-close").on("click", function (event) {
        $("#reg-child-content").css("display", "none");
        event.preventDefault();
    });

    $("#reg-child-open").on("click", function (event) {
        $("#reg-child-content").css("display", "block");
        event.preventDefault();
    });

    $('.datepicker').datepicker({
        format: 'dd-mm-yyyy',
        minDate: -0,
        maxDate: new Date(2017, 12, 10),
        language: 'ua'
    });

    $("#f_add-child").on("submit", function (event) {
        console.log($("#f_add-child").serialize());
        $.ajax({
            type: "POST",
            url: urlAddChield,
            data: $("#f_add-child").serialize(), // serializes the form's elements.
            success: function (data) {
                addTableChield(data); // show response from the php script.
                $("#reg-child-content").css("display", "none");
            }
        });
        event.preventDefault(); // avoid to execute the actual submit of the form.
    });

});

function addTableChield(data) {
    $("#childDetails").html(childToHtml(data));

}

function childToHtml(data) {
    var html = '<table class="table table-bordered"> <thead>' +
        '<tr><th>ПІБ</th><th>День народження</th></tr></thead>' +
        '<tbody>';
    $.each(data, function (index, value) {
        html += '<tr>' +
            '<td>{fullName}</td>'.replace('{fullName}', value.fullName) +
            '<td>{birthDay}</td>'.replace('{birthDay}', formatDate(value.birthDay)) +
            '</tr>';
    });

    html += '</tbody></table>';
    return html;
}

function formatDate(date) {
    return date.split('-').reverse().join('-');
}
