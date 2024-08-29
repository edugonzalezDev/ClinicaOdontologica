package com.example.ClinicaOdontologicaSpringMVC.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name="turno")
public class Turno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "paciente_id", nullable = false)
    private Paciente paciente;
    @ManyToOne
    @JoinColumn(name = "odontologo_id", nullable = false)
    private Odontologo odontologo;
    private LocalDate fecha;
}
