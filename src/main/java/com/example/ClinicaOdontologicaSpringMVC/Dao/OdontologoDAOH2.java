package com.example.ClinicaOdontologicaSpringMVC.Dao;

import com.example.ClinicaOdontologicaSpringMVC.Model.Odontologo;
import com.example.ClinicaOdontologicaSpringMVC.Service.OdontologoService;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OdontologoDAOH2 implements iDao<Odontologo>{

    private static final Logger logger=Logger.getLogger(OdontologoDAOH2.class);

    private static final String SQL_INSERT="INSERT INTO ODONTOLOGOS (MATRICULA, NOMBRE, APELLIDO) VALUES(?,?,?)";
    private static final String SQL_SELECT_BY_ID ="SELECT * FROM ODONTOLOGOS WHERE ID=?";
    private static final String SQL_SELECT_BY_MATRICULA="SELECT * FROM ODONTOLOGOS WHERE MATRICULA=?";
    private static final String SQL_SELECT="SELECT * FROM ODONTOLOGOS";
    private static final String SQL_DELETE_ODONTOLOGO= "DELETE FROM ODONTOLOGOS WHERE ID=?";
    private static final String SQL_UPDATE_ODONTOLOGO= "UPDATE ODONTOLOGOS SET NOMBRE =?, APELLIDO =?, MATRICULA =? WHERE id = ?;";

    @Override
    public Odontologo guardar(Odontologo odontologo) {
        logger.info("iniciando las operaciones de : guardado de: "+odontologo.getNombre());
        Connection connection=null;
        try {
            connection = BD.getConnection();
            PreparedStatement psInsert = connection.prepareStatement(SQL_INSERT,Statement.RETURN_GENERATED_KEYS);
            //aca comunicamos mundo objeto con tabla
            psInsert.setString(1, odontologo.getMatricula());
            psInsert.setString(2, odontologo.getNombre());
            psInsert.setString(3,odontologo.getApellido());
            psInsert.execute(); //aca se carga el ID del odontologo

            ResultSet rs= psInsert.getGeneratedKeys(); //traigo los ID
            while(rs.next()){
                odontologo.setId(rs.getInt(1));
            }
            logger.info("odontologo cargado : "+odontologo.getId());
        }catch (Exception e){
            logger.error("problemas con la BD"+e.getMessage());
        }
        return odontologo;
    }

    @Override
    public void actualizar(Odontologo odontologo) {

        Connection connection=null;
        OdontologoService odontologoService = new OdontologoService();
        Odontologo odontologoActual = odontologoService.buscarPorId(odontologo.getId());

        logger.info("iniciando actualización del odontologo: "+odontologoActual.getNombre());

        try{
            connection=BD.getConnection();
            PreparedStatement psUpdate = connection.prepareStatement(SQL_UPDATE_ODONTOLOGO);
            psUpdate.setString(1,odontologo.getNombre());
            psUpdate.setString(2, odontologo.getApellido());
            psUpdate.setString(3,odontologo.getMatricula());
            psUpdate.setInt(4, odontologo.getId());
            psUpdate.execute();

            Odontologo odontologoActualizado = odontologoService.buscarPorId(odontologo.getId());
            logger.info("***************************");
            logger.info("Odontologo actualizado");
            logger.info("---------------------------");
            logger.info("Nombre: "+ odontologoActualizado.getNombre());
            logger.info("Apellido: "+ odontologoActualizado.getApellido());
            logger.info("Matrícula: "+ odontologoActualizado.getNombre());
            logger.info("***************************");

        }catch (Exception e){
            logger.error("problemas con la BD: "+e.getMessage());
        }

    }

    @Override
    public void eliminar(Integer id) {
        logger.info("iniciando las operaciones de eliminacion de odontologo:");
        Connection connection=null;
        try{
            connection=BD.getConnection();
            PreparedStatement psDelete= connection.prepareStatement(SQL_DELETE_ODONTOLOGO);
            psDelete.setInt(1, id);
            psDelete.execute();

//            ResultSet rs= psDelete.executeQuery();

            logger.info("Odontologo eliminado");
        }catch (Exception e){
            logger.error("problemas con la BD"+e.getMessage());
        }

    }

    @Override
    public List<Odontologo> listarTodos() {
        logger.info("iniciando las operaciones de : listar todos los Odontologos");
        List<Odontologo> odontologos = new ArrayList<>();
        Connection connection=null;
        try{
            connection=BD.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT);

            while (resultSet.next()) {
                Integer id = resultSet.getInt("ID");
                String matricula = resultSet.getString("MATRICULA");
                String nombre = resultSet.getString("NOMBRE");
                String apellido = resultSet.getString("APELLIDO");
                Odontologo odontologo = new Odontologo(id, matricula, nombre, apellido);
                odontologos.add(odontologo);
            }
        }


        catch (Exception e){
            logger.error("problemas con la BD"+e.getMessage());
        }
        return odontologos;
    }



    @Override
    public Odontologo buscarPorId(Integer id) {
        logger.info("iniciando las operaciones de : busqueda  de un odontologo con ID: "+id);
        Connection connection=null;
        Odontologo odontologo=null;
        try{
            connection=BD.getConnection();
            PreparedStatement psUpdate=connection.prepareStatement(SQL_SELECT_BY_ID);
            psUpdate.setInt(1,id);
            psUpdate.execute();
            ResultSet rs= psUpdate.executeQuery();
            while (rs.next()){
                odontologo = new Odontologo(rs.getInt(1),rs.getString(2),rs.getString(3), rs.getString(4));
            }

        }catch (Exception e){
            logger.error("problemas con la BD"+e.getMessage());
        }
        return odontologo;
    }

    @Override
    public Odontologo buscarPorString(String matricula) {

        logger.info("Iniciando la búsqueda de Odontologo con matrícula: "+matricula);
        Connection connection = null;
        Odontologo odontologo = null;
        try {
            connection = BD.getConnection();
            PreparedStatement psBuscarPorString = connection.prepareStatement(SQL_SELECT_BY_MATRICULA);
            psBuscarPorString.setString(1, matricula);
            psBuscarPorString.execute();
            ResultSet rs = psBuscarPorString.executeQuery();

            while (rs.next()){
                odontologo = new Odontologo(rs.getInt(1),rs.getString(2),rs.getString(3), rs.getString(4));
            }

        } catch (Exception e) {
            logger.error("problemas con la BD"+e.getMessage());
        }
        return odontologo;
    }

}