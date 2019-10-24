new Chart(document.getElementById("doughnut-chart"), {
    type: 'doughnut',
    data: {
      labels: ["Bachillerato", "Primaria", "Secundaria"],
      datasets: [
        {
          label: "Etapas",
          backgroundColor: ["#3e95cd", "#8e5ea2","#3cba9f"],
          borderColor: 'rgb(255, 255, 255)',
          data: [2478,5267,734]
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


