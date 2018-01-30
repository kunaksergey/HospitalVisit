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
            data:formSearch.serialize(), // serializes the form's elements.
            success: function(data)
            {
                handleSearchResult(data); // show response from the php script.
            }
        });
        e.preventDefault(); // avoid to execute the actual submit of the form.
    });

    function handleSearchResult(data){
        var searchDoctorGrid=$("#search-doctor-grid");
        searchDoctorGrid.empty();
        $.each(data, function (index, value) {
               var node=createCardDoctorNode(value);
               searchDoctorGrid.append(node);
        });
    }

    function createCardDoctorNode(doctorObj) {
        var cardDoctor=$("#card-doctor-for-clone").clone().children();
        cardDoctor.find(".card-doctor-name").text(doctorObj.fullName);
        var speciality=cardDoctor.find(".card-doctor-speciality");
        $.each(doctorObj.specializations, function (index, value) {
            speciality.append('<div>{speciality}</div>'.replace('{speciality}',value.name));
        });
        cardDoctor.find(".card-doctor-clinic").text(doctorObj.hospitalName);
        cardDoctor.find(".сard-doctor-cabinet").text(doctorObj.address);
        cardDoctor.find(".doctor-details-link").attr("href","/doctor/{id}".replace("{id}",doctorObj.id));
        cardDoctor.find(".photo-doctor-img").attr("src","/photo/user/{userId}".replace("{userId}",doctorObj.userId));
        return cardDoctor;
    }

});