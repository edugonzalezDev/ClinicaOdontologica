package com.example.ClinicaOdontologicaSpringMVC.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name="paciente")
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Size(min = 3, max = 15)
    private String nombre;

    @NotNull
    @Size(min = 3, max = 15)
    private String apellido;

    @NotNull
    @Size(min = 3, max = 15)
    private String cedula;

    @NotNull
    private LocalDate fechaIngreso;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "domicilio_id", nullable = false, referencedColumnName = "id")
    private Domicilio domicilio;

    @NotNull
    @Email
    private String correo;
    @OneToMany(mappedBy = "paciente",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Turno> turnos_paciente= new ArrayList<>();

}
