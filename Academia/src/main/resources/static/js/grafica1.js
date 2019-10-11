
window.onload = function () {

    var options = {
        title: {
            text: "Desktop OS Market Share in 2017"
        },
        subtitles: [{
            text: "As of November, 2017"
        }],
        animationEnabled: true,
        data: [{
            type: "pie",
            startAngle: 40,
            toolTipContent: "<b>{label}</b>: {y}%",
            showInLegend: "true",
            legendText: "{label}",
            indexLabelFontSize: 16,
            indexLabel: "{label} - {y}%",
            dataPoints: [
                { y: 48.36, label: "Windows 7" },
                { y: 26.85, label: "Windows 10" },
                { y: 1.49, label: "Windows 8" },
                { y: 6.98, label: "Windows XP" },
                { y: 6.53, label: "Windows 8.1" },
                { y: 2.45, label: "Linux" },
                { y: 3.32, label: "Mac OS X 10.12" },
                { y: 4.03, label: "Others" }
            ]
        }]
    };
    $("#chartContainer").CanvasJSChart(options);
    
    }