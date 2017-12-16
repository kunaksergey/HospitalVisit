$(document).ready(function () {
    $('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
        var target = $(e.target).attr("href"); // activated tab
        var formSearch=$("#f_search");
        var searchStr=$("#searchStr");
        if(target=='#tab_search_spec'){
            formSearch.attr('action','/search/spec');
            searchStr.val('');
            formSearch.find("input[name='searchStr']").attr("placeholder","Спеціалізація");
        } else if(target=='#tab_search_name'){
            formSearch.attr('action','/search/name');
            searchStr.val('');
            formSearch.find("input[name='searchStr']").attr("placeholder","ПІБ");
        }

    });

    $("#f_search").on("submit",function(e) {
         var formSearch=$("#f_search");
         var url = formSearch.attr('action'); // the script where you handle the form input.
        $.ajax({
            type: "POST",
            url: url,
            data: $("#f_search").serialize(), // serializes the form's elements.
            success: function(data)
            {
                console.log(data); // show response from the php script.
            }
        });
        e.preventDefault(); // avoid to execute the actual submit of the form.
    });



});