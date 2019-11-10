$(function(){
  listarCursos();
  buscarAsignaturas();
  agregarmodificarAsignatura();
  eliminarAsignatura();
  cambiodeBoton();
  llenarCampos();

});

function listarCursos() {
	$.ajax("./api/v1/Cursos",
        {
    		contentType: "application/json",
    		dataType:'json',
    		type: "GET",
    		success:function(datos){
    			 $.each(datos, function(i, e) {        
    			        $('#inputCurso').append("<option value="+e.id+">" + e.nivel +" de "+ e.etapa +"</option>");
                                        });
                                },
        error: function(xhr){
          Toast.fire({
            icon: "warning",
            title: "Error al listar cursos >>> " + xhr.status + " " + xhr.statusText,   
            }); 
          }    		
      }); 
}

function buscarAsignaturas(){
  $(".f").remove();
  var url = "./api/v1/Asignaturas";
	$.ajax(url,
        {
    		contentType: "application/json",
    		dataType:'json',
    		type: "GET",
    		success:function(datos){
          if(datos.length == 0){
            Toast.fire({
              icon: "warning",
              title: "No hay asignaturas ",   
              }); 
            }
          else{
    			 $.each(datos, function(i, e) {
                  $('#A-tabla').append("<tr class='f' data-id="+e.id+">" +
                      "<td>" + "<input value='"+e.id + "' type='radio' class='form-check-input' name='selected'>" + "</td>" +
    			            "<td>" + e.nombre+ "</td>" +
    			            "<td>" + e.curso.nivel + " "+ e.curso.etapa+ "</td>" +			            
                      "</tr>");
                                          });
              }
                                },
        error: function(xhr){
          Toast.fire({
            icon: "warning",
            title: "Error al listar asignaturas >>> " + xhr.status + " " + xhr.statusText,   
            });      
        }    		
    }); 
}

function agregarmodificarAsignatura(){
  $('#botonAceptar').on('click', function(e) {
    e.preventDefault();
    var id = $("input:radio[name=selected]:checked").val()
    var nombre = $("#inputNombre").val();
    var  curso = $("#inputCurso").val();

    var asignatura = {
      "nombre": nombre,
      "curso": null
      }

    BuscarCurso(curso,function(errorLanzado, datosDevueltos){
      if(!errorLanzado){asignatura["curso"] = datosDevueltos;}        
      });
     
    

    if(curso && nombre){
      var elboton = $('#myModal3').find('.modal-footer button[id=botonAceptar]').text()
      if(elboton == "Crear"){
        $.ajax("./api/v1/Asignaturas",
          {
            contentType: "application/json",
            dataType:'json',
            type: "POST",
            data:JSON.stringify(asignatura),
            success:function(){
              Toast.fire({
                icon: "success",
                title: "La asignatura ha sido agregada con exito",   
            });
              $('#myModal3').modal('hide');
              setTimeout(redirect, 1240);
                              },
            error: function(xhr){
              Toast.fire({
                icon: "error",
                title: "Error al insertar una asignatura >>> " + xhr.status + " " + xhr.statusText,   
            });
          }
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
          Toast.fire({
            icon: "success",
            title: "La asignatura ha sido actualizada con exito",   
        });
           $('#myModal3').modal('hide');
            setTimeout(redirect, 1240);
                          },
        error: function(xhr){
          Toast.fire({
            icon: "error",
            title: "Error al modificar una asignatura >>> " + xhr.status + " " + xhr.statusText,   
        });
        }  		
        });
          
    }else{
      Toast.fire({
        icon: "error",
        title: "Faltan datos por rellenar",   
    });
    }
  }
});
}

function redirect(){window.location.href = "./asignatura";}

function BuscarCurso(id, my_callback) {
  $.ajax("./api/v1/Cursos/"+id,
        		{
            async : false,
        		contentType: "application/json",
        		dataType:'json',
        		type: "GET",
            success:function(dato){ 
              my_callback(null, dato);
            },
            error: function(xhr){
              Toast.fire({
                icon: "error",
                title: "Error al buscar el curso >>> " + xhr.status + " " + xhr.statusText,   
                });
              my_callback(xhr);
            }      		
        });
}

function eliminarAsignatura() {  
  $('#btnbaja').on('click', function(e) {
    e.preventDefault(); 
    var id = $("input:radio[name=selected]:checked").val()
    if (!id) {

        Toast.fire({
            icon: 'error',
            title: 'Ninguna asignatura ha sido seleccionado'
        });
        return;
    }

    Swal.fire({
      title: '  ¿Desea eliminar la asignatura?',
      text: "No la podras recuperar!",
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Sí, eliminalo!',
      cancelButtonText: 'Cancelar',
      showClass: {
          popup: 'animated flipInY'
      }
    }).then((result) => {
      if (result.value) {
    $.ajax("./api/v1/Asignaturas/"+id,{
      type:"DELETE",
      success:function(){
        Toast.fire({
          icon: "success",
          title: "La asignatura ha sido eliminada",   
      });

      setTimeout(redirect,1240);
      },
      error: function(xhr){
        Toast.fire({
          icon: "success",
          title: "Error al eliminar una asignatura >>> " + xhr.status + " " + xhr.statusText,   
        });
      }

    });

  } 
  });
});
}

function cambiodeBoton(name, modal) {
  var button = $(name)
  var recipient = button.data('titulo') 
  var recipient2 = button.data('boton')
  console.log(recipient, recipient2);
  var modal = $(modal);
  modal.find('.modal-title').text(recipient)
  modal.find('.modal-footer button[id=botonAceptar]').text(recipient2)
}

function llenarCampos() {
  $('#btnEditar').on('click', function(e) {
      e.preventDefault();
      var id = $("input:radio[name=selected]:checked").val()
      if (!id) {
        Toast.fire({
            icon: 'error',
            title: 'Ninguna asignatura ha sido seleccionada'
        });
        return;
    }

      cambiodeBoton("#btnEditar", "#myModal3");
      $('#myModal3').modal('show');
      $.ajax( "./api/v1/Asignaturas/"+id, 
      {
        type: "GET",
        success:function(datos){
          $("#inputNombre").val(datos.nombre);
          $("#inputCurso").val(datos.curso.id);
          },
        error: function(xhr){
          $('#myModal3').modal('hide')
          Toast.fire({
            icon: "error",
            title: "Error al traer datos >>> " + xhr.status + " " + xhr.statusText,   
        });
                              }
      });
    });

    $('#btnCrear').on('click', function(e){
      e.preventDefault();
      limpiarTodo();
      cambiodeBoton("#btnCrear", "#myModal3");
  });
}

function limpiarTodo() {
	$("input[type=text]").val("");
  $("input[type=number]").val("");
}