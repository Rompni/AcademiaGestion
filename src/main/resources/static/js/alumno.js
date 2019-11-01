$(function() {
  listarCursos();
  buscarAlumno();
  llenarCampos();
  agregarmodificarAlumno();
  eliminarAlumno();
  cambiodeBoton();
  limpiarBusqueda()
});

function listarCursos() {
	$.ajax("./api/v1/Cursos",
        {
    		contentType: "application/json",
    		dataType:'json',
    		type: "GET",
    		success:function(datos){
    			 $.each(datos, function(i, e) {
    			        $('#inputCurso').append("<option data-id="+e.id+">" + e.etapa + "</option>");
                  $('#CursoAlumno').append("<option data-id="+e.id+">" + e.etapa + "</option>");
                                        });
                                },
        error: function(xhr){alert("Error al listar cursos >>> " + xhr.status + " " + xhr.statusText);}    		
      }); 
}

function buscarAlumno() {
  $('#btn1').on('click', function(ev) {
  ev.preventDefault();
  $(".f").remove();
  var url = "./api/v1/Alumnos";
  var busqueda = $('.card-body input').val();

  if( busqueda != ""){
    url = "./api/v1/Alumnos/name/"+busqueda;
  }

	$.ajax(url,
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
                                },
        error: function(xhr){alert("Error al buscar alumnos >>> " + xhr.status + " " + xhr.statusText);}    		
          }); 
                                        });    
}

function agregarmodificarAlumno() {
  $('#botonAceptar').on('click', function(e) {
    e.preventDefault();
    var id = $("input:radio[name=selected]:checked").val()
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
    
    var nombreResponsable = $("#inputNombreResponsable").val();
    var apellido1Responsable = $("#inputApellido1Responsable").val();
    var apellido2Responsable = $("#inputApellido2Responsable").val();
    var nifResponsable = $("#inputNifResponsable").val();
    var telefonoResponsable = $("#inputTelefonoResponsable").val();
    var emailResponsable = $("#inputEmailResponsable").val(); 

    var alumno = {
    "nombre": nombre,
    "apellido1": apellido1,
    "apellido2": apellido2,
    "nif": nif,
    "telefono":telefono,
    "correo": email,
    "fechaalta":fechaalta,
    "fechabaja": fechabaja,
    "observaciones": observaciones
                  }

    var responsable = {"nombre": nombreResponsable,
    "apellido1": apellido1Responsable,
    "apellido2": apellido2Responsable,
    "nif": nifResponsable,
    "telefono":telefonoResponsable,
    "correo": emailResponsable,
    }
    if(nombreResponsable && apellido1Responsable && nifResponsable && telefonoResponsable && emailResponsable && fechaaltaResponsable)
      var respo = agregarResponsable(responsable)

    console.log(respo.success)
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
              console.log("Se agrego el alumno");
              limpiarTodo();
                              },
            error: function(xhr){alert("Error al insertar un alumno >>> " + xhr.status + " " + xhr.statusText);}
          }); 

    }else if(elboton == "Modificar"){
      alumno["id"] = id
      $.ajax("./api/v1/Alumnos/",
      {
        contentType: "application/json",
        dataType:'json',
        type: "PUT",
        data: JSON.stringify(alumno),
        success:function(){
              console.log("Modificado");
              limpiarTodo();
                          },
        error: function(xhr){alert("Error al modificar un alumno >>> " + xhr.status + " " + xhr.statusText);}  		
        });
          
    }else{
      alert("Faltan Datos por rellenar");
    }
  }});
}

function llenarCampos() {
    $('#btneditar').on('click', function(e) {
        e.preventDefault();
        var id = $("input:radio[name=selected]:checked").val()
        $.ajax( "./api/v1/Alumnos/"+id, 
        {
          type: "GET",
          success:function(datos){
            $("#inputNombre").val(datos.nombre);
            $("#inputApellido1").val(datos.apellido1);
            $("#inputApellido2").val(datos.apellido2);
            $("#inputNif").val(datos.nif);
            $("#inputTelefono").val(datos.telefono);
            $("#inputEmail").val(datos.correo);
            $("#inputFechaAlta").val(datos.fechaalta);
            $("#inputFechaBaja").val(datos.fechabaja);
            $("#inputObservaciones").val(datos.observaciones);
            },
          error: function(xhr){
            $('#myModal3').modal('hide')
            alert("Error al traer datos >>> "+ xhr.status + " " + xhr.statusText);
                                }
        });
                                                });
}

function agregarResponsable(responsable) {
  return $.ajax("./api/v1/Responsables",
        		{
        		contentType: "application/json",
        		dataType:'json',
        		type: "POST",
        		data:JSON.stringify(responsable),
            success:function(dato){ 
              console.log("se agrego el responsable");
              return dato;
            },
            error: function(xhr){alert("Error al insertar un responsable >>> " + xhr.status + " " + xhr.statusText);}      		
        });
}

function eliminarAlumno() {
  $('#btnbaja').on('click', function(e) { 
    var id = $("input:radio[name=selected]:checked").val()
    $.ajax("./api/v1/Alumnos/"+id,{
      type: "DELETE",
      success:function(){console.log("Se eliminó el alumno")},
      error: function(xhr){alert("Error al eliminar un alumno >>> " + xhr.status + " " + xhr.statusText);}
    })
  });
}

function limpiarTodo() {
	$("input[type=text]").val("");
  $("input[type=number]").val("");
  $("input[type=email]").val("");
  $("#fechabaja").val("");
  $("#inputObservaciones").val("");
}

function cambiodeBoton() {
$('#myModal3').on('show.bs.modal', function (event) {
    var button = $(event.relatedTarget)// Boton que activa el moda;
    var recipient = button.data('titulo') //se extrae la informacion del atributo data-titulo
    // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
    // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
    var recipient2 = button.data('boton')
    var modal = $(this)
    modal.find('.modal-title').text(recipient)
    modal.find('.modal-footer button[id=botonAceptar]').text(recipient2)
  })
}

function limpiarBusqueda() {
$('#limpiar').on('click', function(event){
    $('.card-body input').val("")
  });
}

window.onload = function() {
    var fecha = new Date(); //Fecha actual
    var mes = fecha.getMonth()+1; //obteniendo mes
    var dia = fecha.getDate(); //obteniendo dia
    var ano = fecha.getFullYear(); //obteniendo año
    if(dia<10)
      dia='0'+dia; //agrega cero si el menor de 10
    if(mes<10)
      mes='0'+mes //agrega cero si el menor de 10
    $('#inputFechaAlta').val(ano+"-"+mes+"-"+dia);  
  }


