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
    public Odontologo buscarPorID(Integer id){
        return OdontologoiDao.buscarporId(id);
    }

    public List<Odontologo> verOdontologos(){
        return OdontologoiDao.listarTodos();
    }
}