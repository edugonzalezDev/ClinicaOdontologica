package com.example.ClinicaOdontologicaSpringMVC.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Odontologo {
    //atributos de la clase paciente
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String matricula;
    @Column
    private String nombre;
    @Column
    private String apellido;

}

