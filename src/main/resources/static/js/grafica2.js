$(function(){
  chart();
});


function chart(){
new Chart($("#bar-chart-grouped"), {
  type: 'bar',
  data: {
    labels: ["Total"],
    datasets: [
      {
        label: "Altas",
        backgroundColor: "#3e95cd",
        borderColor: 'rgb(255, 255, 255)',
        data: [2]
      }, {
        label: "Bajas",
        backgroundColor: "#8e5ea2",
        borderColor: 'rgb(255, 255, 255)',
        data: [4]
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
}

function listarCursos() {
	$.ajax("./api/v1/Cursos",
        {
    		contentType: "application/json",
    		dataType:'json',
    		type: "GET",
    		success:function(datos){
    			 
                                },
        error: function(xhr){alert("Error al listar cursos >>> " + xhr.status + " " + xhr.statusText);}    		
      }); 
}