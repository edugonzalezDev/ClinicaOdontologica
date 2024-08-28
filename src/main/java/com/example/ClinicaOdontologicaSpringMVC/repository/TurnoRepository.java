package com.example.ClinicaOdontologicaSpringMVC.repository;

import com.example.ClinicaOdontologicaSpringMVC.entity.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TurnoRepository extends JpaRepository<Turno, Integer> {
}
