new Chart(document.getElementById("bar-chart-grouped"), {
  type: 'bar',
  data: {
    labels: ["05/19", "06/19", "07/19", "08/19"],
    datasets: [
      {
        label: "Altas",
        backgroundColor: "#3e95cd",
        borderColor: 'rgb(255, 255, 255)',
        data: [133,221,783,2478]
      }, {
        label: "Bajas",
        backgroundColor: "#8e5ea2",
        borderColor: 'rgb(255, 255, 255)',
        data: [408,547,675,734]
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