package com.example.ClinicaOdontologicaSpringMVC.controller;

import com.example.ClinicaOdontologicaSpringMVC.entity.Odontologo;
import com.example.ClinicaOdontologicaSpringMVC.entity.Paciente;
import com.example.ClinicaOdontologicaSpringMVC.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/paciente")
public class PacienteController {
    @Autowired
    private PacienteService pacienteService;

    public PacienteController() {
        pacienteService= new PacienteService();
    }
    @GetMapping("/buscar")
    public Optional<Paciente> buscarPorCorreo(@RequestParam("email") String email)  {
        return pacienteService.buscarPorCorreo(email);
    }
    @GetMapping("/todos")
    public ResponseEntity<List<Paciente>> listarTodos(){
        return ResponseEntity.ok(pacienteService.listarOdontologos());
    }
    @GetMapping("/buscar/{id}")
    public   Optional<Paciente> buscarPorId (@PathVariable Integer id) {
        Optional<Paciente> paciente = pacienteService.buscarPorId(id);
        return paciente;
    }
    @PostMapping
    public Paciente guardarPaciente(@RequestBody Paciente paciente){
        return pacienteService.guardarPaciente(paciente);
    }
    @DeleteMapping("/eliminar")
    public void eliminarPaciente(@RequestParam Integer id){
        pacienteService.eliminarPaciente(id);
    }
    @PutMapping("/actualizar")
    public Paciente actualizarPaciente (@RequestBody Paciente paciente){
        return pacienteService.actualizarPaciente(paciente);
    }

}