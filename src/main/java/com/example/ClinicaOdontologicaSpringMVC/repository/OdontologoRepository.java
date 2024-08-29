package com.example.ClinicaOdontologicaSpringMVC.repository;

import com.example.ClinicaOdontologicaSpringMVC.entity.Odontologo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface OdontologoRepository extends JpaRepository<Odontologo, Integer> {
    Optional<Odontologo> findByMatricula(String matricula);
}
