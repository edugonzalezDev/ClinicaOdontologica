package com.example.ClinicaOdontologicaSpringMVC.Service;

import com.example.ClinicaOdontologicaSpringMVC.Dao.PacienteDAOH2;
import com.example.ClinicaOdontologicaSpringMVC.Dao.iDao;
import com.example.ClinicaOdontologicaSpringMVC.Model.Paciente;
import org.springframework.stereotype.Service;

@Service
public class PacienteService {
    private iDao<Paciente> pacienteiDao;

    public PacienteService() {
        pacienteiDao= new PacienteDAOH2();
    }

    public Paciente guardarPaciente(Paciente paciente){
        return pacienteiDao.guardar(paciente);
    }
    public Paciente buscarPorId(Integer id){
        return pacienteiDao.buscarPorId(id);
    }
    public Paciente buscarPorCorreo(String correo){
        return pacienteiDao.buscarPorString(correo);
    }
    public Paciente actualizarPaciente (Paciente paciente) {
        pacienteiDao.actualizar(paciente);
        return null; }
    public void eliminarPaciente (Integer id){
        pacienteiDao.eliminar(id);
    }
}