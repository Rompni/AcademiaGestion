$(function(){
  listarCursos();
  buscarAsignaturas();
  agregarmodificarAsignatura();
  eliminarAsignatura();
  cambiodeBoton();

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
        error: function(xhr){alert("Error al listar cursos >>> " + xhr.status + " " + xhr.statusText);}    		
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
          console.log(datos)
          if(datos.length == 0){ console.log("No hay asignaturas");}
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
        error: function(xhr){alert("Error al buscar cursos >>> " + xhr.status + " " + xhr.statusText);}    		
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
              alert("Error al buscar el curso >>> " + xhr.status + " " + xhr.statusText);
              my_callback(xhr);
            }      		
        });
}

function eliminarAsignatura() {  
  $('#btnbaja').on('click', function(e) {
    e.preventDefault(); 
    var id = $("input:radio[name=selected]:checked").val()
    console.log(id)
    $.ajax("./api/v1/Asignaturas/"+id,{
      type:"DELETE",
      success:function(){console.log("Se eliminó la asignatura"); window.location.href = "./asignatura";},
      error: function(xhr){alert("Error al eliminar una Asignatura >>> " + xhr.status + " " + xhr.statusText);}
    })
  });
}

function cambiodeBoton(){
$('#myModal3').on('show.bs.modal', function (event) {
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

function llenarCampos() {
  $('#btnEditar').on('click', function(e) {
      e.preventDefault();
      var id = $("input:radio[name=selected]:checked").val()
      $.ajax( "./api/v1/Asignaturas/"+id, 
      {
        type: "GET",
        success:function(datos){
          $("#inputNombre").val(datos.nombre);
          $("#inputCurso").val(datos.curso);
          },
        error: function(xhr){
          $('#myModal3').modal('hide')
          alert("Error al traer datos >>> "+ xhr.status + " " + xhr.statusText);
                              }
      });
                                              });
}

function limpiarTodo() {
	$("input[type=text]").val("");
  $("input[type=number]").val("");
}