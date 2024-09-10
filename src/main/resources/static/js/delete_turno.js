function deleteBy(id)
{
          //con fetch invocamos a la API de Turno con el método DELETE
          //pasandole el id en la URL
          const url = '/turno/eliminar?id='+ id;
          const settings = {
              method: 'DELETE'
          }
          fetch(url,settings)
          .then(response => response.json())

          //borrar la fila de la pelicula eliminada
          let row_id = "#tr_turno_" + id;
          document.querySelector(row_id).remove();

}