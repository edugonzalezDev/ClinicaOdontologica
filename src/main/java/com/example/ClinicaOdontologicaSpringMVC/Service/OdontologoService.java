package com.example.ClinicaOdontologicaSpringMVC.Service;

import com.example.ClinicaOdontologicaSpringMVC.Dao.OdontologoDAOH2;
import com.example.ClinicaOdontologicaSpringMVC.Dao.iDao;
import com.example.ClinicaOdontologicaSpringMVC.Model.Odontologo;

import java.util.List;

public class OdontologoService {
    private iDao<Odontologo> OdontologoiDao;

    public OdontologoService() {
        OdontologoiDao= new OdontologoDAOH2();
    }

    public Odontologo guardarOdontologo(Odontologo odontologo){
        return OdontologoiDao.guardar(odontologo);
    }
    public Odontologo buscarPorId(Integer id){
        return OdontologoiDao.buscarPorId(id);
    }
    public Odontologo buscarPorMatricula(String matricula){
     return OdontologoiDao.buscarPorString(matricula);
    }
    public List<Odontologo> listarOdontologos(){
        return OdontologoiDao.listarTodos();
    }
    public void eliminarOdontologo (Integer id) {
        OdontologoiDao.eliminar(id);
    }
    public void actualizarOdontologo (Odontologo odontologo) {
        OdontologoiDao.actualizar(odontologo);
    }

}