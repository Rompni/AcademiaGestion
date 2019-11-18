$(function () {
  buscarProfesor();
  agregarmodificarProfesor();
  mostrarClases();
  eliminarProfesor();
  llenarCampos();
  limpiarBusqueda();
});


function buscarProfesor() {
  $('#btn1').on('click', function (ev) {
    ev.preventDefault();
    $(".f").remove();
    var url = "./api/v1/Profesores";
    var Nombrebusqueda = $('#inputNombreBusqueda').val();
    var Nifbusqueda = $('#inputNifBusqueda').val();
    console.log(Nombrebusqueda + " " + Nifbusqueda)
    if (Nombrebusqueda != "" || Nifbusqueda != "") {
      url = "./api/v1/Profesores/namenif/" + Nombrebusqueda + "-" + Nifbusqueda;
    }

    $.ajax(url,
      {
        contentType: "application/json",
        dataType: 'json',
        type: "GET",
        success: function (datos) {
          console.log(datos)
          if (datos.length == 0) {
            Toast.fire({
              icon: "warning",
              title: "No se encontro la busqueda",
            });
          }
          else {
            $.each(datos, function (i, e) {
              $('#A-tabla').append("<tr class='f' data-id=" + e.id + ">" +
                "<td>" + "<input value='" + e.id + "' type='radio' class='form-check-input alineado' name='selected'>" + "</td>" +
                "<td>" + e.nombre + " " + e.apellido1 + "</td>" +
                "<td>" + e.nif + "</td>" +
                "<td>" + e.correo + "</td>" +
                "<td>" + e.telefono + "</td>" +
                "</tr>");
            });
          }
        },
        error: function (xhr) {
          Toast.fire({
            icon: "error",
            title: "Error al buscar profesores >>> " + xhr.status + " " + xhr.statusText,
          });
        }
      });
  });
}

function agregarmodificarProfesor() {
  $('#botonAceptar').on('click', function (e) {
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
      "telefono": telefono,
      "correo": email,
      "titulacion": titulacion

    }

    if (nombre && apellido1 && nif && telefono && email && titulacion) {
      var clicked = $('#myModal').find('.modal-footer button[id=botonAceptar]').text()
      if (clicked == "Crear") {
        $.ajax("./api/v1/Profesores",
          {
            contentType: "application/json",
            type: "POST",
            data: JSON.stringify(profesor),
            success: function (datos) {
              Toast.fire({
                icon: "success",
                title: "El Profesor ha sido agregado con exito",
              });
              $("#myModal").modal("hide");
              limpiarTodo();
            },
            error: function (xhr) {
              Toast.fire({
                icon: "error",
                title: "Error, " + xhr.responseJSON.message
              });
            }
          });

      } else if (clicked == "Modificar") {
        profesor["id"] = id
        $.ajax("./api/v1/Profesores",
          {
            contentType: "application/json",
            dataType: 'json',
            type: "PUT",
            data: JSON.stringify(profesor),
            success: function (e) {
              Toast.fire({
                icon: "success",
                title: "El profesor ha sido modificado con exito",
              });
              $("#myModal").modal("hide");
              limpiarTodo();
              setTimeout(redirect, 1240);
            },
            error: function (xhr) {
              Toast.fire({
                icon: "error",
                title: "Error, " + xhr.responseJSON.message,
                timer: 2000
              });
            }
          });

      } else {
        Toast.fire({
          icon: "warning",
          title: "Faltan Datos por rellenar",
        });
      }
    } else Toast.fire({
      icon: "warning",
      title: "Faltan Datos por rellenar",
    });
  });
}

function eliminarProfesor() {
  $('#btnbaja').on('click', function (e) {
    e.preventDefault();
    var id = $("input:radio[name=selected]:checked").val()

    if (!id) {
      Toast.fire({
        icon: "error",
        title: "Ningun profesor ha sido seleccionado"
      });
      return;
    }

    Swal.fire({
      title: "  ¿Desea eliminar el profesor?",
      text: "No lo podras recuperar!",
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

        $.ajax("./api/v1/Profesores/" + id, {
          type: "DELETE",
          success: function () {
            Toast.fire({
              icon: "success",
              title: "El profesor ha sido eliminado"
            });
            setTimeout(redirect, 1240);

          },
          error: function (xhr) {
            Toast.fire({
              icon: "success",
              title: "Error; " + xhr.responseJSON.message
            });
          }
        });
      }
    });
  });
}

function redirect() {
  window.location.href = "./profesor";
}

function llenarCampos() {
  $('#btnEditar').on('click', function (e) {
    e.preventDefault();
    var id = $("input:radio[name=selected]:checked").val();
    if (!id) {
      Toast.fire({
        icon: "error",
        title: "Ningun profesor ha sido seleccionado"
      });
      return;
    }

    cambiodeBoton("#btnEditar", "#myModal")
    $("#myModal").modal("show");
    $.ajax("./api/v1/Profesores/" + id,
      {
        type: "GET",
        success: function (datos) {
          $("#inputNombre").val(datos.nombre);
          $("#inputApellido1").val(datos.apellido1);
          $("#inputApellido2").val(datos.apellido2);
          $("#inputNif").val(datos.nif);
          $("#inputTelefono").val(datos.telefono);
          $("#inputCorreo").val(datos.correo);
          $("#inputTitulacion").val(datos.titulacion);

        },
        error: function (xhr) {
          $("#myModal").modal("hide");
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
    cambiodeBoton("#btnCrear", "#myModal");
  });

}

function mostrarClases() {
  $('#btnclases').on('click', function (e) {
    e.preventDefault();
    var id = $("input:radio[name=selected]:checked").val();
    if (!id) {
      Toast.fire({
        icon: "error",
        title: "Ningun profesor ha sido seleccionado"
      });
      return;
    }
    limpiarTodo();
    $.ajax("./api/v1/Profesores/clases/" + id,
      {
        type: "GET",
        success: function (datos) {

          if (datos.length == 0) {
            Toast.fire({
              icon: "warning",
              title: "Este profesor no tiene clases estipuladas"
            });
          } else {
            $("#myModal2").modal("show");
            $("#tabla tbody tr").each(function () {
              $(this).find("td").each(function () {
                var horaindice = $(this).closest('tr').index();
                var diaindice = $(this).index()
                for (var i = 0; i < datos.length; i++)
                  for (var j = 0; j < datos[i].horario.length; j++)
                    if (datos[i].horario[j].horaindice == horaindice && datos[i].horario[j].diaindice == diaindice) {
                      $(this).text(datos[i].asignatura.curso.nivel + " de " + datos[i].asignatura.curso.etapa + "-" + datos[i].asignatura.nombre)
                    }
              });
            });
          }
        },
        error: function (xhr) {
          $("#myModal").modal("hide");
          Toast.fire({
            icon: "error",
            title: "Error al traer datos >>> " + xhr.status + " " + xhr.statusText
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
  $('#limpiar').on('click', function () {
    $('.card-body input').val("")
  });


  $('#regresar').on('click', function () {
    $("#tabla tbody tr").each(function () {
      $(this).find("td").each(function () {
        $(this).text("");
      });
    });
  });
}

function limpiarTodo() {
  $("input[type=text]").val("");
  $("input[type=number]").val("");
  $("input[type=email]").val("");

  $("#tabla tbody tr").each(function () {
    $(this).find("td").each(function () {
      $(this).text("");
    });
  });
}