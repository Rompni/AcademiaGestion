$(function() {
  buscarCurso();
  llenarCampos();
  agregarmodificarCurso();
  eliminarCurso();
  cambiodeBoton();
  limpiarTodo();
});

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

function buscarCurso() {
  $(".f").remove();
  var url = "./api/v1/Cursos";
	$.ajax(url,
        {
    		contentType: "application/json",
    		dataType:'json',
    		type: "GET",
    		success:function(datos){
          if(datos.length == 0){ alert("No hay cursos");}
          else{
    			 $.each(datos, function(i, e) {
                  $('#A-tabla').append("<tr class='f' data-id="+e.id+">" +
                      "<td>" + "<input value='"+e.id + "' type='radio' class='form-check-input' name='selected'>" + "</td>" +
    			            "<td>" + e.nivel + "</td>" +
    			            "<td>" + e.etapa + "</td>" +			            
                      "</tr>");
                                          });
              }
                                },
        error: function(xhr){alert("Error al buscar cursos >>> " + xhr.status + " " + xhr.statusText);}    		
          }); 
                                            
}

function agregarmodificarCurso(){
    $('#botonAceptar').on('click', function(e) {
      e.preventDefault();
      var id = $("input:radio[name=selected]:checked").val()
      var nivel = $("#inputNivel").val();
      var etapa = $("#inputEtapa").val();
  
      var curso = {
      "nivel": nivel,
      "etapa": etapa,
      }

      if(nivel && etapa){
        var elboton = $('#myModal3').find('.modal-footer button[id=botonAceptar]').text()
        console.log(elboton)
        if(elboton == "Crear"){
          console.log(curso)
          $.ajax("./api/v1/Cursos",
            {
              contentType: "application/json",
              dataType:'json',
              type: "POST",
              data:JSON.stringify(curso),
              success:function(e){
                console.log(e)
                console.log("Se agrego el curso");
                limpiarTodo();
                                },
              error: function(xhr){alert("Error al insertar un curso >>> " + xhr.status + " " + xhr.statusText);}
            }); 
  
      }else if(elboton == "Modificar"){
        curso["id"] = id
        $.ajax("./api/v1/Cursos/",
        {
          contentType: "application/json",
          dataType:'json',
          type: "PUT",
          data: JSON.stringify(curso),
          success:function(){
                console.log("Modificado");
                limpiarTodo();
                            },
          error: function(xhr){alert("Error al modificar un curso >>> " + xhr.status + " " + xhr.statusText);}  		
          });
            
      }else{
        alert("Faltan Datos por rellenar");
      }
    }
   
  });
}

function llenarCampos() {
  $('#btnEditar').on('click', function(e) {
      e.preventDefault();
      var id = $("input:radio[name=selected]:checked").val()
      $.ajax( "./api/v1/Cursos/"+id, 
      {
        type: "GET",
        success:function(datos){
          $("#inputNivel").val(datos.nivel);
          $("#inputEtapa").val(datos.etapa);
          },
        error: function(xhr){
          $('#myModal3').modal('hide')
          alert("Error al traer datos >>> "+ xhr.status + " " + xhr.statusText);
                              }
      });
                                              });
}

function eliminarCurso() {
  $('#btnbaja').on('click', function(e) {
    e.preventDefault(); 
    var id = $("input:radio[name=selected]:checked").val()
    $.ajax("./api/v1/Cursos/"+id,{
      type:"DELETE",
      success:function(){console.log("Se eliminó el curso")},
      error: function(xhr){alert("Error al eliminar un curso >>> " + xhr.status + " " + xhr.statusText);}
    })
    buscarCurso();
  });
}

function limpiarTodo() {
	$("input[type=text]").val("");
  $("input[type=number]").val("");
}