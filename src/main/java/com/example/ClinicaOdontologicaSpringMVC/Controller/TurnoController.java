package com.example.ClinicaOdontologicaSpringMVC.Controller;

import com.example.ClinicaOdontologicaSpringMVC.Entity.Odontologo;
import com.example.ClinicaOdontologicaSpringMVC.Entity.Paciente;
import com.example.ClinicaOdontologicaSpringMVC.Entity.Turno;
import com.example.ClinicaOdontologicaSpringMVC.Exception.BadRequestException;
import com.example.ClinicaOdontologicaSpringMVC.Exception.ResourceNotFoundException;
import com.example.ClinicaOdontologicaSpringMVC.Service.OdontologoService;
import com.example.ClinicaOdontologicaSpringMVC.Service.PacienteService;
import com.example.ClinicaOdontologicaSpringMVC.Service.TurnoService;
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
    public ResponseEntity<Turno> registrarTurno(@RequestBody Turno turno) throws BadRequestException {
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPorId(turno.getPaciente().getId());
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarPorId(turno.getOdontologo().getId());

        if (pacienteBuscado.isPresent()&&odontologoBuscado.isPresent()) {
            return ResponseEntity.ok(turnoService.guardarTurno(turno));

        }else{
            String errorMessage = "El paciente o el odontólogo no existen.";
            throw new BadRequestException(errorMessage);
        }

    }
    @GetMapping("/todos")
    public ResponseEntity<List<Turno>> listarTodos() throws BadRequestException {
        return ResponseEntity.ok(turnoService.listarTurnos());
    }
    @GetMapping("/buscar/{id}")
    public ResponseEntity<Turno> buscarTurnoPorId(@PathVariable Integer id) throws ResourceNotFoundException {
        Optional<Turno> turnoBuscado = turnoService.buscarPorId(id);
        if (turnoBuscado.isPresent()) {
            return ResponseEntity.ok(turnoBuscado.get());
        } else {
            throw new ResourceNotFoundException("Turno no encontrado");
        }
    }
    @DeleteMapping("/eliminar")
    public void eliminarTurno(@RequestParam Integer id){
        turnoService.eliminarTurno(id);
    }
    @PutMapping("/actualizar")
    public Turno actualizarTurno(@RequestBody Turno turno) throws BadRequestException {
        if (turno.getId() == null) {
            throw new BadRequestException("El ID del turno no puede ser nulo");
        }
        // Otras validaciones según sea necesario
        return turnoService.actualizarTurno(turno);
    }

}
