$(function () {
  alumnos();
});

function create(e) {

  var primaria = 0;
  var secundaria = 0;

  for (var i = 0; i < e.length; i++) {
    if (e[i].cursoA.etapa == "Primaria")
      primaria += 1;
    else if (e[i].cursoA.etapa == "Secundaria")
      secundaria += 1;
  }


  var chart = new Chart($("#doughnut-chart"), {
    type: 'doughnut',
    animationEnabled: true,
    data: {
      labels: ["Estudiantes de Primaria", "Estudiantes de Secundaria"],
      datasets: [
        {
          label: "Etapas",
          backgroundColor: ["rgba(255, 0, 0, 0.4)", " rgba(0, 0, 255, 0.4)"],
          borderColor:  ['rgba(255, 0, 0, 0.7)',  ' rgba(0, 0, 255, 0.8)'],
          data: [primaria, secundaria]
        }
      ]
    },
    options: {
      title: {
        display: true,
        text: 'ETAPAS',
      }
    }
    
  });

  chart.render()

}


function alumnos() {
  var url = "./api/v1/Alumnos";
  $.ajax(url,
    {
      async:false,
      contentType: "application/json",
      dataType: 'json',
      type: "GET",
      success: create,
      error: function (s) {console.log("error")}
    });
}



