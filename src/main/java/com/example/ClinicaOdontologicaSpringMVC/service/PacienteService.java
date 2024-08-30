package com.example.ClinicaOdontologicaSpringMVC.service;

import com.example.ClinicaOdontologicaSpringMVC.repository.PacienteRepository;
import com.example.ClinicaOdontologicaSpringMVC.entity.Paciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Optional<Paciente> buscarPorCorreo(String correo){
        return pacienteRepository.findByCorreo(correo);
    }
    public Paciente actualizarPaciente (Paciente paciente) {
        return pacienteRepository.save(paciente);
    }
    public void eliminarPaciente (Integer id){
        pacienteRepository.deleteById(id);
    }
}