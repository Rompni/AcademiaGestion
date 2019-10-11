window.onload = function () {

    var chart = new CanvasJS.Chart("chartContainer", {
        exportEnabled: true,
        animationEnabled: true,
        title:{
            text: " Altas y Bajas"
        },
        axisX: {
            title: "Mes"
        },
        axisY: {
            title: "Numero de Altas",
            titleFontColor: "#4F81BC",
            lineColor: "#4F81BC",
            labelFontColor: "#4F81BC",
            tickColor: "#4F81BC"
        },
        axisY2: {
            title: "Numero de Bajas",
            titleFontColor: "#C0504E",
            lineColor: "#C0504E",
            labelFontColor: "#C0504E",
            tickColor: "#C0504E"
        },
        toolTip: {
            shared: true
        },
        legend: {
            cursor: "pointer",
            itemclick: toggleDataSeries
        },
        data: [{
            type: "column",
            name: "Altas",
            showInLegend: true,      
            yValueFormatString: "# Units",
            dataPoints: [
                { label: "05/2016",  y: 3},
                { label: "06/2016", y: 4},
                { label: "07/2016", y: 5 },
                { label: "08/2016",  y: 2}
            ]
        },
        {
            type: "column",
            name: "Bajas",
            axisYType: "secondary",
            showInLegend: true,
            yValueFormatString: "#Units",
            dataPoints: [
                { label: "05/2016", y: 2 },
                { label: "06/2016", y: 1 },
                { label: "07/2016", y: 4 },
                { label: "08/2016", y: 1 }
            ]
        }]
    });
    chart.render();
    
    function toggleDataSeries(e) {
        if (typeof (e.dataSeries.visible) === "undefined" || e.dataSeries.visible) {
            e.dataSeries.visible = false;
        } else {
            e.dataSeries.visible = true;
        }
        e.chart.render();
    }
    
    }