package com.example.ClinicaOdontologicaSpringMVC.Repository;

import com.example.ClinicaOdontologicaSpringMVC.Entity.Domicilio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DomicilioRepository extends JpaRepository<Domicilio, Integer> {
}
