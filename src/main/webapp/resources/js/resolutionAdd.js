$(document).ready(function () {

    function parseJSON(data) {
        return window.JSON && window.JSON.parse ? window.JSON.parse(data) : (new Function("return " + data))();
    }

    var unitsNamesJson = $('#unitsNames');
    unitsNames = parseJSON(unitsNamesJson.val());

    var resolutionId = $("#selectResolution").val();


    unitsNames.forEach(function (index, value) {
        if (index.id == resolutionId) {
            $("#unitInput").text(index.name)
        }
    })



    $('#selectResolution').on('change', function (event) {
        var resolutionId = $("#selectResolution").val();

        unitsNames.forEach(function (index, value) {
            if (index.id == resolutionId) {
                $("#unitInput").text(index.name)

            }


        })

    })


})