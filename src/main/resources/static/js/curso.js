$(function () {
    buscarCurso();
    llenarCampos();
    agregarmodificarCurso();
    eliminarCurso();
    limpiarTodo();
});

function cambiodeBoton(name, modal) {
    var button = $(name);
    var recipient = button.data("titulo");
    var recipient2 = button.data("boton");
    console.log(recipient, recipient2);
    var modal = $(modal);
    modal.find(".modal-title").text(recipient);
    modal.find(".modal-footer button[id=botonAceptar]").text(recipient2);
}

function buscarCurso() {
    $(".f").remove();
    var url = "./api/v1/Cursos";
    $.ajax(url, {
        contentType: "application/json",
        dataType: "json",
        type: "GET",
        success: function (datos) {
            if (datos.length == 0) {
                Toast.fire({
                    icon: "warning",
                    title: "No hay cursos registrados"
                });
            } else {
                console.log(datos)
                $.each(datos, function (i, e) {
                    $("#A-tabla").append(
                        "<tr class='f' data-id=" +
                        e.id +
                        ">" +
                        "<td>" +
                        "<input value='" +
                        e.id +
                        "' type='radio' class='form-check-input' name='selected'>" +
                        "</td>" +
                        "<td>" +
                        e.nivel +
                        "</td>" +
                        "<td>" +
                        e.etapa +
                        "</td>" +
                        "</tr>"
                    );
                });
            }
        },
        error: function (xhr) {
            Toast.fire({
                icon: "error",
                title: "Error al buscar cursos >>> " + xhr.status + " " + xhr.statusText
            });
        }
    });
}

function agregarmodificarCurso() {
    $("#botonAceptar").on("click", function (e) {
        e.preventDefault();
        var id = $("input:radio[name=selected]:checked").val();
        var nivel = $("#inputNivel").val();
        var etapa = $("#inputEtapa").val();

        var curso = {
            nivel: nivel,
            etapa: etapa,
        };

        if (nivel && etapa) {
            var elboton = $("#myModal3")
                .find(".modal-footer button[id=botonAceptar]")
                .text();
            if (elboton == "Crear") {
                $.ajax("./api/v1/Cursos", {
                    contentType: "application/json",
                    dataType: "json",
                    type: "POST",
                    data: JSON.stringify(curso),
                    success: function (e) {
                        Toast.fire({
                            icon: "success",
                            title: "El curso ha sido agregado con exito"
                        });
                        $("#myModal3").modal("hide");
                        setTimeout(redirect, 1300);
                    },
                    error: function (xhr) {
                        Toast.fire({
                            icon: "error",
                            title:
                                "Error, " + xhr.responseJSON.message
                        });
                    }
                });
            } else if (elboton == "Modificar") {
                curso["id"] = id;
                $.ajax("./api/v1/Cursos", {
                    contentType: "application/json",
                    dataType: "json",
                    type: "PUT",
                    data: JSON.stringify(curso),
                    success: function (datos) {
                        Toast.fire({
                            icon: "success",
                            title: "El curso ha sido modificado con exito"
                        });

                        $("#myModal3").modal("hide");
                        setTimeout(redirect, 1300);
                    },
                    error: function (xhr) {
                        Toast.fire({
                            icon: "error",
                            title:
                                "Error," + xhr.responseJSON.message
                        });
                    }
                });
            }
        } else {
            Toast.fire({
                icon: "error",
                title: "Faltan datos por rellenar"
            });
        }
    });
}

function llenarCampos() {
    $("#btnEditar").on("click", function (e) {
        e.preventDefault();
        var id = $("input:radio[name=selected]:checked").val();
        if (!id) {
            Toast.fire({
                icon: "error",
                title: "Ningun curso ha sido seleccionado"
            });
            return;
        }
        cambiodeBoton("#btnEditar", "#myModal3");
        $("#myModal3").modal("show");
        $.ajax("./api/v1/Cursos/" + id, {
            type: "GET",
            success: function (datos) {
                $("#inputNivel").val(datos.nivel);
                $("#inputEtapa").val(datos.etapa);
            },
            error: function (xhr) {
                $("#myModal3").modal("hide");
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
        cambiodeBoton("#btnCrear", "#myModal3");
    });

}

function eliminarCurso() {
    $("#btnbaja").on("click", function (e) {
        e.preventDefault();
        var id = $("input:radio[name=selected]:checked").val();

        if (!id) {
            Toast.fire({
                icon: "error",
                title: "Ningun curso ha sido seleccionado"
            });
            return;
        }

        Swal.fire({
            title: "  ¿Desea eliminar el curso?",
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
                $.ajax("./api/v1/Cursos/" + id, {
                    type: "DELETE",

                    success: function () {
                        Toast.fire({
                            icon: "success",
                            title: "El curso ha sido eliminado"
                        });

                        setTimeout(redirect, 1300);
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

function redirect() {
    window.location.href = "./curso";
}

function limpiarTodo() {
    $("input[type=text]").val("");
    $("input[type=number]").val("");
}
