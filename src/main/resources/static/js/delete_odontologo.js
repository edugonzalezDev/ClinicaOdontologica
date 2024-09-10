function deleteBy(id)
{
          //con fetch invocamos a la API de Odontologos con el método DELETE
          //pasandole el id en la URL
          const url = '/odontologo/eliminar/'+ id;
          const settings = {
              method: 'DELETE'
          }

    fetch(url, settings)
        .then(response => {
            if (response.ok) {
                // Eliminar la fila del odontologo solo si la respuesta es exitosa
                let row_id = "#tr_odontologo_" + id;
                document.querySelector(row_id).remove();
            } else {
                console.error('Error al eliminar el odontologo');
            }
        })
        .catch(error => {
            console.error('Hubo un problema con la petición:', error);
        });
}