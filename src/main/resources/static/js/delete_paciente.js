function deleteBy(id)
{
          //con fetch invocamos a la API de peliculas con el método DELETE
          //pasandole el id en la URL
          const url = '/paciente/eliminar?id='+ id;
          const settings = {
              method: 'DELETE'
          }
          fetch(url,settings)
          .then(response => response.json())

          //borrar la fila del paciente eliminado
          let row_id = "#tr_paciente_" + id;
          document.querySelector(row_id).remove();

}