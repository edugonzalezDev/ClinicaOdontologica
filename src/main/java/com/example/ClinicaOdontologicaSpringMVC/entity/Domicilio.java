package com.example.ClinicaOdontologicaSpringMVC.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
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
    @Min(value = 0, message = "El número debe ser mayor o igual a 0")
    @Max(value = 9999, message = "El número debe ser menor o igual a 9999")
    private Integer numero;

    @NotNull
    @Size(min = 3, max = 15)
    private String localidad;

    @NotNull
    @Size(min = 3, max = 15)
    private String provincia;

}
