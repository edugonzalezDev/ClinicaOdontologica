package com.example.ClinicaOdontologicaSpringMVC.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


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

    @NotNull
    @Size(min = 3, max = 15)
    @Column
    private String matricula;

    @NotNull
    @Size(min = 3, max = 15)
    @Column
    private String nombre;

    @NotNull
    @Size(min = 3, max = 15)
    @Column
    private String apellido;

    @OneToMany(mappedBy = "odontologo",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Turno> turnos= new ArrayList<>();

}

