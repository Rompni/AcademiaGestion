$(function () {
  listarCursosClases();
  listarProfesores();
  agregarmodificarClase();
  eliminarClase();
  llenarCampos();
  cambiodeBoton();
  RowClickEvent();
  buscarClase();
  buscarAlumnos();
  limpiarBusqueda();
});

function RowClickEvent() {
  $('.clicker').click(function () {

    var Curso = $("#cursoClase").val();
    var Asignatura = $("#asignaturaClase").val();
    var Profesor = $("#profesorClase").val();
    var elboton = $('#myModal3').find('.modal-footer button[id=botonAceptar]').text()

    if ( (Curso !== "nn" && Asignatura !== "nn" && Profesor !== "nn") || elboton == "Modificar" ) {

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

    var elboton = $('#myModal3').find('.modal-footer button[id=botonAceptar]').text()
    
    if (idCurso !== "nn" && idAsignatura !== "nn" && idProfesor !== "nn" && horario.length > 0) {

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
                title: "Error, " + xhr.responseJSON.message
              });
            }
          });
        }

      } else if (horario.length > 0) {
        if (elboton == "Modificar") {
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
                title: "Error, " + xhr.responseJSON.message
              });
            }
          });
      }
    } else {
      Toast.fire({
        icon: "warning",
        title: "Faltan datos por rellenar",
      });
    }
  });
}

function listarCursosClases() {
  $.ajax("./api/v1/Cursos",
    {
      async: false,
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
      async: false,
      contentType: "application/json",
      dataType: 'json',
      type: "GET",
      success: renderListaAsignaturas,
    });
}

function listarAsignaturasx(idCurso) {
  $.ajax("./api/v1/Asignaturas/curso/" + idCurso,
    {
      async: false,
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
          title: "Error, " + xhr.responseJSON.message
        });
      }
    });
}

function buscarClase() {
  $('#buscar').on('click', function (ev) {
    ev.preventDefault();
    $(".f").remove();
    var url = "./api/v1/Clases";
    var asignatura = $("#inputAsignatura").val();
    var curso = $("#inputCurso").val();
    var profesor = $("#inputProfesor").val();
    console.log(curso);

    if (curso == null) {
      Toast.fire({
        icon: "error",
        title: "No hay cursos para buscar"
      });
      return;
    }

    if (curso !== "nn") {
      url = "./api/v1/Clases/curso/" + curso;
      if (asignatura !== "nn")
        url = "./api/v1/Clases/asig/" + asignatura;
      else if (profesor !== "nn")
        url = "./api/v1/Clases/cursoprofe/" + curso + "/" + profesor;

    } else if (profesor !== "nn")
      url = "./api/v1/Clases/profe/" + profesor;

    if (curso !== "nn" && asignatura !== "nn" && profesor !== "nn")
      url = "./api/v1/Clases/CsAsPr/" + curso + "/" + asignatura + "/" + profesor;

    

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
            title: "Error, " + xhr.responseJSON.message
          });
        }
      });
  });
}

function buscarAlumnos() {
  $('#btnAlumnos').on('click', function (ev) {
    ev.preventDefault();
    $(".z").remove();
    var id = $("input:radio[name=selected]:checked").val()
    if (!id) {
      Toast.fire({
        icon: "error",
        title: "Ninguna Clase ha sido seleccionado"
      });
      return;
    }
    var url = "./api/v1/Clases/Alumno/" + id;
    console.log(url)
    $.ajax(url,
      {
        contentType: "application/json",
        dataType: 'json',
        type: "GET",
        success: function (datos) {
          var apellidos2 = "";
          if (datos.length === 0) {
            Toast.fire({
              icon: "warning",
              title: "No hay estudiantes en esta clase",
            });
          }
          else {
            $("#myModal6").modal("show");
            $.each(datos, function (i, e) {
              if(e.apellido2 != null) apellidos2 = e.apellido2
              $('#tabla2').append("<tr class='z'>"+
                "<td>" + e.nombre +" "+ e.apellido1 +" "+apellidos2 +". </td>" +
                "</tr>");
            });
          }
        },
        error: function (xhr) {
          Toast.fire({
            icon: "error",
            title: "Error, " + xhr.responseJSON.message
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
    var id = $("input:radio[name=selected]:checked").val()
    if (!id) {
      Toast.fire({
        icon: "error",
        title: "Ninguna clase ha sido seleccionada"
      });
      return;
    }

    limpiarTodo();
    $(".create").attr("hidden", true);
    $(".edit").attr("hidden", false);
    cambiodeBoton("#btnEditar", "#myModal3")
    $("#myModal3").modal("show");
    $.ajax("./api/v1/Clases/" + id,
      {
        type: "GET",
        success: function (datos) {

         var curso = $("input:radio[name=selected]:checked").closest('tr').children()[1].textContent
         var asignatura = $("input:radio[name=selected]:checked").closest('tr').children()[2].textContent
         var profesor = $("input:radio[name=selected]:checked").closest('tr').children()[3].textContent

        $("#verCurso").text(curso)
        $("#verAsignatura").text(asignatura)
        $("#verProfesor").text(profesor)

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
            title: "Error, " + xhr.responseJSON.message
          });
        }
      });
  });

  $("#btnCrear").on("click", function (e) {
    e.preventDefault();
    limpiarTodo();
    $(".create").attr("hidden", false);
    $(".edit").attr("hidden", true);
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
              title: "Error, " + xhr.responseJSON.message
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
    limpiarTodo();
  });
}

function limpiarTodo() {
  $("#cursoClase").val("nn");
  $("#profesorClase").val("nn");
  $("#asignaturaClase option").remove();

  $("#inputCurso").val("nn");
  $("#inputProfesor").val("nn");
  $("#inputAsignatura option").remove();



  $("#tabla tbody tr").each(function () {
    $(this).find("td").each(function () {
      $(this).text("");
    });
  });

}
