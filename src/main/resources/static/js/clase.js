$(function () {
  listarCursosClases();
  listarProfesores();
  agregarmodificarClase();
  eliminarClase();
  llenarCampos();
  cambiodeBoton();
  RowClickEvent();
  buscarClase();
});

function RowClickEvent() {
  $('.clicker').click(function () {

    var Curso = $("#cursoClase").val();
    var Asignatura = $("#asignaturaClase").val();
    var Profesor = $("#profesorClase").val();

    if (Curso !== "nn" && Asignatura !== "nn" && Profesor !== "nn") {

      if ($(this).text() == "")
        $(this).text("X");
      else {
        $(this).text("");
      }
    }

  });
}


function guardasHoraSemanal() {
  var horario = [];
  //console.log("SELECTED");
  $("#tabla tbody tr").each(function () {
    $(this).find("td").each(function () {
      if ($(this).text() !== "") {
        var horaindice = $(this).closest('tr').index();
        var hora = $(this).closest('tr').children().first().text();
        var diaindice = $(this).index()
        var dia = $('.head').children().eq(diaindice).text();

        horario.push({
          "dia": dia,
          "hora": hora,
          "diaindice": diaindice,
          "horaindice": horaindice
        });

        //console.log($(this).text(), "Fila:" + horaindice, hora, "Col:" + diaindice, dia);
      }
    });
  });
  console.log(horario);
  return horario;
}


function agregarmodificarClase() {
  $('#botonAceptar').on('click', function (e) {
    e.preventDefault();
    var id = $("input:radio[name=selected]:checked").val()
    var idCurso = $("#cursoClase").val();
    var idAsignatura = $("#asignaturaClase").val();
    var idProfesor = $("#profesorClase").val();
    var horario = guardasHoraSemanal();

    var clase = {
      "idAsignatura": idAsignatura,
      "idProfesor": idProfesor,
      "idCurso": idCurso,
      "horario": horario
    }


    if (idCurso !== "nn" && idAsignatura !== "nn" && idProfesor !== "nn" && horario !== null) {
      var elboton = $('#myModal3').find('.modal-footer button[id=botonAceptar]').text()
      console.log(elboton)
      if (elboton == "Crear") {
        $.ajax("./api/v1/Clases",
          {
            contentType: "application/json",
            dataType: 'json',
            type: "POST",
            data: JSON.stringify(clase),
            success: function (e) {
              console.log(e);
              Toast.fire({
                icon: "success",
                title: "La clase ha sido agregada con exito",
              });
              limpiarTodo();
              $('#myModal3').modal('hide')
            },
            error: function (xhr) {
              Toast.fire({
                icon: "error",
                title: "Error, "+xhr.responseJSON.message 
              });
            }
          });

      } else if (elboton == "Modificar") {
        clase["id"] = id
        $.ajax("./api/v1/Clases/",
          {
            contentType: "application/json",
            dataType: 'json',
            type: "PUT",
            data: JSON.stringify(clase),
            success: function (e) {
              console.log(e);
              Toast.fire({
                icon: "success",
                title: "La clase ha sido modificada con exito",
              });
              limpiarTodo();
              $('#myModal3').modal('hide')
            },
            error: function (xhr) {
              Toast.fire({
                icon: "error",
                title: "Error, "+xhr.responseJSON.message 
              });
            }
          });

      } else {
        Toast.fire({
          icon: "warning",
          title: "Faltan datos por rellenar",
        });
      }
    }
  });
}

function listarCursosClases() {
  $.ajax("./api/v1/Cursos",
    {
      contentType: "application/json",
      dataType: 'json',
      type: "GET",
      success: renderListaCursos
    });
}

function renderListaCursos(data) {
  $('#cursoClase option').remove();
  $('#inputCurso option').remove();
  if (data.length == 0) {
    Toast.fire({
      icon: "warning",
      title: "No hay cursos disponibles"
    });
  } else {
    $('#cursoClase').append('<option value="nn">Selecciona un curso</option>');
    $('#inputCurso').append('<option value="nn">Selecciona un curso</option>');
    $.each(data, function (i, e) {
      $('#cursoClase').append("<option value=" + e.id + ">" + e.nivel + " - " + e.etapa + "</option>");
      $('#inputCurso').append("<option value=" + e.id + ">" + e.nivel + " - " + e.etapa + "</option>");
    });
    $('#cursoClase').focus();
    $('#inputCurso').focus();
  }
}

