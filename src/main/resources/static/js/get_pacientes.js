window.addEventListener("load", function () {
    (function () {
        const url = "/paciente/todos";
        const settings = {
            method: "GET",
        };

        fetch(url, settings)
            .then((response) => response.json())
            .then((data) => {
                //recorremos los pacientes
                for (paciente of data) {
                    //por cada paciente armamos una fila con el id
                    var table = document.getElementById("pacienteTable");
                    var pacienteRow = table.insertRow();
                    let tr_id = "tr_paciente_" + paciente.id;
                    pacienteRow.id = tr_id;

                    //Se crea un botón "delete" en cada fila de paciente que llama a la función deleteBy para eliminar el paciente mediante la API.
                    let deleteButton =
                        "<button" +
                        " id=" +
                        '"' +
                        "btn_delete_paciente_" +
                        paciente.id +
                        '"' +
                        ' type="button" onclick="deleteBy(' +
                        paciente.id +
                        ')" class="btn btn-danger btn-sm">' +
                        '<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-trash" viewBox="0 0 16 16">' +
                        '<path d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5m2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5m3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0z"/>' +
                        '<path d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1zM4.118 4 4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4zM2.5 3h11V2h-11z"/>' +
                        "</svg>" +
                        "</button>";
                    //Se crea un botón "update" en cada fila de paciente que llama a la función editar para editar la fila.
                    let updateButton =
                        "<button" +
                        " id=" +
                        '"' +
                        "btn_id_paciente_" +
                        paciente.id +
                        '"' +
                        ' type="button" onclick="editar(' +
                        paciente.id +
                        ')" class="btn btn-warning btn-sm">' +
                        '<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pencil-square" viewBox="0 0 16 16">' +
                        '<path d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z"/>' +
                        '<path fill-rule="evenodd" d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5z"/>' +
                        "</svg>" +
                        "</button>";

                    //Agregamos los datos del paciente a la fila junto con los botones "editar" y "eliminar".
                    pacienteRow.innerHTML =
                        "<td>" +
                        paciente.id +
                        "</td>" +
                        "<td id=nombre_paciente_" +
                        paciente.id +
                        ">" +
                        paciente.nombre.toUpperCase() +
                        "</td>" +
                        "<td id=apellido_paciente_" +
                        paciente.id +
                        ">" +
                        paciente.apellido.toUpperCase() +
                        "</td>" +
                        "<td id=correo_paciente_" +
                        paciente.id +
                        ">" +
                        paciente.correo +
                        "</td>" +
                        "<td id=cedula_paciente_" +
                        paciente.id +
                        ">" +
                        paciente.cedula +
                        "</td>" +
                        "<td id=fechaIngreso_paciente_" +
                        paciente.id +
                        ">" +
                        paciente.fechaIngreso +
                        "</td>" +
                        "<td id=id_domicilio_paciente_" +
                        paciente.id +
                        " style='display: none;'>" +
                        paciente.domicilio.id +
                        "</td>" +
                        "<td id=calle_paciente_" +
                        paciente.id +
                        ">" +
                        paciente.domicilio.calle +
                        "</td>" +
                        "<td id=numero_paciente_" +
                        paciente.id +
                        ">" +
                        paciente.domicilio.numero +
                        "</td>" +
                        "<td id=localidad_paciente_" +
                        paciente.id +
                        ">" +
                        paciente.domicilio.localidad +
                        "</td>" +
                        "<td id=provincia_paciente_" +
                        paciente.id +
                        ">" +
                        paciente.domicilio.provincia +
                        "</td>" +
                        '<td class="action-column">' +
                        updateButton +
                        "</td>" +
                        '<td class="action-column">' +
                        deleteButton +
                        "</td>";
                }
            });
    })(function () {
        let pathname = window.location.pathname;
        if (pathname == "./get_pacientes.html") {
            document.querySelector(".nav .nav-item a:last").addClass("active");
        }
    });
});
