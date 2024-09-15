package com.example.ClinicaOdontologicaSpringMVC.Service;

import com.example.ClinicaOdontologicaSpringMVC.Repository.PacienteRepository;
import com.example.ClinicaOdontologicaSpringMVC.Entity.Paciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {
    @Autowired
    private PacienteRepository pacienteRepository;

    public Paciente guardarPaciente(Paciente paciente){
        return pacienteRepository.save(paciente);
    }
    public Optional<Paciente> buscarPorId(Integer id){
        return pacienteRepository.findById(id);
    }

    public boolean validarCedula(String cedula){
        return pacienteRepository.existsByCedula(cedula);
    }

    public Optional<Paciente> buscarPorCorreo(String correo){
        return pacienteRepository.findByCorreo(correo);
    }

    public List<Paciente> listarPacientes() {
        return pacienteRepository.findAll();
    }

    public Paciente actualizarPaciente (Paciente paciente) {
        return pacienteRepository.save(paciente);
    }

    public void eliminarPaciente (Integer id){
        pacienteRepository.deleteById(id);
    }
}