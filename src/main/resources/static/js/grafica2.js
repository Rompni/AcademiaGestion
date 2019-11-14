$(function () {
  profesores();
});


function create2(e) {

  var chart2 =  new Chart($("#bar-chart-grouped"), {
    type: 'bar',
    data: {
      labels: ["Total"],
      datasets: [
        {
          label: "Profesores existentes",
          backgroundColor: "rgba(76, 28, 183, 0.5)",
          borderColor: 'rgba(76, 28, 183, 0.9)',
          data: [e.length]
        }
      ]
    },
    options: {
      title: {
        display: true,
        text: 'Profesores'
      }
    }
  });
  chart2.render()
}

function profesores() {
  var url = "./api/v1/Profesores";
  $.ajax(url,
    {
      contentType: "application/json",
      dataType: 'json',
      type: "GET",
      success: create2
    })
}
