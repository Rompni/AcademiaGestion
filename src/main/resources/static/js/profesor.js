$(function(){
  buscarProfesor();
  agregarmodificarProfesor();
  eliminarProfesor();
  llenarCampos();
  cambiodeBoton();
  limpiarBusqueda();
});


function buscarProfesor() {
  $('#btn1').on('click', function(ev) {
  ev.preventDefault();
  $(".f").remove();
  var url = "./api/v1/Profesores";
  var Nombrebusqueda = $('#inputNombreBusqueda').val();
  var Nifbusqueda = $('#inputNifBusqueda').val();
    console.log(Nombrebusqueda+ " " +Nifbusqueda)
  if( Nombrebusqueda != "" || Nifbusqueda != "" ) {
    url = "./api/v1/Profesores/namenif/"+Nombrebusqueda+"-"+Nifbusqueda;
  }

	$.ajax(url,
        {
    		contentType: "application/json",
    		dataType:'json',
    		type: "GET",
    		success:function(datos){
          console.log(datos)
          if(datos.length == 0){ alert("No se encontró la busqueda");}
          else{
    			 $.each(datos, function(i, e) {
                  $('#A-tabla').append("<tr class='f' data-id="+e.id+">" +
                      "<td>" + "<input value='"+e.id + "' type='radio' class='form-check-input alineado' name='selected'>" + "</td>" +
    			            "<td>" + e.nombre +" "+e.apellido1+ "</td>" +
    			            "<td>" + e.nif + "</td>" +
                      "<td>" + e.correo + "</td>" +
                      "<td>" + e.telefono + "</td>" + 			            
                      "</tr>");
                                          });
              }
                                },
        error: function(xhr){alert("Error al buscar profesores >>> " + xhr.status + " " + xhr.statusText);}    		
          }); 
                                        });    
}

function agregarmodificarProfesor() {
  $('#botonAceptar').on('click', function(e) {
    e.preventDefault();
    var id = $("input:radio[name=selected]:checked").val()
    var nombre = $("#inputNombre").val();
    var apellido1 = $("#inputApellido1").val();
    var apellido2 = $("#inputApellido2").val();
    var nif = $("#inputNif").val();
    var telefono = $("#inputTelefono").val();
    var email = $("#inputCorreo").val();
    var titulacion = $("#inputTitulacion").val();

    var profesor = {
    "nombre": nombre,
    "apellido1": apellido1,
    "apellido2": apellido2,
    "nif": nif,
    "telefono":telefono,
    "correo": email,
    "titulacion": titulacion

    }

    console.log(profesor)

    if(nombre && apellido1 && nif && telefono && email && titulacion){
      var elboton = $('#myModal').find('.modal-footer button[id=botonAceptar]').text()
      console.log(elboton)
      if(elboton == "Crear"){
        console.log(profesor)
        $.ajax("./api/v1/Profesores",
          {
            contentType: "application/json",
            //dataType:'json',
            type: "POST",
            data:JSON.stringify(profesor),
            success:function(datos){
              console.log("Se agrego el profesor");
              limpiarTodo();
                              },
            error: function(xhr){alert("Error al insertar un profesor >>> " + xhr.status + " " + xhr.statusText);}
          }); 

    }else if(elboton == "Modificar"){
      profesor["id"] = id
      $.ajax("./api/v1/Profesores",
      {
        contentType: "application/json",
        dataType:'json',
        type: "PUT",
        data: JSON.stringify(profesor),
        success:function(e){
          console.log("Modificado");
              limpiarTodo();
                          },
        error: function(xhr){alert("Error al modificar un profesor >>> " + xhr.status + " " + xhr.statusText);}  		
        });
          
    }else{
      alert("Faltan Datos por rellenar");
    }
    window.location.href = "./profesor"
  }else alert("Faltan Datos por rellenar");
});
}

function eliminarProfesor() {
  $('#btnbaja').on('click', function(e) { 
    e.preventDefault();
    var id = $("input:radio[name=selected]:checked").val()
    if(confirm("¿Desea eliminar este profesor?")){
    $.ajax("./api/v1/Profesores/"+id,{
      type: "DELETE",
      success:function(){console.log("Se eliminó el profesor"); window.location.href = "./profesor" },
      error: function(xhr){alert("Error al eliminar un profesor>>> " + xhr.status + " " + xhr.statusText);}
        });
      }else {
        alert("¡Haz cancelado la eliminacion!");
        }
  });
}

function llenarCampos() {
  $('#btneditar').on('click', function(e) {
      e.preventDefault();
      var id = $("input:radio[name=selected]:checked").val()
      $.ajax( "./api/v1/Profesores/"+id, 
      {
        type: "GET",
        success:function(datos){
          $("#inputNombre").val(datos.nombre);
          $("#inputApellido1").val(datos.apellido1);
          $("#inputApellido2").val(datos.apellido2);
          $("#inputNif").val(datos.nif);
          $("#inputTelefono").val(datos.telefono);
          $("#inputCorreo").val(datos.correo);
          $("#inputTitulacion").val(datos.titulacion);

          },
        error: function(xhr){
          $('#myModal').modal('hide')
          alert("Error al traer datos >>> "+ xhr.status + " " + xhr.statusText);
                              }
      });
                                              });
}

function cambiodeBoton() {
$('#myModal').on('show.bs.modal', function (event) {
    var button = $(event.relatedTarget) // Button that triggered the modal
    var recipient = button.data('titulo') // Extract info from data-* attributes
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

function limpiarTodo() {
  $("input[type=text]").val("");
  $("input[type=number]").val("");
  $("input[type=email]").val("");
}