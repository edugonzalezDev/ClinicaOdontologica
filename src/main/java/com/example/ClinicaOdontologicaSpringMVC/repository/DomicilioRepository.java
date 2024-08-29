package com.example.ClinicaOdontologicaSpringMVC.repository;

import com.example.ClinicaOdontologicaSpringMVC.entity.Domicilio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DomicilioRepository extends JpaRepository<Domicilio, Integer> {
}
