function editar(id) {

        var row = document.getElementById('tr_odontologo_' + id); // Selecciona la fila por su ID, que tiene el formato 'tr_' seguido por el número de ID
        var cells = row.getElementsByTagName('td'); // Obtiene todas las celdas de la fila seleccionada

        // Itera sobre las celdas y convierte su contenido a un campo de entrada para editar
        for (var i = 1; i < cells.length - 2; i++) { // Ignorar la primera celda (ID) y las dos últimas (acciones)
            var cellContent = cells[i].innerText;
            cells[i].innerHTML = '<input type="text" class="form-control" value="' + cellContent + '">';
        }

        // Cambia el botón a un botón de guardar después de editar
        var editButton = cells[cells.length - 2].getElementsByTagName('button')[0];
        editButton.innerHTML = 'Guardar';
        editButton.onclick = function() { saveChanges(id); };
    }

    function saveChanges(id) {
        // Selecciona la fila por su ID
        var row = document.getElementById('tr_odontologo_' + id);

        // Obtiene todas las celdas de la fila seleccionada
        var cells = row.getElementsByTagName('td');

        // Itera sobre las celdas y actualiza el texto con el valor del campo de entrada
        for (var i = 1; i < cells.length - 2; i++) { // Ignorar la primera celda (ID) y las dos últimas (acciones)
            var inputField = cells[i].getElementsByTagName('input')[0];
            if (inputField) {
                cells[i].innerText = inputField.value; // Actualiza el texto de la celda
            }
        }

        // Cambia el botón de nuevo a un botón de edición
        var editButton = cells[cells.length - 2].getElementsByTagName('button')[0];
        editButton.innerHTML = '<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pencil-square" viewBox="0 0 16 16">'+
                                                                         '<path d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z"/>'+
                                                                         '<path fill-rule="evenodd" d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5z"/>'+
                                                                       '</svg>';
        editButton.onclick = function() { editar(id); };
                    //creamos un JSON que tendrá los datos de la película
                    //a diferencia de una pelicula nueva en este caso enviamos el id
                    //para poder identificarla y modificarla para no cargarla como nueva
                    const formData = {
                        id: id,
                        matricula: document.querySelector('#matricula_odontologo_'+id).innerText,
                        nombre: document.querySelector('#nombre_odontologo_'+id).innerText,
                        apellido: document.querySelector('#apellido_odontologo_'+id).innerText,
                    };

                    //invocamos utilizando la función fetch la API peliculas con el método PUT que modificará
                    //la película que enviaremos en formato JSON
                    const url = '/odontologo/actualizar';
                    const settings = {
                        method: 'PUT',
                        headers: {
                            'Content-Type': 'application/json',
                        },
                        body: JSON.stringify(formData)
                    }
                      fetch(url,settings)
                      .then(response => response.json())

    }
