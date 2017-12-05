$(document).ready(function () {

    $("#f_search_spec").on("submit",function(e) {
       var url = "/search_spec"; // the script where you handle the form input.
        console.log($("#f_search_spec").serialize());
        $.ajax({
            type: "POST",
            url: url,
            data: $("#f_search_spec").serialize(), // serializes the form's elements.
            success: function(data)
            {
                alert(data); // show response from the php script.
            }
        });
        e.preventDefault(); // avoid to execute the actual submit of the form.
    });

    $("#f_search_name").submit(function(e) {
        var url = "/search_name"; // the script where you handle the form input.
        console.log($("#f_search_name").serialize());
        $.ajax({
            type: "POST",
            url: url,
            data: $("#f_search_name").serialize(), // serializes the form's elements.
            success: function(data)
            {
                alert(data); // show response from the php script.
            }
        });
    e.preventDefault(); // avoid to execute the actual submit of the form.
    });

});