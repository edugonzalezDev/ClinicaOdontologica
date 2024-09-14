window.addEventListener('load', function () {
    function establecerFechaMinima() {
        const inputFecha = document.querySelector('#fecha');
        const hoy = new Date();
        const year = hoy.getFullYear();
        const month = ('0' + (hoy.getMonth() + 1)).slice(-2); // Añadir el 0 en meses de 1 dígito
        const day = ('0' + hoy.getDate()).slice(-2); // Añadir el 0 en días de 1 dígito

        const fechaMinima = `${year}-${month}-${day}`; // Usar backticks correctos
        inputFecha.setAttribute('min', fechaMinima);
    }

        // Establecer la fecha mínima al cargar la página
        establecerFechaMinima();
    // Función para cargar los pacientes en el selector
    function cargarPacientes() {
        const urlPacientes = '/paciente/todos'; // Ajusta el endpoint según tu API
        const settings = {
            method: 'GET'
        };

        fetch(urlPacientes, settings)
            .then(response => response.json())
            .then(data => {
                let selectPaciente = document.querySelector('#select-paciente');
                data.forEach(paciente => {
                    let option = document.createElement('option');
                    option.value = paciente.id;
                    option.text = paciente.nombre + ' ' + paciente.apellido;
                    selectPaciente.appendChild(option);
                });
            })
            .catch(error => console.error('Error al cargar pacientes:', error));
    }

    // Función para cargar los odontólogos en el selector
    function cargarOdontologos() {
        const urlOdontologos = '/odontologo/todos'; // Ajusta el endpoint según tu API
        const settings = {
            method: 'GET'
        };

        fetch(urlOdontologos, settings)
            .then(response => response.json())
            .then(data => {
                let selectOdontologo = document.querySelector('#select-odontologo');
                data.forEach(odontologo => {
                    let option = document.createElement('option');
                    option.value = odontologo.id;
                    option.text = odontologo.nombre + ' ' + odontologo.apellido;
                    selectOdontologo.appendChild(option);
                });
            })
            .catch(error => console.error('Error al cargar odontólogos:', error));
    }

    // Cargar los selectores de pacientes y odontólogos al cargar la página
    cargarPacientes();
    cargarOdontologos();

    // Captura el formulario
    const formulario = document.querySelector('#add_new_turno');

    formulario.addEventListener('submit', function (event) {
        event.preventDefault(); // Evita que se envíe el formulario por defecto

        // Crea un objeto JSON con los datos del nuevo turno
        const formData = {
            paciente: {
                id: document.querySelector('#select-paciente').value,
            },
            odontologo: {
                id : document.querySelector('#select-odontologo').value,
            },
            fecha: document.querySelector('#fecha').value,
        };

        // Configuración del fetch para enviar los datos a la API
        const url = '/turno'; // Ajusta este endpoint según tu API
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
                            errorMessage = errorData.message || 'Error al registrar turno, verifique los campos: Todos los campos deben estar completos';
                        } catch (e) {
                            errorMessage = errorText; // Si no es JSON, usar el texto directamente
                        }
                        throw new Error(errorMessage);
                    });
                }
                return response.json(); // Si la respuesta es correcta, convertimos a JSON
            })
            .then(data => {
                let successAlert = '<div class="alert alert-success alert-dismissible">' +
                    '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                    '<strong></strong> Turno registrado correctamente </div>';

                document.querySelector('#response').innerHTML = successAlert;
                document.querySelector('#response').style.display = "block";
                resetForm();
            })
            .catch(error => {
                let errorAlert = '<div class="alert alert-danger alert-dismissible">' +
                    '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                    '<strong> Error: ' + error.message + '</strong> </div>';

                document.querySelector('#response').innerHTML = errorAlert;
                document.querySelector('#response').style.display = "block";
            });
    });

    // Función para reiniciar el formulario
    function resetForm() {
        document.querySelector('#select-paciente').selectedIndex = 0;
        document.querySelector('#select-odontologo').selectedIndex = 0;
        document.querySelector('#fecha').value = "";
    }
});
