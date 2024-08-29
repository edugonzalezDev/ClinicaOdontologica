package com.example.ClinicaOdontologicaSpringMVC.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="domicilio")
public class Domicilio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String calle;
    private Integer numero;
    private String localidad;
    private String provincia;

}
