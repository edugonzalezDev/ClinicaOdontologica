function deleteBy(id) {
    // Invocar la API para eliminar el paciente con el método DELETE
    const url = '/paciente/eliminar?id=' + id;
    const settings = {
        method: 'DELETE'
    }

    fetch(url, settings)
        .then(response => {
            if (response.ok) {
                // Eliminar la fila del paciente solo si la respuesta es exitosa
                let row_id = "#tr_paciente_" + id;
                document.querySelector(row_id).remove();
            } else {
                console.error('Error al eliminar el paciente');
            }
        })
        .catch(error => {
            console.error('Hubo un problema con la petición:', error);
        });
}
