new Chart($("#bar-chart-grouped"), {
  type: 'bar',
  data: {
    labels: ["Total"],
    datasets: [
      {
        label: "Altas",
        backgroundColor: "#3e95cd",
        borderColor: 'rgb(255, 255, 255)',
        data: [133]
      }, {
        label: "Bajas",
        backgroundColor: "#8e5ea2",
        borderColor: 'rgb(255, 255, 255)',
        data: [408]
      }
    ]
  },
  options: {
    title: {
      display: true,
      text: 'Altas y Bajas'
    }
  }
});