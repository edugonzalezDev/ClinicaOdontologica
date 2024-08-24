package com.example.ClinicaOdontologicaSpringMVC.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Data

public class Odontologo {
    //atributos de la clase paciente
    private Integer id;
    private String matricula;
    private String nombre;
    private String apellido;

}

