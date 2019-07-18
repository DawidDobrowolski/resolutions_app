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


    // dashboardCharts.forEach(function (i, value) {
    //     alert(i.name)
    // });


    function drawChart() {


        var array = [];

        dashboardCharts.forEach(function (index, value) {
            var array2 = [];
            array2.push(index.user);
            array2.push(index.sumUnits);
            array.push(array2)
        })


        var data = new google.visualization.DataTable()
        data.addColumn('string', 'user');
        data.addColumn('number', 'value');
        data.addRows(array)

        var view = new google.visualization.DataView(data);


        var options = {
            width: 1000,
            height: 400,
            bar: {groupWidth: "75%"},
            legend: { position: "none" },
            animation: {duration: 2000, "startup": true},
            colors: ['#33D7FF']
        };

        var chart = new google.visualization.BarChart(document.getElementById("chart_div"));
        chart.draw(view, options);
    }


})