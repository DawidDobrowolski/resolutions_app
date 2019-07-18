$(document).ready(function () {

    // Load the Visualization API and the corechart package.
    google.charts.load('current', {'packages': ['corechart']});
    google.charts.load('current', {'packages':['gauge']});

// Set a callback to run when the Google Visualization API is loaded.
    google.charts.setOnLoadCallback(drawChart);
    google.charts.setOnLoadCallback(drawChart2);
    google.charts.setOnLoadCallback(drawChart3);

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
            array2.push(index.name);
            array2.push(index.unitsInActions);
            array2.push(index.toGo);
            array.push(array2)
        })

        // Create the data table.
        var data = new google.visualization.DataTable();
        data.addColumn('string', 'Name');
        data.addColumn('number', 'Units done');
        data.addColumn('number', 'Units vs Plan');

        data.addRows(array)


        var options = {
            isStacked: 'percent',
            height: 300,
            legend: {position: 'top', alignment: "center", maxLines: 3},
            animation: {duration: 1000, "startup": true},
            colors: ['#8cb833', '#dd3a40'],
            vAxis: {
                minValue: 0,
                ticks: [0, .25, .5, .75, 1]
            }
        };

        // Instantiate and draw our chart, passing in some options.
        var chart = new google.visualization.ColumnChart(document.getElementById("chart_div"));
        chart.draw(data, options);
    }

//    second chart

    function drawChart2() {

        var data = new google.visualization.DataTable()
        data.addColumn('string', 'Label');
        data.addColumn('number', 'value');


        var array3 = [];
        array3.push('Realization');
        array3.push($('#average').val()-0.00000001);
        data.addRow(array3)



        var options = {
            width: 300, height: 300,
            redFrom: 0, redTo: 20,
            yellowFrom: 20, yellowTo: 50,
            greenFrom: 80, greenTo:100,
            minorTicks: 5,
            animation:{duration: 500, easing: 'inAndOut' },
        };

        var chart = new google.visualization.Gauge(document.getElementById('chart_div2'));


        chart.draw(data, options);


    }

    // third chart

    function drawChart3() {


        var array = [];

        dashboardCharts.forEach(function (index, value) {
            var array2 = [];
            array2.push(index.name);
            array2.push(index.unitsInActions);
            array.push(array2)
        })


        var data = new google.visualization.DataTable()
        data.addColumn('string', 'Label');
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

        var chart = new google.visualization.BarChart(document.getElementById("chart_div3"));
        chart.draw(view, options);
    }


})