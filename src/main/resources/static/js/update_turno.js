function editar(id) {
    var row = document.getElementById('tr_turno_' + id); // Selecciona la fila por su ID
    var fechaCell = document.getElementById('fecha_' + id); // Solo la celda de la fecha

    // Convertir el contenido de la fecha a un campo de entrada para editar
    var fechaContent = fechaCell.innerText;
    fechaCell.innerHTML = '<input type="date" class="form-control" value="' + fechaContent + '">';

    // Cambiar el botón a un botón de guardar después de editar
    var editButton = row.querySelector('.action-column button');
//    editButton.innerHTML = 'Guardar';
    editButton.innerHTML =
        '<svg xmlns="http://www.w3.org/2000/svg" height="16" viewBox="0 -960 960 960" width="16" fill="#e8eaed"><path d="M840-680v480q0 33-23.5 56.5T760-120H200q-33 0-56.5-23.5T120-200v-560q0-33 23.5-56.5T200-840h480l160 160Zm-80 34L646-760H200v560h560v-446ZM480-240q50 0 85-35t35-85q0-50-35-85t-85-35q-50 0-85 35t-35 85q0 50 35 85t85 35ZM240-560h360v-160H240v160Zm-40-86v446-560 114Z"/></svg>';
    editButton.className = 'btn btn-success btn-sm';
    editButton.onclick = function() { saveChanges(id); };
}


function saveChanges(id) {
    var row = document.getElementById('tr_turno_' + id);

    // Obtener la nueva fecha desde el campo de entrada
    var fecha = document.querySelector('#fecha_' + id + ' input').value;

    // Obtener los IDs de paciente y odontólogo
    var pacienteId = document.querySelector('#paciente_' + id).getAttribute('data-id');
    var odontologoId = document.querySelector('#odontologo_' + id).getAttribute('data-id');

    // Crear el objeto JSON con el formato solicitado
    const formData = {
        id: id,
        paciente: {
            id: pacienteId
        },
        odontologo: {
            id: odontologoId
        },
        fecha: fecha
    };

    // Configuración del fetch para enviar los datos actualizados al servidor
    const url = '/turno/actualizar'; // Ajusta el endpoint según tu API
    const settings = {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(formData)
    };

    fetch(url, settings)
        .then(response => response.json())
        .then(data => {
            // Actualizar la tabla con la nueva fecha
            document.querySelector('#fecha_' + id).innerText = data.fecha;

            // Cambiar el botón de nuevo a 'Editar'
            var editButton = row.querySelector('.action-column button');
            editButton.innerHTML =
                '<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pencil-square" viewBox="0 0 16 16">' +
                '<path d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z"/>' +
                '<path fill-rule="evenodd" d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5z"/>' +
                "</svg>";
            editButton.className = 'btn btn-warning btn-sm';
            editButton.onclick = function() { editar(id); };
        })
        .catch(error => {
            console.error('Error al guardar los cambios:', error);
        });
}
