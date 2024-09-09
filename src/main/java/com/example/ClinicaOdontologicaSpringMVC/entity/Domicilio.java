package com.example.ClinicaOdontologicaSpringMVC.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name="domicilio")
public class Domicilio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Size(min = 3, max = 15)
    private String calle;

    @NotNull
    @Size(min = 3, max = 5)
    private Integer numero;

    @NotNull
    @Size(min = 3, max = 15)
    private String localidad;

    @NotNull
    @Size(min = 3, max = 15)
    private String provincia;

}
