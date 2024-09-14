package com.example.ClinicaOdontologicaSpringMVC.Dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class TurnoDTO {
    private Integer id;
    private LocalDate fecha;
    private Integer pacienteId;
    private String pacienteNombreApellido;
    private Integer odontologoId;
    private String odontologoNombreApellido;
}
