$(document).ready(function () {
    $("#reg-child-close").on("click", function (event) {
        $("#reg-child-content").css("display", "none");
        event.preventDefault();
    });

    $("#reg-child-open").on("click", function (event) {
        $("#reg-child-content").css("display", "block");
        event.preventDefault();
    });

    $("#f_add-child").on("submit",function(event) {
        var url = "/add_chield"; // the script where you handle the form input.
        console.log($("#f_add-child").serialize());
        // $.ajax({
        //     type: "POST",
        //     url: url,
        //     data: $("#f_search_spec").serialize(), // serializes the form's elements.
        //     success: function(data)
        //     {
        //         alert(data); // show response from the php script.
        //     }
        // });
        event.preventDefault(); // avoid to execute the actual submit of the form.
    });

});

