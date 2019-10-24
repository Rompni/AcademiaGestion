$(function() {

  ListarCursos()
  BuscarA()  
  agregarModificarAlumno();
  eliminar();
  CambiodeBoton();
});

function ListarCursos() {
  
	$.ajax("./api/v1/Cursos",
        {
    		contentType: "application/json",
    		dataType:'json',
    		type: "GET",
    		success:function(datos){
    			 $.each(datos, function(i, e) {
    			        $('#inputCurso').append("<option data-id="+e.id+">" + 			            
                  e.etapa +    
                  "</option>");

                  $('#CursoAlumno').append("<option data-id="+e.id+">" + 			            
                  e.etapa +    
                  "</option>");
    			});
    		}    		
      }); 
  }

function BuscarA() {
  $('#btn1').on('click', function(ev) {
  ev.preventDefault();
  $(".f").remove();
	$.ajax("./api/v1/Alumnos",
        {
    		contentType: "application/json",
    		dataType:'json',
    		type: "GET",
    		success:function(datos){
    			 $.each(datos, function(i, e) {
                  $('#A-tabla').append("<tr class='f' data-id="+e.id+">" +
                      "<td>" + "<input value='"+e.id + "' type='radio' class='form-check-input' name='selected'>" + "</td>" +
    			            "<td>" + e.nombre + "</td>" +
    			            "<td>" + e.apellido1 + "</td>" +
                      "<td>" +  "</td>" +
                      "<td>" +  "</td>" +
                      "<td>" + e.fechaalta + "</td>" +
                      "<td>" +  "</td>" + 			            
                      "</tr>");
                    });
                  }    		
                }); 
    });    
  }

function agregarModificarAlumno() {
    $('#botonAceptar').on('click', function(e) {
        e.preventDefault();
        var nombre = $("#inputNombre").val();
        var apellido1 = $("#inputApellido1").val();
        var apellido2 = $("#inputApellido2").val();
        var nif = $("#inputNif").val();
        var telefono = $("#inputTelefono").val();
        var email = $("#inputEmail").val();
        var fechaalta = $("#inputFechaAlta").val();
        var fechabaja = $("#inputFechaBaja").val();
        var observaciones = $("#inputObservaciones").val();
      console.log(nombre, apellido1, nif, telefono, email, fechaalta)
      /*
        var nombreResponsable = $("#inputNombreResponsable").val();
        var apellido1Responsable = $("#inputApellido1Responsable").val();
        var apellido2Responsable = $("#inputApellido2Responsable").val();
        var nifResponsable = $("#inputNifResponsable").val();
        var telefonoResponsable = $("#inputTelefonoResponsable").val();
        var emailResponsable = $("#inputEmailResponsable").val(); */

        var alumno = {"nombre": nombre,
        "apellido1": apellido1,
        "apellido2": apellido2,
        "nif": nif,
        "telefono":telefono,
        "correo": email,
        "fechaalta":fechaalta,
        "fechabaja": fechabaja,
        "observaciones": observaciones
        }
/*
        var responsable = {"nombre": nombreResponsable,
        "apellido1": apellido1Responsable,
        "apellido2": apellido2Responsable,
        "nif": nifResponsable,
        "telefono":telefonoResponsable,
        "correo": emailResponsable,
        }
        if(nombreResponsable && apellido1Responsable && nifResponsable && telefonoResponsable && emailResponsable && fechaaltaResponsable)
        agregarResponsable(responsable)*/


      if(nombre && apellido1 && nif && telefono && email && fechaalta){
    	 var elboton = $('#myModal3').find('.modal-footer button[id=botonAceptar]').text()
    	  console.log(elboton)
    	if(elboton == "Crear"){  
        $.ajax("./api/v1/Alumnos",
        		{
        		contentType: "application/json",
        		dataType:'json',
        		type: "POST",
        		data:JSON.stringify(alumno),
        		success:function(){
              console.log("Se agrego el alumno")
              LimpiarTodo()
            }     		
        }); 
        
    	}else if(elboton == "Modificar"){
            $.put("./api/v1/Alumnos/",JSON.stringify(alumno),
                    function(){
                    console.log("Modificado")
                    LimpiarTodo()
                  }, "application/json")     		
              }
    		
      }else{
        alert("Faltan Datos por rellenar");
      }
    });
}

function agregarResponsable(responsable){
  $.ajax("./api/v1/Responsables",
        		{
        		contentType: "application/json",
        		dataType:'json',
        		type: "POST",
        		data:JSON.stringify(responsable),
            success:function(){ console.log("se agrego el responsable")},
            error: function(xhr){alert("An error occured: " + xhr.status + " " + xhr.statusText)
          }      		
        });

}




function eliminar() {
  $('#btnbaja').on('click', function(e) { 
    var selectedOption = $("input:radio[name=selected]:checked").val()
    $.delete("./api/v1/Alumnos/"+selectedOption,{},function(e){
    })
  });
}


//POP UPS
function CambiodeBoton(){
$('#myModal3').on('show.bs.modal', function (event) {
    var button = $(event.relatedTarget)// Button that triggered the modal
    var recipient = button.data('titulo') // Extract info from data-* attributes
    // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
    // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
    var recipient2 = button.data('boton')
    var modal = $(this)
    modal.find('.modal-title').text(recipient)
    modal.find('.modal-footer button[id=botonAceptar]').text(recipient2)
  })
}

$('#limpiar').on('click', function(event){
    $('.card-body input').val("")
  })

window.onload = function(){
    var fecha = new Date(); //Fecha actual
    var mes = fecha.getMonth()+1; //obteniendo mes
    var dia = fecha.getDate(); //obteniendo dia
    var ano = fecha.getFullYear(); //obteniendo año
    if(dia<10)
      dia='0'+dia; //agrega cero si el menor de 10
    if(mes<10)
      mes='0'+mes //agrega cero si el menor de 10
    document.getElementById('inputFechaAlta').value=ano+"-"+mes+"-"+dia;
  }

function LimpiarTodo(){
	$("input[type=val]").val("");
    $("input[type=number]").val("");
    $("input[type=email]").val("");
    $("#fechabaja").val("");
    $("#inputObservaciones").val("");
}