$('#cursoClase').change(function () {
  listarAsignaturas($(this).val());
});

$('#inputCurso').change(function () {
  listarAsignaturasx($(this).val());
});

function listarAsignaturas(idCurso) {
  $.ajax("./api/v1/Asignaturas/curso/" + idCurso,
    {
      contentType: "application/json",
      dataType: 'json',
      type: "GET",
      success: renderListaAsignaturas,
    });
}

function listarAsignaturasx(idCurso) {
  $.ajax("./api/v1/Asignaturas/curso/" + idCurso,
    {
      contentType: "application/json",
      dataType: 'json',
      type: "GET",
      success: renderListaAsignaturasx,
    });
}

function renderListaAsignaturas(data) {
  $('#asignaturaClase option').remove();
  $('#asignaturaClase').append('<option value="nn">Selecciona una asignatura</option>');
  if (data.length == 0) {
    console.log("vacio")
  } else {

    $.each(data, function (i, e) {
      $('#asignaturaClase').append("<option value=" + e.id + ">" + e.nombre + "</option>");
    });
    $('#asignaturaClase').focus();
  }
}

function renderListaAsignaturasx(data) {
  $('#inputAsignatura option').remove();
  $('#inputAsignatura').append('<option value="nn">Selecciona una asignatura</option>');
  if (data.length == 0) {
    console.log("vacio")
  } else {
    $.each(data, function (i, e) {
      $('#inputAsignatura').append("<option value=" + e.id + ">" + e.nombre + "</option>");
    });
    $('#InputAsignatura').focus();
  }
}

function listarProfesores() {
  $.ajax("./api/v1/Profesores",
    {
      contentType: "application/json",
      dataType: 'json',
      type: "GET",
      success: function (datos) {
        $('#profesorClase').append('<option value="nn">Selecciona un profesor</option>');
        $('#inputProfesor').append('<option value="nn">Selecciona un profesor</option>');
        if (datos.length == 0) {
          Toast.fire({
            icon: "warning",
            title: "No hay profesores disponibles"
          });
        } else {
          $.each(datos, function (i, e) {
            $('#profesorClase').append("<option value=" + e.id + ">" + e.nombre + " " + e.apellido1 + "</option>");
            $('#inputProfesor').append("<option value=" + e.id + ">" + e.nombre + " " + e.apellido1 + "</option>");
          });
        }
      },
      error: function (xhr) {
        Toast.fire({
          icon: "success",
          title: "Error al listar profesores >>> " + xhr.status + " " + xhr.statusText
        });
      }
    });
}

function buscarClase() {
  $('#buscar').on('click', function (ev) {
    ev.preventDefault();
    $(".f").remove();
    var url = "./api/v1/Clases";
    var busqueda = $('.card-body input').val();
    var curso = $("#inputCurso").val();
    console.log(curso);
    /*
    if (busqueda !== "") {
      url = "./api/v1/Clases/name/" + busqueda;
    } else if (curso !== "nn") {
      url = "./api/v1/Clases/curso/" + curso;
    }

    if (busqueda !== "" && curso !== "nn")
      url = "./api/v1/Clases/name/" + busqueda + "/" + curso;
*/
    console.log(url)
    $.ajax(url,
      {
        contentType: "application/json",
        dataType: 'json',
        type: "GET",
        success: function (datos) {
          console.log(datos)
          if (datos.length === 0) {
            Toast.fire({
              icon: "warning",
              title: "No se encontro la busqueda",
            });
          }
          else {
            $.each(datos, function (i, e) {
              var cadena = "";
              for (var i = 0; i < e.horario.length; i++)
                cadena += "[" + e.horario[i].dia + "::" + e.horario[i].hora + "]";

              $('#A-tabla').append("<tr class='f' data-id=" + e.id + ">" +
                "<td>" + "<input value='" + e.id + "' type='radio' class='form-check-input' name='selected'>" + "</td>" +
                "<td>" + e.asignatura.curso.nivel + " de " + e.asignatura.curso.etapa + "</td>" +
                "<td>" + e.asignatura.nombre + "</td>" +
                "<td>" + e.profesor.nombre + " " + e.profesor.apellido1 + "</td>" +
                "<td>" + cadena + "</td>" +
                "</tr>");
            });
          }
        },
        error: function (xhr) {
          Toast.fire({
            icon: "error",
            title: "Error al buscar clases >>> " + xhr.status + " " + xhr.statusText,
          });
        }
      });
  });
}


