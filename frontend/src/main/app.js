"use strict";

$(function () {

    var endpointUrl = "api/isp";
    var table = $("#index-table");
    var addForm = $("#add-form");
    var viewForm = $("#view-form");

    addForm.validator().on('submit', function (e) {
        if (!e.isDefaultPrevented()) {
            saveCreateForm();
            e.preventDefault();
        }
    });

    $('#add').on('shown.bs.modal', function () {
        $('#edit-companyName').focus()
    });

    $("#index-create").click(clearAddForm);

    renderTableEntries();

    function renderTableEntries() {
        clearTable();
        acquireIsps(addIsps);
    }

    function clearTable() {
        table.children("tbody").html("");
    }

    function acquireIsps(onDone) {
        $.ajax({
            url: endpointUrl,
            type: "GET"
        }).done(onDone).fail(renderError);
    }

    function addIsps(response) {
        if (!response._embedded) {
            return;
        }
        var isps = response._embedded.internetServiceProviders;
        isps.forEach(function (isp) {
            addIsp(isp);
        });
    }

    function addIsp(isp) {
        table.children("tbody:last-child").append(buildRow(isp));
    }

    function clearViewForm() {
        viewForm[0].reset();
    }

    function clearAddForm() {
        addForm[0].reset();
    }

    function saveCreateForm() {
        var isp = {
            companyName: $("#edit-companyName").val(),
            website: $("#edit-website").val(),
            email: $("#edit-email").val()
        };
        $.ajax({
            url: endpointUrl,
            type: "POST",
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(isp),
            dataType: "text"
        }).done(renderTableEntries).fail(renderError);
        $('#add').modal('hide')
    }

    function deleteIsp(link) {
        $.ajax({
            url: link.href,
            type: "DELETE"
        }).done(renderTableEntries).fail(renderError);
    }

    function viewIsp(link) {
        $.ajax({
            url: link.href,
            type: "GET"
        }).done(renderViewForm).fail(renderError);
    }

    function renderViewForm(isp) {
        clearViewForm();
        $("#view-id").val(isp.id);
        $("#view-companyName").val(isp.companyName);
        $("#view-website").val(isp.website);
        $("#view-email").val(isp.email);
    }

    function buildRow(isp) {
        var row = $("<tr />");
        $("<td />", {
            id: "index-id-" + isp.id,
            text: isp.id
        }).appendTo(row);
        $("<td />", {
            html: $("<a />", {
                id: "index-companyName-" + isp.id,
                href: "#",
                "data-toggle": "modal",
                "data-target": "#view",
                click: function () {
                    viewIsp(isp._links.self);
                },
                text: isp.companyName
            })
        }).appendTo(row);
        $("<td />", {
            id: "index-website-" + isp.id,
            html: $("<a />", {
                href: isp.website,
                text: isp.website
            })
        }).appendTo(row);
        $("<td />", {
            id: "index-email-" + isp.id,
            html: $("<a />", {
                href: "mailto:" + isp.email,
                text: isp.email
            })
        }).appendTo(row);
        $("<td />", {
            html: $("<button />", {
                id: "index-remove-" + isp.id,
                class: "btn btn-xs btn-danger",
                click: function () {
                    deleteIsp(isp._links.self);
                },
                html: $("<span />", {
                    class: "glyphicon glyphicon-remove"
                })
            })
        }).appendTo(row);
        return row;
    }

    function renderError(result) {
        console.error("Ajax error occurred", result);
    }
});
