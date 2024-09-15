package com.example.ClinicaOdontologicaSpringMVC.Repository;

import com.example.ClinicaOdontologicaSpringMVC.Entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Integer> {
    Optional<Paciente> findByCorreo(String correo);
    boolean existsByCedula(String cedula);
}
