$(function () {
  listarCursos();
  buscarAlumno();
  llenarCampos();
  agregarmodificarAlumno();
  eliminarAlumno();
  limpiarBusqueda();
});

function listarCursos() {
  $.ajax("./api/v1/Cursos",
    {
      contentType: "application/json",
      dataType: 'json',
      type: "GET",
      success: function (datos) {
        $.each(datos, function (i, e) {
          $('#inputCurso').append("<option value=" + e.id + ">" + e.nivel + " - " + e.etapa + "</option>");
          $('#CursoAlumno').append("<option value=" + e.id + ">" + e.nivel + " - " + e.etapa + "</option>");
        });
      },
      error: function (xhr) {
        Toast.fire({
          icon: "error",
          title: "Error al listar cursos >>> " + xhr.status + " " + xhr.statusText,
        });
      }
    });
}

function buscarAlumno() {
  $('#btn1').on('click', function (ev) {
    ev.preventDefault();
    $(".f").remove();
    var url = "./api/v1/Alumnos";
    var busqueda = $('.card-body input').val();
    var curso = $("#inputCurso").val();
    console.log(curso);
    if (busqueda !== "") {
      url = "./api/v1/Alumnos/name/" + busqueda;
    } else if (curso !== "nn") {
      url = "./api/v1/Alumnos/curso/" + curso;
    }

    if (busqueda !== "" && curso !== "nn")
    url = "./api/v1/Alumnos/name/"+busqueda+"/"+curso;

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
                var name;
                if (!e.responsable) name = 'NO APLICA'; else name = e.responsable.nombre;
                $('#A-tabla').append("<tr class='f' data-id=" + e.id + ">" +
                  "<td>" + "<input value='" + e.id + "' type='radio' class='form-check-input' name='selected'>" + "</td>" +
                  "<td>" + e.nombre + "</td>" +
                  "<td>" + e.apellido1 + "</td>" +
                  "<td>" + e.cursoA.nivel + " de " + e.cursoA.etapa + "</td>" +
                  "<td>" + name + "</td>" +
                  "<td>" + e.fechaalta + "</td>" +
                  "<td>" + "</td>" +
                  "</tr>");
              });
            }
          },
          error: function (xhr) {
            Toast.fire({
              icon: "error",
              title: "Error al buscar alumnos >>> " + xhr.status + " " + xhr.statusText,
            });
          }
        });
  });
}

