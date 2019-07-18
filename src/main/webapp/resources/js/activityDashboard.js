$(document).ready(function () {

    // Load the Visualization API and the corechart package.
    google.charts.load('current', {'packages': ['corechart']});

// Set a callback to run when the Google Visualization API is loaded.
    google.charts.setOnLoadCallback(drawChart);

// Callback that creates and populates a data table,
// instantiates the pie chart, passes in the data and
// draws it.

    function parseJSON(data) {
        return window.JSON && window.JSON.parse ? window.JSON.parse(data) : (new Function("return " + data))();
    }

    var dashboardChartsJson = $('#dashboardCharts');
    dashboardCharts = parseJSON(dashboardChartsJson.val());


    function drawChart() {

        var array = [];

        dashboardCharts.forEach(function (index, value) {
            var array2 = [];
            array2.push(index.resolution);
            array2.push(index.number);
            array.push(array2)
        })

        // Create the data table.
        var data = new google.visualization.DataTable();
        data.addColumn('string','Resolution type');
        data.addColumn('number','Activities number');

        data.addRows(array)


        var options = {
            isStacked: 'percent',
            is3D: true,
            height: 300,
            legend: {position: 'top',alignment: "center", maxLines: 3},
            animation: {duration: 1000, "startup": true},
            vAxis: {
                minValue: 0,
                ticks: [0, .25, .5, .75, 1]
            }
        };

        // Instantiate and draw our chart, passing in some options.
        var chart = new google.visualization.PieChart(document.getElementById("chart_div"));
        chart.draw(data, options);
    }

    $('#deleteModal').on('show.bs.modal', function (event) {
        var activityId = $(event.relatedTarget).data('activity-id');
        var resolutionName = $(event.relatedTarget).data('resolution-name');
        $(this).find('.modal-body p #resolutionName').text(resolutionName);
        $('#deleteId').on('click', function () {
            window.location.href = "/activity/delete/" + activityId;
        })
    });





})