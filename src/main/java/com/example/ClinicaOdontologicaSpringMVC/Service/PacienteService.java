package com.example.ClinicaOdontologicaSpringMVC.Service;

import com.example.ClinicaOdontologicaSpringMVC.Exception.BadRequestException;
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
    public Optional<Paciente> buscarPorCorreo(String correo){
        return pacienteRepository.findByCorreo(correo);
    }
    public List<Paciente> listarPacientes() throws BadRequestException {
        List<Paciente> pacientes = pacienteRepository.findAll();
        if (pacientes.isEmpty()) {
            throw new BadRequestException("No se encontraron pacientes registrados");
        }
        return pacientes;
    }
    public Paciente actualizarPaciente (Paciente paciente) {
        return pacienteRepository.save(paciente);
    }
    public void eliminarPaciente (Integer id){
        pacienteRepository.deleteById(id);
    }
}