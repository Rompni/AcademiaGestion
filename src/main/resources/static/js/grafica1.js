new Chart(document.getElementById("doughnut-chart"), {
    type: 'doughnut',
    data: {
      labels: ["Primaria", "Secundaria"],
      datasets: [
        {
          label: "Etapas",
          backgroundColor: ["#3e95cd", "#8e5ea2"],
          borderColor: 'rgb(255, 255, 255)',
          data: [10,10]
        }
      ]
    },
    options: {
      title: {
        display: true,
        text: 'Etapas'
      }
    }
});