function redirect() {
  window.location.href = "./clase";
}
function llenarCampos() {
  $('#btnEditar').on('click', function (e) {
    e.preventDefault();
    limpiarTodo();
    var id = $("input:radio[name=selected]:checked").val()
    if (!id) {
      Toast.fire({
        icon: "error",
        title: "Ninguna clase ha sido seleccionada"
      });
      return;
    }

    cambiodeBoton("#btnEditar", "#myModal3")
    $("#myModal3").modal("show");
    $.ajax("./api/v1/Clases/" + id,
      {
        type: "GET",
        success: function (datos) {
          console.log(datos)
         
          $("#cursoClase").val(datos.asignatura.curso.id);
          $("#profesorClase").val(datos.profesor.id);
          
          $("#asignaturaClase").val(datos.asignatura.id);
          
          $("#tabla tbody tr").each(function () {
            $(this).find("td").each(function () {
              var horaindice = $(this).closest('tr').index();
              var diaindice = $(this).index()

              for (var i = 0; i < datos.horario.length; i++)
                if (datos.horario[i].horaindice == horaindice && datos.horario[i].diaindice == diaindice) {
                  $(this).text("X")
                }
            });
          });

        },
        error: function (xhr) {
          $('#myModal3').modal('hide')
          Toast.fire({
            icon: "error",
            title: "Error al traer datos >>> " + xhr.status + " " + xhr.statusText
          });
        }
      });
  });

  $("#btnCrear").on("click", function (e) {
    e.preventDefault();
    limpiarTodo();
    cambiodeBoton("#btnCrear", "#myModal3");
  });
}

function eliminarClase() {
  $('#btnbaja').on('click', function (e) {
    e.preventDefault();
    var id = $("input:radio[name=selected]:checked").val()

    if (!id) {
      Toast.fire({
        icon: "error",
        title: "Ninguna Clase ha sido seleccionado"
      });
      return;
    }

    Swal.fire({
      title: "  ¿Desea eliminar la Clase?",
      text: "No la podras recuperar!",
      icon: "warning",
      showCancelButton: true,
      confirmButtonColor: "#3085d6",
      cancelButtonColor: "#d33",
      confirmButtonText: "Sí, eliminalo!",
      cancelButtonText: "Cancelar",
      showClass: {
        popup: "animated flipInY"
      }
    }).then(result => {
      if (result.value) {

        $.ajax("./api/v1/Clases/" + id, {
          type: "DELETE",
          success: function () {
            Toast.fire({
              icon: "success",
              title: "El Clase ha sido eliminado"
            });
            setTimeout(redirect, 1240);
          },
          error: function (xhr) {
            Toast.fire({
              icon: "success",
              title:
                "Error al eliminar una clase >>> " +
                xhr.status +
                " " +
                xhr.statusText
            });
          }
        });
      }
    });
  });
}


function cambiodeBoton(name, modal) {
  var button = $(name);
  var recipient = button.data("titulo");
  var recipient2 = button.data("boton");
  console.log(recipient, recipient2);
  var modal = $(modal);
  modal.find(".modal-title").text(recipient);
  modal.find(".modal-footer button[id=botonAceptar]").text(recipient2);
}

function limpiarBusqueda() {
  $('#limpiar').on('click', function (event) {
    $('.card-body select').val("nn")
  });
}

function limpiarTodo() {
  $("#cursoClase").val("nn");
  $("#profesorClase").val("nn");
  $("#asignaturaClase").val("nn");

  $("#tabla tbody tr").each(function () {
    $(this).find("td").each(function () {
      $(this).text("");
    });
  });

}
