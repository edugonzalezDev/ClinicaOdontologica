window.addEventListener('load', function () {
    const formulario = document.querySelector('#add_new_paciente');

    formulario.addEventListener('submit', function (event) {
        event.preventDefault(); // Evita que se envíe el formulario por defecto

        // Creamos un JSON que tendrá los datos del nuevo paciente
        const formData = {
            nombre: document.querySelector('#nombre').value,
            apellido: document.querySelector('#apellido').value,
            cedula: document.querySelector('#cedula').value,
            fechaIngreso: "2024-08-20",
            domicilio: {
                calle: document.querySelector('#calle').value,
                numero: document.querySelector('#numero').value,
                localidad: document.querySelector('#localidad').value,
                provincia: document.querySelector('#provincia').value,
            },
            correo: document.querySelector('#correo').value,
        };

        const url = '/paciente';
        const settings = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        };

        fetch(url, settings)
            .then(response => {
                if (!response.ok) {
                    // Intentar obtener el mensaje de error si es un JSON
                    return response.text().then(errorText => {
                        let errorMessage;
                        try {
                            const errorData = JSON.parse(errorText);
                            errorMessage = errorData.message || 'Error al agregar el paciente, verifique los campos.';
                        } catch (e) {
                            errorMessage = errorText || 'Error al agregar el paciente, verifique los campos: Todos los campos deben estar completos, Nombre, Apellido y Cédula deben tener entre 3 y 15 caracteres y el Correo electrónico debe tener un formato correcto';
                        }
                        throw new Error(errorMessage);
                    });
                }
                return response.json(); // Si la respuesta es correcta, convertimos a JSON
            })
            .then(data => {
                // Mostrar mensaje de éxito
                let successAlert = '<div class="alert alert-success alert-dismissible">' +
                    '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                    '<strong></strong> Paciente agregado con éxito</div>';

                document.querySelector('#response').innerHTML = successAlert;
                document.querySelector('#response').style.display = "block";
                resetUploadForm();
            })
            .catch(error => {
                // Mostrar mensaje de error
                let errorAlert = '<div class="alert alert-danger alert-dismissible">' +
                    '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                    '<strong> Error: ' + error.message + '</strong> </div>';

                document.querySelector('#response').innerHTML = errorAlert;
                document.querySelector('#response').style.display = "block";
            });
    });

    function resetUploadForm() {
        document.querySelector('#nombre').value = "";
        document.querySelector('#apellido').value = "";
        document.querySelector('#cedula').value = "";
        document.querySelector('#correo').value = "";
        document.querySelector('#calle').value = "";
        document.querySelector('#numero').value = "";
        document.querySelector('#localidad').value = "";
        document.querySelector('#provincia').value = "";
    }
});
