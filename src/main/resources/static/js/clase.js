$(function(){
  listarCursosAsignaturas();
  listarProfesores();
  agregarmodificarClase();
  cambiodeBoton();
});

function agregarmodificarClase(){
  $('#botonAceptar').on('click', function(e) {
    e.preventDefault();
    var id = $("input:radio[name=selected]:checked").val()
    var idCurso = $("#cursoClase").val();
    var idAsignatura = $("#asignaturaClase").val();
    var idProfesor = $("#profesorClase").val();
    var hora = $("#horaClase");
    var dia = $("#diaClase").val();

    var clase = {
      "idAsignatura": idAsignatura,
      "idProfesor": idProfesor
      }
/*
    BuscarCurso(curso,function(errorLanzado, datosDevueltos){
      if(!errorLanzado){asignatura["curso"] = datosDevueltos;}        
      }); */
     
    console.log(idCurso, idAsignatura, idProfesor, dia, hora);

    if(curso && nombre){
      var elboton = $('#myModal3').find('.modal-footer button[id=botonAceptar]').text()
      console.log(elboton)
      if(elboton == "Crear"){
        console.log(asignatura)
        $.ajax("./api/v1/Asignaturas",
          {
            contentType: "application/json",
            dataType:'json',
            type: "POST",
            data:JSON.stringify(asignatura),
            success:function(){
              console.log("Se agrego la asignatura");
              limpiarTodo();
                              },
            error: function(xhr){alert("Error al insertar una asignatura >>> " + xhr.status + " " + xhr.statusText);}
          }); 

    }else if(elboton == "Modificar"){
      asignatura["id"] = id
      $.ajax("./api/v1/Asignaturas/",
      {
        contentType: "application/json",
        dataType:'json',
        type: "PUT",
        data: JSON.stringify(asignatura),
        success:function(){
              console.log("Modificado");
              limpiarTodo();
                          },
        error: function(xhr){alert("Error al modificar una asignatura >>> " + xhr.status + " " + xhr.statusText);}  		
        });
          
    }else{
      alert("Faltan Datos por rellenar");
    }
    window.location.href = "./asignatura";
  }
});
}

function listarCursosAsignaturas(){
$.ajax("./api/v1/Cursos",
{
  contentType: "application/json",
  dataType:'json',
  type: "GET",
  success: renderListaCursos
});
}

function renderListaCursos(data){
  $('#cursoClase option').remove();
  if(data.length == 0){
    alert("NO HAY CURSOS")
  } else {
    $('#cursoClase').append('<option value="-1">Selecciona un curso</option>');
   $.each(data, function(i, e) {
      $('#cursoClase').append("<option value="+e.id+">" + e.nivel +" - "+ e.etapa+"</option>");
        });   
    $('#cursoClase').focus();
    }
}

$('#cursoClase').change(function(){
  listarAsignaturas($(this).val());
});

function listarAsignaturas(idCurso){
  $.ajax("./api/v1/Asignaturas/curso/"+idCurso,
  {
  contentType: "application/json",
  dataType:'json',
  type: "GET",
  success: renderListaAsignaturas
  });
}

function renderListaAsignaturas(data){
  $('#asignaturaClase option').remove();
  if(data.length == 0){
    alert("No hay Asignaturas");
  }else{
    $('#asignaturaClase').append('<option value="-1">Selecciona una asignatura</option>');
    $.each(data, function(i, e) {
      $('#asignaturaClase').append("<option value="+e.id+">" + e.nombre + "</option>");
    });
    $('#asignaturaClase').focus();
  }
}

$('#asignaturaClase').change(function(){
  //some
});

function listarProfesores(){
  $.ajax("./api/v1/Profesores",
  {
  contentType: "application/json",
  dataType:'json',
  type: "GET",
  success:function(datos){
    console.log(datos)
      $.each(datos, function(i, e) {
            $('#profesorClase').append("<option value="+e.id+">" + e.nombre + " " + e.apellido1+ "</option>");
                                  });
                          },
  error: function(xhr){alert("Error al listar profesores >>> " + xhr.status + " " + xhr.statusText);}    		
  }); 
  }
  
  
function cambiodeBoton() {
$('#myModal5').on('show.bs.modal', function (event) {
  var button = $(event.relatedTarget) // Button that triggered the modal
  var recipient = button.data('titulo') // Extract info from data-* attributes
  // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
  // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
  var recipient2 = button.data('boton')
  var modal = $(this)
  modal.find('.modal-title').text(recipient)
  modal.find('.modal-footer button[id=botonAceptar]').text(recipient2)
  });
}