function agregarmodificarAlumno() {
  $('#botonAceptar').on('click', function (e) {
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
    var curso = $("#CursoAlumno").val();
    var repetidor = $('.checking').prop('checked', function (i, value) { return value; });
    repetidor = repetidor[0].checked

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
      "telefono": telefono,
      "correo": email,
      "fechaalta": fechaalta,
      "fechabaja": fechabaja,
      "observaciones": observaciones,
      "repetidor": repetidor,
      "stringaux": curso
    }

    var responsable = {
      "nombre": nombreResponsable,
      "apellido1": apellido1Responsable,
      "apellido2": apellido2Responsable,
      "nif": nifResponsable,
      "telefono": telefonoResponsable,
      "correo": emailResponsable,
      "alumnosrespon": []
    }

    if (nombreResponsable && apellido1Responsable && nifResponsable && telefonoResponsable && emailResponsable) {
      agregarResponsable(responsable, function (errorLanzado, datosDevueltos) {
        if (!errorLanzado) {
          alumno["responsable"] = datosDevueltos;
        }
      });
    }
    if (nombre && apellido1 && nif && telefono && email && fechaalta) {
      var clicked = $('#myModal3').find('.modal-footer button[id=botonAceptar]').text()
      if (clicked == "Crear") {
        $.ajax("./api/v1/Alumnos",
          {
            contentType: "application/json",
            type: "POST",
            data: JSON.stringify(alumno),
            success: function (datos) {
              Toast.fire({
                icon: "success",
                title: "El alumno ha sido agregado con exito",
              });
              $("#myModal3").modal("hide");
              limpiarTodo();
            },
            error: function (xhr) {
              Toast.fire({
                icon: "error",
                title: "Error al insertar un estudiante >>> " + xhr.status + " " + xhr.statusText,
              });
            }
          });

      } else if (clicked == "Modificar") {
        alumno["id"] = id
        $.ajax("./api/v1/Alumnos/",
          {
            contentType: "application/json",
            dataType: 'json',
            type: "PUT",
            data: JSON.stringify(alumno),
            success: function (e) {
              Toast.fire({
                icon: "success",
                title: "El alumno ha sido modificado con exito",
              });

              $("#myModal3").modal("hide");
              limpiarTodo();
              setTimeout(redirect, 1240);
            },
            error: function (xhr) {
              Toast.fire({
                icon: "error",
                title: "Error al modificar un estudiante >>> " + xhr.status + " " + xhr.statusText,
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

function redirect() {
  window.location.href = "./alumno";
}

function llenarCampos() {
  $('#btnEditar').on('click', function (e) {
    e.preventDefault();
    var id = $("input:radio[name=selected]:checked").val()
    if (!id) {
      Toast.fire({
        icon: "error",
        title: "Ningun alumno ha sido seleccionado"
      });
      return;
    }

    cambiodeBoton("#btnEditar", "#myModal3")
    $("#myModal3").modal("show");
    $.ajax("./api/v1/Alumnos/" + id,
      {
        type: "GET",
        success: function (datos) {
          $("#inputNombre").val(datos.nombre);
          $("#inputApellido1").val(datos.apellido1);
          $("#inputApellido2").val(datos.apellido2);
          $("#inputNif").val(datos.nif);
          $("#inputTelefono").val(datos.telefono);
          $("#inputEmail").val(datos.correo);
          $("#inputFechaAlta").val(datos.fechaalta);
          $("#inputFechaBaja").val(datos.fechabaja);
          $("#inputObservaciones").val(datos.observaciones);

          if (datos.responsable) {
            $("#inputNombreResponsable").val(datos.responsable.nombre);
            $("#inputApellido1Responsable").val(datos.responsable.apellido1);
            $("#inputApellido2Responsable").val(datos.responsable.apellido2);
            $("#inputNifResponsable").val(datos.responsable.nif);
            $("#inputTelefonoResponsable").val(datos.responsable.telefono);
            $("#inputEmailResponsable").val(datos.responsable.correo);
          }

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

function agregarResponsable(responsable, my_callback) {
  $.ajax("./api/v1/Responsables",
    {
      async: false,
      contentType: "application/json",
      dataType: 'json',
      type: "POST",
      data: JSON.stringify(responsable),
      success: function (dato) {
        Toast.fire({
          icon: "success",
          title: "El responsable ha sido agregado con exito",
        });
        my_callback(null, dato);
      },
      error: function (xhr) {
        Toast.fire({
          icon: "error",
          title: "Error al insertar un responsable >>> " + xhr.status + " " + xhr.statusText,
        });
        my_callback(xrh);
      }
    });
}

function eliminarAlumno() {
  $('#btnbaja').on('click', function (e) {
    e.preventDefault();
    var id = $("input:radio[name=selected]:checked").val()

    if (!id) {
      Toast.fire({
        icon: "error",
        title: "Ningun alumno ha sido seleccionado"
      });
      return;
    }

    Swal.fire({
      title: "  ¿Desea eliminar el alumno?",
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


        $.ajax("./api/v1/Alumnos/" + id, {
          type: "DELETE",
          success: function () {
            Toast.fire({
              icon: "success",
              title: "El alumno ha sido eliminado"
            });
            setTimeout(redirect, 1240);
          },
          error: function (xhr) {
            Toast.fire({
              icon: "success",
              title:
                "Error al eliminar un profesor >>> " +
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

function limpiarTodo() {
  $("input[type=text]").val("");
  $("input[type=number]").val("");
  $("input[type=email]").val("");
  $("#fechabaja").val("");
  $("#inputObservaciones").val("");
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
    $('.card-body input').val("")
  });
}

window.onload = function () {
  var fecha = new Date(); //Fecha actual
  var mes = fecha.getMonth() + 1; //obteniendo mes
  var dia = fecha.getDate(); //obteniendo dia
  var ano = fecha.getFullYear(); //obteniendo año
  if (dia < 10)
    dia = '0' + dia; //agrega cero si el menor de 10
  if (mes < 10)
    mes = '0' + mes //agrega cero si el menor de 10
  $('#inputFechaAlta').val(ano + "-" + mes + "-" + dia);
}


