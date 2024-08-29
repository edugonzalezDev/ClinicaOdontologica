package com.example.ClinicaOdontologicaSpringMVC.controller;

import com.example.ClinicaOdontologicaSpringMVC.entity.Odontologo;
import com.example.ClinicaOdontologicaSpringMVC.entity.Paciente;
import com.example.ClinicaOdontologicaSpringMVC.entity.Turno;
import com.example.ClinicaOdontologicaSpringMVC.service.OdontologoService;
import com.example.ClinicaOdontologicaSpringMVC.service.PacienteService;
import com.example.ClinicaOdontologicaSpringMVC.service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/turno")
public class TurnoController {
    @Autowired
    private TurnoService turnoService;
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private OdontologoService odontologoService;

    @PostMapping
    public ResponseEntity<Turno> registrarTurno(@RequestBody Turno turno){
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPorId(turno.getPaciente().getId());
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarPorId(turno.getOdontologo().getId());

        if (pacienteBuscado!=null&&odontologoBuscado!=null) {
            return ResponseEntity.ok(turnoService.guardarTurno(turno));

        }else{
            return ResponseEntity.badRequest().build();
        }

    }
        @GetMapping
        public ResponseEntity<List<Turno>> listarTodos() {
            return ResponseEntity.ok(turnoService.listarTurnos());
        }
}
