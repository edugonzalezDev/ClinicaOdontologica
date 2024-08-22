package com.example.ClinicaOdontologicaSpringMVC.Service;

import com.example.ClinicaOdontologicaSpringMVC.Dao.DomicilioDAOH2;
import com.example.ClinicaOdontologicaSpringMVC.Dao.iDao;
import com.example.ClinicaOdontologicaSpringMVC.Model.Domicilio;

public class DomicilioService {
    private iDao<Domicilio> domicilioiDao;
    public DomicilioService(){
        domicilioiDao = new DomicilioDAOH2();
    }
    public void eliminarDomicilio(Integer id) {
        domicilioiDao.eliminar(id);
    }
}
