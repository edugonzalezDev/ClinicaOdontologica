package com.example.ClinicaOdontologicaSpringMVC.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Data
@Entity
@Table(name="paciente")
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private String apellido;
    private String cedula;
    private LocalDate fechaIngreso;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "domicilio_id", nullable = false, referencedColumnName = "id")
    private Domicilio domicilio;
    private String correo;

}
