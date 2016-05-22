"use strict";

$(function() {

    var endpointUrl = "/api/isp";

    $("#save").click(function () {
        $.ajax({
            url: endpointUrl,
            type: "GET"
        }).done(renderSuccessUpload).fail(renderError);
    });

    $('#add').on('shown.bs.modal', function () {
        $('#inputCompany').focus()
    });

    function renderSuccessUpload(result) {
        $("#result").text(result);
    }

    function renderError(result) {
        console.error("Ajax error occurred", result);
        $("#result").text("Error occurred. See console logs for details.");
    }
});